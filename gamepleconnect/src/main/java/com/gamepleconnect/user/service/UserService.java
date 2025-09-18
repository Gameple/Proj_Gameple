package com.gamepleconnect.user.service;

import com.gamepleconnect.common.code.StatusCode;
import com.gamepleconnect.common.response.ApiResponse;
import com.gamepleconnect.exception.common.DuplicatedEmailException;
import com.gamepleconnect.user.domain.SignUpType;
import com.gamepleconnect.user.domain.User;
import com.gamepleconnect.user.dto.request.UserSignUpRequest;
import com.gamepleconnect.user.exception.DuplicatedUserNameException;
import com.gamepleconnect.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public ApiResponse SignUpOnLocal(UserSignUpRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicatedEmailException();
        }

        if (userRepository.existsByUserName(request.getUserName())) {
            throw new DuplicatedUserNameException();
        }

        User user = User.builder()
                .email(request.getEmail())
                .userName(request.getUserName())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .signupType(SignUpType.LOCAL)
                .profileImageUrl(request.getProfileImageUrl())
                .build();

        userRepository.save(user);

        return ApiResponse.builder()
                .statusCode(StatusCode.SUCCESS.getStatusCode())
                .message(StatusCode.SUCCESS.getMessage())
                .data(null)
                .build();
    }
}
