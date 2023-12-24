package com.zwanan.utils;

import javax.servlet.http.HttpSession;

public class AuthUtil {

    /**
     * 判断是否登陆上
     * @param session
     * @return
     */
    public static Boolean isAuthorize(HttpSession session) {
        return null != session.getAttribute("curUser");
    }

}
