package com.haoyong.admin.secrity.filter;


import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: admin
 * @description:
 * @author: ywd
 * @contact:1371690483@qq.com
 * @create: 2020-12-19 09:08
 **/
public class CorsRequestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,DELETE,PUT");
//        response.setHeader("Access-Control-Expose-Headers","*");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,Cache-Control,Pragma,Content-Type,Authorization,token");
        response.setHeader("Access-Control-Allow-Credentials","true");
        if (request.getMethod().equals(HttpMethod.OPTIONS.name())){
            response.setStatus(HttpStatus.NO_CONTENT.value());
        }else{
            filterChain.doFilter(request,response);
        }

    }
}
