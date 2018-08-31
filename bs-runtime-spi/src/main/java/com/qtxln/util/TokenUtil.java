package com.qtxln.util;

import com.qtxln.exception.BsUserException;
import com.qtxln.model.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * @author QT
 * 2018-08-27 15:36
 */
public class TokenUtil {
    private TokenUtil() {
    }

    public static User getUserId(String token) throws BsUserException, IOException {
        if (StringUtils.isEmpty(token)) {
            throw new BsUserException(BsUserException.ErrorUserEnum.TOKEN_EXPIRED);
        } else {
            Jws<Claims> jws = JwtUtil.parseJWT(token);
            if (jws.getBody().getExpiration().before(DateUtil.getDate())) {
                throw new BsUserException(BsUserException.ErrorUserEnum.TOKEN_EXPIRED);
            }
        }
        Jws<Claims> jws = JwtUtil.parseJWT(token);
        return JsonUtil.jsonToObj(jws.getBody().getSubject(), User.class);
    }
}
