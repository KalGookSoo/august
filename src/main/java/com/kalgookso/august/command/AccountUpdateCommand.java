package com.kalgookso.august.command;

import com.kalgookso.august.value.ContactNumber;
import com.kalgookso.august.value.Email;

import javax.validation.constraints.NotBlank;

/**
 * 계정 업데이트 명령에 대한 클래스입니다.
 */
public class AccountUpdateCommand {

    @NotBlank
    private String name;

    private Email email;

    private ContactNumber contactNumber;

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