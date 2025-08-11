package com.gamepleconnect.branch.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CountryRestrictionsResponse {

    private boolean allowed;
    private List<RestrictionReason> reasons;

    public static class RestrictionReason {

        private String code;
        private String message;

        public RestrictionReason(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
