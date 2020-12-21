package com.codebetterlife.apimock.interceptor;

import com.codebetterlife.apimock.model.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jin Wang
 * @version 1.0
 * @date 2020-12-21 6:09 下午
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authorization");
        if (ObjectUtils.isEmpty(token)) {
            return writeResponse(response, "未携带TOKEN");
        }

        return true;
    }

    private boolean writeResponse(HttpServletResponse response, String message) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(new ObjectMapper().writeValueAsString(R.fail(1, message)));
        return false;
    }
}
