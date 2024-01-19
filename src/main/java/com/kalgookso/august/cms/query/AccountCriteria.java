package com.kalgookso.august.cms.query;

public class AccountCriteria {

    private final String username;

    private final String name;

    private final String emailId;

    private final String contactNumber;

    public AccountCriteria(String username, String name, String emailId, String contactNumber) {
        this.username = username;
        this.name = name;
        this.emailId = emailId;
        this.contactNumber = contactNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getContactNumber() {
        return contactNumber;
    }

}