package com.eleven.mvp_back.domain.enums;

public enum MatchingStatus {
    WAITING("대기"),
    IN_PROGRESS("진행 중"),
    COMPLETED("완료");

    private final String displayName;

    MatchingStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
