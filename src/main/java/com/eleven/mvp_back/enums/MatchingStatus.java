package com.eleven.mvp_back.enums;

public enum MatchingStatus {
    REQUESTED("요청상태"),
    NEGOTIATING("협상 중"),
    COMPLETED("완료"),
    FAILED("실패");

    private final String displayName;

    MatchingStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
