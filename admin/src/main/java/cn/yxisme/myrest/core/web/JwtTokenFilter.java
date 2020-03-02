package cn.yxisme.myrest.core.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yangxiong on 2020/1/6.
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    //存放Token的Header Key
    public static final String HEADER_STRING = "Authorization";

    private static final String TOKEN_PREFIX = "Bearer";

    private static final String SPACE = " ";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
            String token = parseToken(request);

            if (!StringUtils.isEmpty(token)) {
                String username = jwtTokenUtil.getUsernameFromToken(token);
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                    if (jwtTokenUtil.validateToken(token, userDetails)) {
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }

            chain.doFilter(request, response);
    }

    private String parseToken(HttpServletRequest request) {
        String authorization = request.getHeader(HEADER_STRING);

        if (StringUtils.isEmpty(authorization) || authorization.startsWith(TOKEN_PREFIX + SPACE)) {
            return null;
        }

        String token = authorization.split(SPACE)[1];
        if (StringUtils.isEmpty(token)) {
            return null;
        }

        return token;
    }
}
