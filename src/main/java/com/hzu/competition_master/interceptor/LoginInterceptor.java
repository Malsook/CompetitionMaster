package com.hzu.competition_master.interceptor;

import com.hzu.competition_master.util.Const;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute(Const.USERTYPE) == null || request.getSession().getAttribute(Const.USERTYPE) == ""){
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        return true;
    }
}
