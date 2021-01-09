package com.suyang.filter;

import com.suyang.Constants;
import com.suyang.TokenUtils;
import com.suyang.service.TokenService;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description 过滤器处理所有HTTP请求，并检查是否存在带有正确令牌的Authorization标头。例如，如果令牌未过期或签名密钥正确。
 * @date: 2021/1/2 11:34 上午 <br>
 * @author: suyang <br>
 * version: 1.0 <br>
 */
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private TokenService tokenService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, TokenService tokenService) {
        super(authenticationManager);
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(Constants.TOKEN_HEADER);
        if (token == null || !token.startsWith(Constants.TOKEN_PREFIX)) {
            SecurityContextHolder.clearContext();
            chain.doFilter(request, response);
            return;
        }
        String tokenValue = token.substring(Constants.TOKEN_PREFIX.length()).trim();
        UsernamePasswordAuthenticationToken authentication = null;
        try {
            String prevToken = tokenService.get(TokenUtils.getId(tokenValue));
            if (!token.equals(prevToken)) {
                SecurityContextHolder.clearContext();
                chain.doFilter(request, response);
                return;
            }
            authentication = TokenUtils.getAuthentication(tokenValue);
        } catch (JwtException e) {
            log.error(e.getMessage(), e);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}
