package com.gamepleconnect.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
public class ApiResponse {

    @Schema(description = "상태코드" , example = "1")
    private String statusCode;

    @Schema(description = "API 응답 데이터")
    private Object data;

    @Builder
    public ApiResponse(String statusCode, Object data) {
        this.statusCode = statusCode;
        this.data = data;
    }
}
