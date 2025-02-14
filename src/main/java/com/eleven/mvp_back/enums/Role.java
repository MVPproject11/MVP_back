package com.eleven.mvp_back.enums;

public enum Role {
    CAREGIVER("요양보호사"),
    SOCIALWORKER("사회복지사");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
