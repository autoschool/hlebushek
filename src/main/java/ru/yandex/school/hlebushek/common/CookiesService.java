package ru.yandex.school.hlebushek.common;

import javax.servlet.http.Cookie;

public class CookiesService {

    /**
     * Method create auth cookie with user id
     * @param userId int
     * @return Cookie
     */
    public static Cookie setCookieWithUserId(int userId) {
        Cookie cookie = new Cookie("hlebushek_auth", String.valueOf(userId));
        cookie.setMaxAge(24 * 60 * 60 * 60);
        cookie.setPath("/");
        return cookie;
    }
}
