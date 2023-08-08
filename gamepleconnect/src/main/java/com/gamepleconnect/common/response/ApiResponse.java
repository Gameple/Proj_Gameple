package com.gamepleconnect.common.response;

import lombok.Data;

import java.util.List;

@Data
public class ApiResponse {

    private int statusCode;

    private List<String> messages;

    private Object data;
}
