package com.hoodoo.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoodoo.board.entity.UserEntity;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity,String>{ //^ 누구의 Repository인지 , 그다음에는 PK의 타입이 뭔지 

    // ^ 함수 이름을 규칙에 따라 쓰면 자동으로 내용까지 작성이 된다 
    public boolean existsByEmail(String email);
    public boolean existsByNickname(String nickname);
    public boolean existsByTelNumber(String telNumber);

    public boolean existsByEmailOrNicknameOrTelNumber(String email,String nickname,String telNumber);

    public UserEntity findByEmail(String email);


    
}
