package com.kalgookso.august.entity.lms;

public enum CourseType {
    COMMON("공통교양"),
    BALANCED("균형교양"),
    AUTONOMOUS("자율교양"),
    ENGINEERING("공학소양"),
    SERIES("계열교양"),
    MAJOR_REQUIRED("전공필수"),
    MAJOR_ELECTIVE("전공선택");

    private final String alias;

    CourseType(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }
}