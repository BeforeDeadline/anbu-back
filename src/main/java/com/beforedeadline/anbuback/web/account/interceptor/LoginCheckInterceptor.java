package com.beforedeadline.anbuback.web.account.interceptor;

import com.beforedeadline.anbuback.domain.account.exception.LoginRequiredException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("id")==null){
            throw new LoginRequiredException();
        }
        return true;
    }
}
