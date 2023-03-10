package com.koreait.board.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.filter.OncePerRequestFilter;

import com.koreait.board.provider.TokenProvider;

// ^ Java Bean으로 등록시킨다?
@Component
public class JwtAuthentication extends OncePerRequestFilter {

    @Autowired private TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                try{
                    // ? Request Header에 있는 Bearer Token을 가져옴
                    String token = parseBearToken(request);

                    // ? token이 있는지 부터 검사 
                    
                    // ? token이 있으면 
                    if(token !=null){

                        // ! 이해 X 나중에 사용할 때 그냥 배껴서 사용 
                        // ! SPring Security 영역 
                        String sub = tokenProvider.validate(token);

                        AbstractAuthenticationToken authenticationToken = 
                            new UsernamePasswordAuthenticationToken(sub, null,AuthorityUtils.NO_AUTHORITIES);
                        authenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContext securityContext =SecurityContextHolder.createEmptyContext();
                        securityContext.setAuthentication(authenticationToken);
                        SecurityContextHolder.setContext(securityContext);
                    }

                }catch(Exception exception){
                    exception.printStackTrace();
                }

                filterChain.doFilter(request, response);
        
        
    }

    private String parseBearToken(HttpServletRequest request){
        // ? Request Header의 Authorization 필드의 Value를 가져옴 
        String authorizationValue = request.getHeader("Authorization");

        // ^ 큰 흐름에서 벗어나는게 걸러져야한다 
        // ? Authorization Value에 문자가 포함되어있는지 
        boolean hasTokenValue = StringUtils.hasText(authorizationValue);
        if(!hasTokenValue) return null;

        // ^ if문에서 걸리는게 null을 return 하는게 흐름상 맞아 

        // ? Authorization Value가 Bearer로 시작되는지 
        boolean isBearer = authorizationValue.startsWith("Bearer ");
        if(isBearer) return null;

        
        
        
        
        // ^ if 문에 걸리지 않는게 주된 흐름으로 
        // ? "Bearer" 다음에 오는 문자열(Token)을 추출
        String token =  authorizationValue.substring(7);
        return token;

    }
    
}
