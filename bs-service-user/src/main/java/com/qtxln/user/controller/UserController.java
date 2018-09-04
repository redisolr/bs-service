package com.qtxln.user.controller;

import com.qtxln.exception.BsUserException;
import com.qtxln.model.common.GeetInit;
import com.qtxln.model.user.User;
import com.qtxln.model.user.dto.LoginRegisterDTO;
import com.qtxln.transport.InvokerResult;
import com.qtxln.user.service.UserService;
import com.qtxln.util.GeetestLib;
import com.qtxln.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author QT
 * 2018-08-22 16:03
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    private final RedisTemplate<String, String> redisTemplate;
    private final UserService userService;

    @Autowired
    public UserController(RedisTemplate<String, String> redisTemplate, UserService userService) {
        this.redisTemplate = redisTemplate;
        this.userService = userService;
    }

    @GetMapping("checkLogin")
    public InvokerResult checkLogin(@RequestParam(value = "token", defaultValue = "") String token) throws BsUserException, IOException {
        return userService.checkLogin(token);
    }

    @GetMapping("geetestInit")
    public String geetestInit() throws IOException {
        GeetestLib geetestLib = new GeetestLib(GeetestLib.id, GeetestLib.key, GeetestLib.newfailback);

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<>();

        //进行验证预处理
        int gtServerStatus = geetestLib.preProcess(param);
        String key = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(key, JsonUtil.objToJson(gtServerStatus), 360, TimeUnit.SECONDS);

        String resStr = geetestLib.getResponseStr();

        GeetInit geetInit = JsonUtil.jsonToObj(resStr, GeetInit.class);
        geetInit.setStatusKey(key);
        return JsonUtil.objToJson(geetInit);
    }

    @PostMapping("register")
    public InvokerResult register(@RequestBody LoginRegisterDTO loginRegisterDTO) throws BsUserException, IOException {
        checkVerification(loginRegisterDTO);
        return userService.register(loginRegisterDTO);
    }

    @PostMapping("login")
    public InvokerResult login(@RequestBody LoginRegisterDTO loginRegisterDTO) throws IOException, BsUserException {
        checkVerification(loginRegisterDTO);
        return userService.login(loginRegisterDTO);
    }

    @GetMapping("list")
    public InvokerResult findAll(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        return userService.findAll(pageNum, pageSize);
    }

    @PutMapping("update/enable")
    public InvokerResult updateUserEnableState(@RequestBody User user) {
        return userService.updateUserEnableState(user);
    }

    private void checkVerification(LoginRegisterDTO loginRegisterDTO) throws IOException, BsUserException {
        GeetestLib geetestLib = new GeetestLib(GeetestLib.id, GeetestLib.key, GeetestLib.newfailback);
        String challenge = loginRegisterDTO.getChallenge();
        String validate = loginRegisterDTO.getValidate();
        String seccode = loginRegisterDTO.getSeccode();
        Integer statusCode = JsonUtil.jsonToObj(redisTemplate.opsForValue().get(loginRegisterDTO.getStatusKey()), Integer.class);
        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<>();

        Integer gtResult;

        if (statusCode == 1) {
            //gt-server正常，向gt-server进行二次验证
            gtResult = geetestLib.enhencedValidateRequest(challenge, validate, seccode, param);
            log.info(gtResult.toString());
        } else {
            // gt-server非正常情况下，进行failback模式验证
            log.info("failback:use your own server captcha validate");
            gtResult = geetestLib.failbackValidateRequest(challenge, validate, seccode);
            log.info(gtResult.toString());
        }
        if (!Objects.equals(gtResult, 1)) {
            throw new BsUserException(BsUserException.ErrorUserEnum.VERIFICATION_CODE_ERR);
        }
    }
}
