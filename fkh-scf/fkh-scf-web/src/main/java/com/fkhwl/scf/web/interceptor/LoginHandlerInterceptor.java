package com.fkhwl.scf.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
//TODO-LA: 登陆拦截器必要逻辑未添加
//        String contextPath = request.getContextPath();
//        String systemEnv = EnvironmentEnum.PROFILE.getValue();
//        if (isProdProfile(systemEnv)) {
//            contextPath = "";
//        } else {
//            contextPath = "/pre_earthwork";
//        }
//
//        if (path.matches(FkhAutoConfiguration.Constant.NO_INTERCEPTOR_PATH)) {
//            return true;
//        } else {
//            Object sessionUser = request.getSession().getAttribute(Views.SESSION_AUTH_USER_KEY);
//            if (sessionUser != null) {
//                //普通用户不能使用后台管理的所有地址
//                User authUser=(User) sessionUser;
//                DataValidateException.notNull(authUser, RespCode.USER_NOT_EXISTS);
//                if (authUser.getUserType() == 2) {//普通用户
//                    if (path.matches(Constant.MABANER_OPT_PATH)) {
//                        response.sendRedirect(contextPath + "/backLogin.html");
//                        return false;
//                    }
//                }
                return true;
//            } else {
//                if (isAjax(request)) { //is ajax
//                    response.getWriter().print(Constant.AJAX_RESULT_TIMEOUT);
//                } else { //is normal
//                    if (path.startsWith("/back")) {//跳转到后台管理登录界面
//                        response.sendRedirect(contextPath + "/backLogin.html");
//                    } else {//跳转到前台登录界面
//                        response.sendRedirect(contextPath + "/login.html");
//                    }
//                }
//                return false;
//            }
//        }
    }

    private boolean isAjax(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        return header != null && "XMLHttpRequest".equals(header) ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * 判断是否是费正式环境
     *
     * @param systemEnv
     * @return
     */
    private boolean isProdProfile(String systemEnv) {
//        if (StringUtils.isBlank(systemEnv) || !"prod".equalsIgnoreCase(systemEnv)) {
//            return false;
//        }
        return true;
    }

}
