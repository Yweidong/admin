package com.haoyong.admin.secrity.security;


import com.haoyong.admin.Enum.CommonEnum;
import com.haoyong.admin.exception.BizException;

import io.jsonwebtoken.*;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


/**
 * <p>
 * token管理
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Component
public class TokenManager {



    @Value ("${jwt.expire}")
    private long tokenExpiration;

    private String tokenSignKey = "jwt_token";



    public String createToken(String username, String userid, List<String> list) {
        String token = null;
        try {
            token = Jwts.builder()
                    .setId(userid)//JWT_ID
                    .setSubject(username)//主题
                    .claim("roleList",list)//自定义属性
                    .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                    .signWith(SignatureAlgorithm.HS256, tokenSignKey)
                    .compressWith(CompressionCodecs.GZIP).compact();
        } catch (Exception e) {

            throw new BizException(CommonEnum.SIGNATURE_NOT_MATCH);
        }

        return  token;

    }


    public Claims parseJWT(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        } catch (ExpiredJwtException e) {
            throw new BizException(CommonEnum.BODY_NOT_MATCH.getResultCode(),"token过期,请重新登录");
        } catch (Exception e) {
            throw new BizException(CommonEnum.BODY_NOT_MATCH.getResultCode(),"token解析异常");
        }
    }

    public void removeToken(String token,String username) {

        //jwttoken无需删除，客户端扔掉即可。
    }

}
