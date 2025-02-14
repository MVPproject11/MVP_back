package com.eleven.mvp_back.domain.enums;

public enum Gender {
    M("남자"),
    F("여자");

    private final String displayName;

    Gender(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
