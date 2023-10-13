package com.gamepleconnect.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApiResponse implements Serializable {

    @Schema(description = "서버 정의 상태코드" , example = "1")
    private String statusCode;

    @Schema(description = "서버 정의 메세지" , example = "1")
    private String message;

    @Schema(description = "데이터")
    private Object data;

    @Builder
    public ApiResponse(String statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
}
