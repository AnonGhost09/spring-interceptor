package com.jwt.dipa.controllers.interceptors;

import com.jwt.dipa.constansts.UrlMappings;
import com.jwt.dipa.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MiddlewareInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getRequestURI().contains(UrlMappings.AUTH_URL)){
            return true;
        }
        return jwtUtil.validateToken(request.getHeader(" "));
    }
}
