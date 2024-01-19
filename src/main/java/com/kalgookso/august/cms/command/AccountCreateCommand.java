package com.kalgookso.august.cms.command;

import com.kalgookso.august.cms.value.ContactNumber;
import com.kalgookso.august.cms.value.Email;

import javax.validation.constraints.NotBlank;

/**
 * 계정 생성 명령에 대한 클래스입니다.
 * 이 클래스는 계정명, 패스워드, 이름을 필드로 가지고 있습니다.
 * 각 필드는 NotBlank 제약 조건이 적용되어 있습니다.
 */
public class AccountCreateCommand {

    @NotBlank
    private String username;  // 계정명 필드

    @NotBlank
    private String password;  // 패스워드 필드

    @NotBlank
    private String name;  // 이름 필드

    private Email email;

    private ContactNumber contactNumber;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public ContactNumber getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(ContactNumber contactNumber) {
        this.contactNumber = contactNumber;
    }

}