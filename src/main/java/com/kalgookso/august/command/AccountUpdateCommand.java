package com.kalgookso.august.command;

import javax.validation.constraints.NotBlank;

/**
 * 계정 업데이트 명령에 대한 클래스입니다.
 */
public class AccountUpdateCommand {

    @NotBlank
    private String name;

    private String emailId;

    private String emailDomain;

    private String firstContactNumber;

    private String middleContactNumber;

    private String lastContactNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEmailDomain() {
        return emailDomain;
    }

    public void setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
    }

    public String getFirstContactNumber() {
        return firstContactNumber;
    }

    public void setFirstContactNumber(String firstContactNumber) {
        this.firstContactNumber = firstContactNumber;
    }

    public String getMiddleContactNumber() {
        return middleContactNumber;
    }

    public void setMiddleContactNumber(String middleContactNumber) {
        this.middleContactNumber = middleContactNumber;
    }

    public String getLastContactNumber() {
        return lastContactNumber;
    }

    public void setLastContactNumber(String lastContactNumber) {
        this.lastContactNumber = lastContactNumber;
    }

}