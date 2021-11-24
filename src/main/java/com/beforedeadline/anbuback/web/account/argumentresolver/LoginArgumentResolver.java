package com.beforedeadline.anbuback.web.account.argumentresolver;

import com.beforedeadline.anbuback.domain.account.Account;
import com.beforedeadline.anbuback.domain.account.AccountService;
import com.beforedeadline.anbuback.web.account.annotation.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {

    private final AccountService accountService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        boolean isAccount = parameter.getParameterType().isAssignableFrom(Account.class);
        return hasLoginAnnotation && isAccount;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("id")==null){
            return Optional.empty();
        }

        return accountService.findById((Long) session.getAttribute("id")).orElseThrow(() -> new IllegalArgumentException("로그인이 되어있지 않습니다"));
    }
}
