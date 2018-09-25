package com.qtxln.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qtxln.exception.BsUserException;
import com.qtxln.model.user.Admin;
import com.qtxln.model.user.dto.AdminDTO;
import com.qtxln.transport.InvokerResult;
import com.qtxln.user.mapper.AdminMapper;
import com.qtxln.util.DateUtil;
import com.qtxln.util.JwtUtil;
import com.qtxln.util.PageDataUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author QT
 * 2018-08-14 14:44
 */
@Slf4j
@Service
public class AdminService {
    private final AdminMapper adminMapper;
    private final RedisTemplate<String,String> redisTemplate;
    private final BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();

    @Autowired
    public AdminService(AdminMapper adminMapper, RedisTemplate<String, String> redisTemplate) {
        this.adminMapper = adminMapper;
        this.redisTemplate = redisTemplate;
    }

    public InvokerResult insert(Admin admin) {
        admin.setPassword(bpe.encode(admin.getPassword()));
        adminMapper.insert(admin);
        return InvokerResult.getInstance();
    }

    public InvokerResult login(Admin admin) throws BsUserException {
        Admin adminByName = adminMapper.findByName(admin.getUsername());
        if (adminByName == null) {
            throw new BsUserException(BsUserException.ErrorUserEnum.USERNAME_NON_EXISTENT);
        }
        if (!bpe.matches(admin.getPassword(), adminByName.getPassword())) {
            throw new BsUserException(BsUserException.ErrorUserEnum.USERNAME_OR_PASSWORD_ERR);
        }
        if (!adminByName.getEnable()) {
            throw new BsUserException(BsUserException.ErrorUserEnum.USER_DISABLE);
        }
        //生成jwt
        String jwt = JwtUtil.buildJWT(adminByName.getId().toString());
        AdminDTO adminDTO = new AdminDTO();
        BeanUtils.copyProperties(adminByName, adminDTO);
        adminDTO.setToken(jwt);
        return InvokerResult.getInstance(adminDTO);
    }

    public boolean checkTokenIsExpire(String token) {
        Jws<Claims> jws;
        try {
            jws = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            log.error(e.getMessage());
            return true;
        }
        return jws.getBody().getExpiration().before(DateUtil.getDate());
    }


    public boolean checkPermissions(Map<String, String> map) {
        String token = map.get("token");
        String path = map.get("path");
        Jws<Claims> jws = JwtUtil.parseJWT(token);
        String id = jws.getBody().getSubject();
        //超级管理员
        if (Objects.equals(id, "1")) {
            return false;
        }
        Long roleId = adminMapper.findRoleByUserId(Long.parseLong(id));
        String permissions = redisTemplate.opsForValue().get(roleId.toString());
        return StringUtils.isEmpty(permissions) || !permissions.contains(path);
    }

    public InvokerResult selectAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AdminDTO> adminDTOS = adminMapper.selectAll();
        return InvokerResult.getInstance(PageDataUtil.toPageData(new PageInfo<>(adminDTOS)));
    }

    public InvokerResult updateEnableState(Admin admin) {
        adminMapper.updateEnableState(admin);
        return InvokerResult.getInstance();
    }

    public InvokerResult get(Long id) {
        return InvokerResult.getInstance(adminMapper.findById(id));
    }

    public InvokerResult update(Admin admin) {
        if (StringUtils.isNotEmpty(admin.getPassword())) {
            admin.setPassword(bpe.encode(admin.getPassword()));
        }
        adminMapper.update(admin);
        return InvokerResult.getInstance();
    }

    public InvokerResult delete(Long id) {
        adminMapper.deleteById(id);
        return InvokerResult.getInstance();
    }

}
