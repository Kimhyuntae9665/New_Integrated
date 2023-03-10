package com.koreait.board.provider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenProvider {

    public String create(){
        // ^ 현재 시간을 Date 타입으로 바꿔준다 
        // ^ 현재 시간에 1시간 더해진 시간이 expireDate에 저장 된다 
        Date expireDate = Date.from(Instant.now().plus(1,ChronoUnit.HOURS));
        // ^ 줄이 끄어지는 건 조만간 없어진다는 뜻 (1시간 뒤에 삭제 된다 )

        String jwt = 
                    //  ? Jwts 클래스를 이용해서 JWT 빌드 (생성)
                    Jwts.builder()
                    // ? 암호화 알고리즘,암호화 키 
                    .signWith(SignatureAlgorithm.HS256, "Securekey")
                    // ? jwt sub의 값을 지정  
                    .setSubject("id")
                    // ? jwt iat의 값 지정 (생성 시간)
                    .setIssuedAt(new Date())
                    // ? jwt exp의 값 지정 (만료 시간)
                    .setExpiration(expireDate)
                    // ? 암호화 알고리즘과 암호화 키를 이용해서 지정한 값들을 토큰으로 변형
                    .compact();


    return jwt;
        
        
    }

    public String validate(String jwt){

        // ? 매개변수로 받은 jwt가 소유하고있는 Securekey를 사용해서 복호화(Decoding)
        Claims claims = Jwts
        .parser()
        .setSigningKey("Securekey")
        .parseClaimsJws(jwt)
        .getBody();
        
    return claims.getSubject();
    }
    
}
