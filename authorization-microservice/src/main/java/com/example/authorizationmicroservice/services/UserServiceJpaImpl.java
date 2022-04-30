package com.example.authorizationmicroservice.services;

import com.example.authorizationmicroservice.domain.Role;
import com.example.authorizationmicroservice.domain.User;
import com.example.authorizationmicroservice.dto.UserDto;
import com.example.authorizationmicroservice.exceptions.EmailIsBusyException;
import com.example.authorizationmicroservice.exceptions.PasswordIsIncorrectException;
import com.example.authorizationmicroservice.exceptions.UserNotFoundException;
import com.example.authorizationmicroservice.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@Service
@Transactional
public class UserServiceJpaImpl implements UserService {

    public static final long JWT_TOKEN_VALIDITY = 60 * 60;

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    public UserServiceJpaImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserDto userDto) {
        User userByEmail = userRepository.findUserByEmail(userDto.getEmail());
        if (userByEmail != null) throw new EmailIsBusyException("email is busy");

        userRepository.saveAndFlush(new User(
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()),
                Arrays.asList(new Role(Role.USER_ROLE)))
        );
    }

    @Override
    public String checkUser(UserDto userDto) {
        User userByEmail = userRepository.findUserByEmail(userDto.getEmail());
        if (userByEmail == null) throw new UserNotFoundException("user not found");

        if (!passwordEncoder.matches(userDto.getPassword(), userByEmail.getPassword()))
            throw new PasswordIsIncorrectException("password is incorrect");
        return makeTokenForUser(userByEmail);
    }

    private String makeTokenForUser(User user) {

        String token = Jwts.builder()
                .claim("id", user.getId())
                .claim("roles", Arrays.toString(user.getRoles().toArray()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();

        return token;
    }
}
