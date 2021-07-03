package com.example.crud.interceptor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@Component
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = (request.getQueryString() != null) ? String.join("", request.getRequestURI(),"?" ,request.getQueryString()) : request.getRequestURI() ;
        log.info("request : {} {}", request.getMethod(), requestUri);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("response : {}", response.getStatus());
    }
}
