package com.example.AuthUserService.repositories;

import com.example.AuthUserService.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Token save(Token token);
    Optional<Token> findByUserIdAndExpiryDateGreaterThanAndDeleted(Long userId,  Date currentDate, boolean isDeleted);
}


