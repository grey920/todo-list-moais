package com.sample.todo.web.util;

import com.sample.todo.service.user.domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServer;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public final class SessionUtil {
    public static final String SESSION_ATTR_KEY_USER = "user";

    /**
     * 세션에 Attribute를 가져온다.
     * @param key
     * @return
     */
    public static Object getAttribute( String key ) {
        ServletRequestAttributes sr = ( ServletRequestAttributes ) RequestContextHolder.getRequestAttributes();
        HttpSession session = sr.getRequest().getSession();

        return session != null ? session.getAttribute( key ) : "";
    }

    /**
     * 세션체크
     */
    public static boolean checkSession() {

        HttpSession session = getSession();
        if( session != null && session.getAttribute( SESSION_ATTR_KEY_USER ) != null){
            return true;
        }

        return false;
    }


    public static User getUser() {
        Object user = getAttribute( SESSION_ATTR_KEY_USER );

        if ( user instanceof User ) {
            return ( User ) user;
        }

        return User.empty();
    }


    //======================= private =======================//
    private static HttpSession getSession() {

        ServletRequestAttributes sr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if ( sr == null || sr.getRequest() == null) {
            return null;
        }

        HttpSession session = sr.getRequest().getSession();
        return session;
    }

}
