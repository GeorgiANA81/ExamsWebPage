package com.exams.backend.service;

import com.exams.backend.dto.UserPasswordDataDTO;
import com.exams.backend.entity.UserEntity;
import com.exams.backend.exception.CustomException;
import com.exams.backend.repo.UserRepository;
import com.exams.backend.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    private AuthenticationManager authenticationManager;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setJwtTokenProvider(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public UserEntity createOrUpdate(UserEntity student) {
        return userRepository.save(student);
    }

    public UserEntity getOneById(long id) {
        return userRepository.getOne(id);
    }

    public UserEntity getOneByEmail(String email) {
        return userRepository.getOneByEmail(email);
    }

    public UserEntity signin(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            UserEntity user = userRepository.getOneByEmail(email);
            String token = jwtTokenProvider.createToken(email, user.getRoles());

            user.setToken(token);

            return user;
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signup(UserEntity user) {
        if (!userRepository.existsByEmail(user.getEmail())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return jwtTokenProvider.createToken(user.getEmail(), user.getRoles());
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public UserEntity changePassword(UserPasswordDataDTO userPasswordDataDTO) {
        if (!userRepository.existsByEmail(userPasswordDataDTO.getEmail())) {
            throw new CustomException("Username not found", HttpStatus.NOT_FOUND);
        }

        UserEntity user = userRepository.getOneByEmail(userPasswordDataDTO.getEmail());
        if (!passwordEncoder.matches(userPasswordDataDTO.getCurrentPassword(), user.getPassword())) {
            throw new CustomException("Invalid password provided.", HttpStatus.NOT_FOUND);
        }

        user.setPassword(passwordEncoder.encode(userPasswordDataDTO.getNewPassword()));
        return userRepository.save(user);
    }
}
