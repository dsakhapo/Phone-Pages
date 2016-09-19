package com.darius.phonesystem;

import java.util.List;

/**
 * Created by bicboi on 9/13/16.
 */
public interface PhoneBLInterface {
    public void addPhoneEntry(PhoneEntryBean phoneEntryBean);

    public void deletePhoneEntry(PhoneEntryBean phoneEntryBean);

    public PhoneEntryBean getByPhoneNumber(String phoneNumber);

    public List<PhoneEntryBean> getPhoneDirectory();

    public void updatePhoneEntry(PhoneEntryBean phoneEntryBean);
}
