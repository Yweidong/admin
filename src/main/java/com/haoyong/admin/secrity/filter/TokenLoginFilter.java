package com.haoyong.admin.secrity.filter;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haoyong.admin.infrastructure.RedisKey;
import com.haoyong.admin.secrity.entity.SecurityUser;
import com.haoyong.admin.secrity.security.TokenManager;
import com.haoyong.admin.sys.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 登录过滤器，继承UsernamePasswordAuthenticationFilter，对用户名密码进行登录校验
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Slf4j
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {



    private AuthenticationManager authenticationManager;
    private TokenManager tokenManager;


    public TokenLoginFilter(AuthenticationManager authenticationManager, TokenManager tokenManager) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;

        this.setPostOnly(false);

        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/acl/login","POST"));
    }

    /**
     *
     * 登录后先到这个方法
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {


        try {

            User user = new ObjectMapper().readValue(req.getInputStream(), User.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 登录成功
     * @param req
     * @param res
     * @param chain
     * @param auth
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        SecurityUser user = (SecurityUser) auth.getPrincipal();
        String token = tokenManager.createToken(
                user.getCurrentUserInfo().getUsername(),
                user.getCurrentUserInfo().getId(),
                user.getPermissionValueList()

        );

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",200);
        map.put("message","登录成功");
        map.put("data",token);
        res.setContentType("application/json;charset=utf-8");
        PrintWriter out = res.getWriter();
        out.write(JSON.toJSONString(map));
        out.flush();
        out.close();

    }

    /**
     * 登录失败
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter out = response.getWriter();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",401);
        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            map.put("message","用户名或密码错误");
        } else if (e instanceof DisabledException) {
            map.put("message","账户被禁用");
        } else {
            map.put("message","登录失败!");
        }
        map.put("data","操作失败");
        out.write(JSON.toJSONString(map));
        out.flush();
        out.close();
    }
}
