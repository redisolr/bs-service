package com.qtxln.util;

import com.qtxln.exception.BsUserException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.commons.lang3.StringUtils;

/**
 * @author QT
 * 2018-08-27 15:36
 */
public class TokenUtil {
    private TokenUtil() {
    }

    public static Long getUserId(String token) throws BsUserException {
        if (StringUtils.isEmpty(token)) {
            throw new BsUserException(BsUserException.ErrorUserEnum.TOKEN_EXPIRED);
        } else {
            Jws<Claims> jws = JwtUtil.parseJWT(token);
            if (jws.getBody().getExpiration().before(DateUtil.getDate())) {
                throw new BsUserException(BsUserException.ErrorUserEnum.TOKEN_EXPIRED);
            }
        }
        Jws<Claims> jws = JwtUtil.parseJWT(token);
        return Long.parseLong(jws.getBody().getSubject());
    }
}
