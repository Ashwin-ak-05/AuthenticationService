package com.example.AuthUserService.services;

import com.example.AuthUserService.exceptions.*;
import com.example.AuthUserService.models.Token;
import com.example.AuthUserService.models.User;
import com.example.AuthUserService.repositories.TokenRepository;
import com.example.AuthUserService.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private TokenRepository tokenRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, TokenRepository tokenRepository,BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository=userRepository;
        this.tokenRepository=tokenRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    public User getUserById(Long userId){
        Optional<User> optionalUser = this.userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        User savedUser = optionalUser.get();
        return savedUser;
    }

    public User signUp(String name, String email, String password){
        Optional<User> optionalUser = this.userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
            throw new UserAlreadyExistException("User Already Exist");
        }

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));
        User savedUser = this.userRepository.save(user);
        return savedUser;
    }

    public Token login(String email, String password){
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User with email "+email+" not found");
        }
        User user = optionalUser.get();

        if(!bCryptPasswordEncoder.matches(password,user.getHashedPassword())){
            throw new InvalidPassordException("Password is incorrect");
        }
        String tokenValue = RandomStringUtils.random(30);
        Token token = new Token();
        token.setValue(tokenValue);
        token.setUser(user);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,30);
        Date datePlus30Days = calendar.getTime();
        token.setExpiryDate(datePlus30Days);
        Token savedToken = tokenRepository.save(token);
        return savedToken;
    }

    public Token logout(Long userId, String tokenValue) throws UserNotFoundException, TokenAlreadyExpired {
        // do all the checks
        Optional<Token> optionalToken = tokenRepository.findByUserIdAndExpiryDateGreaterThanAndDeleted(userId, new Date(), false);
        if(optionalToken.isEmpty()){
            throw new TokenAlreadyExpired("token is already expired");
        }
        Token token = optionalToken.get();

        // do the core logic
        token.setDeleted(true);
        Token deletedToken = tokenRepository.save(token);
        return deletedToken;
    }

    public boolean validateToken(String tokenValue, Long userId) throws TokenAlreadyExpired, InvalidTokenArgument {
        Optional<Token> optionalToken = tokenRepository.findByUserIdAndExpiryDateGreaterThanAndDeleted(userId, new Date(), false);
        if(optionalToken.isEmpty()){
            throw new TokenAlreadyExpired("token is already expired");
        }
        Token tokenInDB = optionalToken.get();

        if(!tokenInDB.equals(tokenValue)){
            throw new InvalidTokenArgument("wrong token passed");
        }
        return true;
    }
}
