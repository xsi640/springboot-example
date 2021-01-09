package com.suyang;

/**
 * description: Constants <br>
 * date: 2021/1/2 10:57 上午 <br>
 * author: suyang <br>
 * version: 1.0 <br>
 */
public class Constants {
    public static final String ROLE_CLAIMS = "role";
    public static final Long EXPIRATION = 60 * 60 * 1000L;
    public static final String JWT_SECRET_KEY = "C*F-JaNdRgUkXn2r5u8x/A?D(G+KbPeShVmYq3s6v9y$B&E)H@McQfTjWnZr4u7w";
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";

    public static final String LOGIN_WHITELIST = "/api/auth/login";

    public static final String FILTER_ALL = "/api/**";
}
