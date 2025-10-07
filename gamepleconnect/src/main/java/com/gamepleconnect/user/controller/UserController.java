package com.gamepleconnect.user.controller;

import com.gamepleconnect.common.response.ApiResponse;
import com.gamepleconnect.user.dto.request.UserSignUpRequest;
import com.gamepleconnect.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "User", description = "유저 - 유저 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입 API", description = "게임플 로컬 회원가입 API")
    @PostMapping("/sign-up")
    public ApiResponse<Void> SignUpOnLocal(@RequestBody @Valid UserSignUpRequest request) throws Exception {
        return userService.SignUpOnLocal(request);
    }

}
