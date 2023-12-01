package com.kalgookso.august.value;

import javax.persistence.Embeddable;

@Embeddable
public class ContactNumber {

    private String first;

    private String middle;

    private String last;

    protected ContactNumber() {
    }

    public ContactNumber(String first, String middle, String last) {
        this.first = first;
        this.middle = middle;
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public String getMiddle() {
        return middle;
    }

    public String getLast() {
        return last;
    }

    public String getValue() {
        return first + "-" + middle + "-" + last;
    }

}