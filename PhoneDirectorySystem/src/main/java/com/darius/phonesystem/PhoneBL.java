package com.darius.phonesystem;

import java.util.List;

/**
 * Created by bicboi on 9/7/16.
 */
public class PhoneBL implements PhoneBLInterface{

    public void addPhoneEntry(PhoneEntryBean phoneEntryBean){
        PhoneDAO phoneDAO = new PhoneDAO();
        phoneDAO.addEntry(phoneEntryBean);
    }

    public void deletePhoneEntry(PhoneEntryBean phoneEntryBean){
        PhoneDAO phoneDAO = new PhoneDAO();
        phoneDAO.deleteEntry(phoneEntryBean);
    }

    public PhoneEntryBean getByPhoneNumber(String phoneNumber){
        PhoneDAO phoneDAO = new PhoneDAO();
        return phoneDAO.getByPhoneNumber(phoneNumber);
    }

    public List<PhoneEntryBean> getPhoneDirectory(){
        PhoneDAO phoneDAO = new PhoneDAO();
        return phoneDAO.getPhoneDirectory();
    }

    public void updatePhoneEntry(PhoneEntryBean phoneEntryBean){
        PhoneDAO phoneDAO = new PhoneDAO();
        phoneDAO.updateEntry(phoneEntryBean);
    }
}
