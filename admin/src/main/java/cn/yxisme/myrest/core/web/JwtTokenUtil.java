package cn.yxisme.myrest.core.web;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangxiong on 2020/1/6.
 */
@Component
public class JwtTokenUtil extends GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    private static final String CLAIM_KEY_USERNAME = "sub";

    // 5天（毫秒）
    private static final long EXPIRATION_TIME = 432000000;

    // JWT密码
    private static final String SECRET = "secret";

    /**
     * 签发JWT
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> cliaims = new HashMap<>(16);
        cliaims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        return Jwts.builder()
                .setClaims(cliaims)
                .setExpiration(new Date(Instant.now().toEpochMilli() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * 验证JWT
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * 获取token是否过期
     * @param token
     * @return
     */
    public boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 获取token过期时间
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    /**
     * 根据Token获取username
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    /**
     * 解析JWT
     * @param token
     * @return
     */
    public Claims getClaimsFromToken(String token) {
        Claims claims;

//        try {
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
//        } catch (ExpiredJwtException e) {
//            claims = e.getClaims();
//        }

        return claims;
    }
}
