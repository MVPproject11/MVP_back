package com.eleven.mvp_back.enums;

public enum CareGrade {
    GRADE_1("1등급"),
    GRADE_2("2등급"),
    GRADE_3("3등급"),
    GRADE_4("4등급"),
    GRADE_5("5등급"),
    Cognitive_SUPPORT("인지지원");

    private final String displayName;

    CareGrade(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
