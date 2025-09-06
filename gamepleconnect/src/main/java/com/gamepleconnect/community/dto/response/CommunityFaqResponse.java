package com.gamepleconnect.community.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class CommunityFaqResponse {

    private Long categoryId;

    private String categoryName;

    private Long faqId;

    private String question;

    private String answer;

    private Date createdDate;

    private Date updatedDate;
}
