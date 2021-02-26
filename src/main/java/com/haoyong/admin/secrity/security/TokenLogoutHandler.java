package com.haoyong.admin.secrity.security;


import com.alibaba.fastjson.JSON;
import com.haoyong.admin.infrastructure.RedisKey;
import com.haoyong.admin.secrity.entity.SecurityUser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 登出业务逻辑类
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Slf4j
public class TokenLogoutHandler implements LogoutHandler {

    private TokenManager tokenManager;


    public TokenLogoutHandler(TokenManager tokenManager) {
        this.tokenManager = tokenManager;

    }


    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = request.getHeader("token");

        if (token != null) {
//            if (authentication != null) {
//                String username = (String) authentication.getPrincipal();
//                tokenManager.removeToken(token,username);
//            }
            //清空当前用户缓存中的权限数据

            Map<String,Object> map = new HashMap<String,Object>();
            map.put("code",200);
            map.put("message","退出成功");
            map.put("data",authentication);
            response.setContentType("application/json;charset=utf-8");
            response.setHeader("Access-Control-Allow-Origin","*");
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.write(JSON.toJSONString(map));
            out.flush();
            out.close();
        }

    }

}
