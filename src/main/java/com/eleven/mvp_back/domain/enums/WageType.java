package com.eleven.mvp_back.domain.enums;

public enum WageType {
    HOURLY("시급"),
    DAILY("일급"),
    MONTHLY("월급"),
    YEARLY("연봉"),
    PER_TASK("건당");

    private final String displayName;

    WageType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
