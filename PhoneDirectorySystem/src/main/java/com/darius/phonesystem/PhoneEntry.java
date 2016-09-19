package com.darius.phonesystem;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by bicboi on 9/7/16.
 */
@Entity(name = "PHONE_DIRECTORY")
public class PhoneEntry {
    private String name;
    private String address;
    @Id
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

    public PhoneEntryBean toPhoneEntryBean(PhoneEntry phoneEntry) {
        PhoneEntryBean phoneEntryBean = new PhoneEntryBean();
        phoneEntryBean.setName(phoneEntry.getName());
        phoneEntryBean.setPhoneNumber(phoneEntry.getPhoneNumber());
        phoneEntryBean.setAddress(phoneEntry.getAddress());
        return phoneEntryBean;
    }
}
