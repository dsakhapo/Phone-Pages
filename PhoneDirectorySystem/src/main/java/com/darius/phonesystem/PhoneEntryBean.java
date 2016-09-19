package com.darius.phonesystem;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by bicboi on 9/5/16.
 */

@XmlRootElement
public class PhoneEntryBean {
    private String name;
    private String address;
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
