//application.properties파일에서 터미널에서  echo 'dayone-spring-boot-dividend-project-tutorial-jwt-secret-key' | base64
//를 쳐서 얻은 값인 ZGF5b25lLXNwcmluZy1ib290LWRpdmlkZW5kLXByb2plY3QtdHV0b3JpYWwtand0LXNlY3JldC1rZXkK를 아래처럼
// jwt.secret.key=ZGF5b25lLXNwcmluZy1ib290LWRpdmlkZW5kLXByb2plY3QtdHV0b3JpYWwtand0LXNlY3JldC1rZXkK부분을 적어넣고나서
//여기로 와라

package com.example.Marketplace.security;

import com.example.Marketplace.domain.Member;
import com.example.Marketplace.service.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TokenProvider {

    private static final long TOKEN_EXPIRED_TIME = 1000 * 60 * 6;//1hour을 의미
    private static final String KEY_ROLES = "roles";

    @Value("{spring.jwt.secret}")
    private String secretKey;
    private final MemberService memberService;


    /**
     * 토큰 생성(발급)
     *
     * @param username
     * @param roles
     * @return
     */
    public String generateToken(String username, List<Member> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put(KEY_ROLES, roles);

        var now = new Date();//토큰이 생성된 시간.
        var expiredDate = new Date(now.getTime() + TOKEN_EXPIRED_TIME);//토큰 만료시간.

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)//토큰생성시간
                .setExpiration(expiredDate)//토큰만료시간
                .signWith(SignatureAlgorithm.HS512, this.secretKey)
                .compact();

    }

    public UsernamePasswordAuthenticationToken getAuthentication(String jwt) {
        UserDetails userDetails = this.memberService.loadUserByUsername(this.getUsername(jwt));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }



    public String getUsername(String token){
        return this.parseClaims(token).getSubject();
    }

    public boolean validateToken(String token){
        if (!StringUtils.hasText(token)) return false;

        var claims = this.parseClaims(token);
        return !claims.getExpiration().before(new Date());
    }

    private Claims parseClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();

        } catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }

}
