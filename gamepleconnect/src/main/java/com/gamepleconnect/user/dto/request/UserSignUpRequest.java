package com.gamepleconnect.user.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserSignUpRequest {

    @NotBlank()
    private String email;

    @NotBlank()
    private String userName;

    @NotBlank()
    private String password;

    private String profileImageUrl;

}
