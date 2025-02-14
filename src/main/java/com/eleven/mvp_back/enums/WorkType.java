package com.eleven.mvp_back.enums;

public enum WorkType {
    VISIT_CARE("방문요양"),
    NURSING_HOME("요양원"),
    RESIDENTIAL_CARE("입주요양"),
    HOSPITAL("병원"),
    VISIT_BATH("방문목욕"),
    HOSPITAL_ESCORT("병원동행"),
    DAY_NIGHT_PROTECTION("주야간보호");

    private final String description;

    WorkType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
