// constants안에 Authority를 만들어서 간단한 회원관리 기능을 만들고,
// 회원관리 관련 부분은 security패키지 안에서 다 관리해 주겠다.
//model 안에 MemberEntity를 만들어서, 회원가입을 했을때 MemberEntity를 기준으로 해서, 데이터베이스를 저장할 수 있게 되고, 로그인 할때도 MemberEntity에서 데이터를 가져올 수 있다.
//MemberEntity로 어떤 데이터를 관리할지는 우리가 정하면됨.
// 복잡한 기능보다는 간단하게 가기위해 MemberEntity에서 id(@ID)를 만들어주고,

//이걸 다구현 한다음에는 --> SecurityConfiguration을 구현해줘야 함.

package com.example.Marketplace.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = this.resolveTokenFromRequest(request);
        //토큰 유효성 검증
        if (StringUtils.hasText(token) && this.tokenProvider.validateToken(token)) {
            UsernamePasswordAuthenticationToken auth = this.tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);

            log.info(String.format("[%s] -> %s", this.tokenProvider.getUsername(token), request.getRequestURI()));


            filterChain.doFilter(request, response);
        }

    }
    private String resolveTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);

        if (!ObjectUtils.isEmpty(token)&& token.startsWith(TOKEN_PREFIX)){//토큰이 있으면
            return token.substring(TOKEN_PREFIX.length());
        }
        return  null;//없을경우에는 null반환
    }
}