package com.qtxln.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qtxln.exception.BsUserException;
import com.qtxln.model.user.User;
import com.qtxln.model.user.dto.LoginRegisterDTO;
import com.qtxln.model.user.dto.UserDTO;
import com.qtxln.transport.InvokerResult;
import com.qtxln.user.mapper.UserMapper;
import com.qtxln.util.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author QT
 * 2018-08-23 17:03
 */
@Service
public class UserService {
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public InvokerResult checkLogin(String token) throws BsUserException, IOException {
        User user1 = TokenUtil.getUserId(token);
        User user = userMapper.findById(user1.getId());
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        // 重新生成token,即重新设置过期时间
        String jwt = JwtUtil.buildJWT(user.getId().toString());
        userDTO.setToken(jwt);
        return InvokerResult.getInstance(userDTO);
    }

    public InvokerResult register(LoginRegisterDTO loginRegisterDTO) throws BsUserException {
        User byUsername = userMapper.findByUsername(loginRegisterDTO.getUsername());
        if (byUsername != null) {
            throw new BsUserException(BsUserException.ErrorUserEnum.USERNAME_EXISTENT);
        }
        User user = new User();
        user.setUsername(loginRegisterDTO.getUsername());
        user.setPassword(bpe.encode(loginRegisterDTO.getPassword()));
        userMapper.insert(user);
        return InvokerResult.getInstance();
    }

    public InvokerResult login(LoginRegisterDTO loginRegisterDTO) throws BsUserException, JsonProcessingException {
        User user = userMapper.findByUsername(loginRegisterDTO.getUsername());
        if (user == null) {
            throw new BsUserException(BsUserException.ErrorUserEnum.USERNAME_NON_EXISTENT);
        }
        if (!bpe.matches(loginRegisterDTO.getPassword(), user.getPassword())) {
            throw new BsUserException(BsUserException.ErrorUserEnum.USERNAME_OR_PASSWORD_ERR);
        }
        //生成jwt
        user.setPassword(null);
        String jwt = JwtUtil.buildJWT(JsonUtil.objToJson(user));
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        userDTO.setToken(jwt);
        return InvokerResult.getInstance(userDTO);
    }

    public InvokerResult findAll(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.findAll();
        return InvokerResult.getInstance(PageDataUtil.toPageData(new PageInfo<>(userList)));
    }

    public InvokerResult updateUserEnableState(User user){
        userMapper.updateUserEnableState(user);
        return InvokerResult.getInstance();
    }
}
