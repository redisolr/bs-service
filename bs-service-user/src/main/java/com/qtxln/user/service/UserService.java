package com.qtxln.user.service;

import com.qtxln.exception.BsUserException;
import com.qtxln.model.user.User;
import com.qtxln.model.user.dto.LoginRegisterDTO;
import com.qtxln.model.user.dto.UserDTO;
import com.qtxln.transport.InvokerResult;
import com.qtxln.user.mapper.UserMapper;
import com.qtxln.util.DateUtil;
import com.qtxln.util.JwtUtil;
import com.qtxln.util.TokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

    public InvokerResult checkLogin(String token) throws BsUserException {
        Long userId = TokenUtil.getUserId(token);
        User user = userMapper.findById(userId);
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

    public InvokerResult login(LoginRegisterDTO loginRegisterDTO) throws BsUserException {
        User byUsername = userMapper.findByUsername(loginRegisterDTO.getUsername());
        if (byUsername == null) {
            throw new BsUserException(BsUserException.ErrorUserEnum.USERNAME_NON_EXISTENT);
        }
        if (!bpe.matches(loginRegisterDTO.getPassword(), byUsername.getPassword())) {
            throw new BsUserException(BsUserException.ErrorUserEnum.USERNAME_OR_PASSWORD_ERR);
        }
        //生成jwt
        String jwt = JwtUtil.buildJWT(byUsername.getId().toString());
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(byUsername, userDTO);
        userDTO.setToken(jwt);
        return InvokerResult.getInstance(userDTO);
    }
}
