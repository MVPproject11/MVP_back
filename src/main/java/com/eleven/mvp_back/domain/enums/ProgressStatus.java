package com.eleven.mvp_back.domain.enums;

public enum ProgressStatus {
    RESPONSE("응답"),
    WAITING("대기"),
    ACCEPTED("수락"),
    NEGOTIATING("조율요청"),
    REJECTED("거절");

    private final String displayName;

    ProgressStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
