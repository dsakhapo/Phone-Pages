package com.darius.phonesystem;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bicboi on 9/6/16.
 */
public class PhoneDAO {

    public void addEntry(PhoneEntryBean phoneEntryBean){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        addEntry(session, phoneEntryBean);
        session.getTransaction().commit();
        session.close();
    }

    private void addEntry(Session session, PhoneEntryBean phoneEntryBean){
        PhoneEntry phoneEntry = new PhoneEntry();
        phoneEntry.setName(phoneEntryBean.getName());
        phoneEntry.setAddress(phoneEntryBean.getAddress());
        phoneEntry.setPhoneNumber(phoneEntryBean.getPhoneNumber());

        session.save(phoneEntry);
    }

    public void deleteEntry(PhoneEntryBean phoneEntryBean) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        deleteEntry(session, phoneEntryBean);
        session.getTransaction().commit();
        session.close();
    }

    private void deleteEntry(Session session, PhoneEntryBean phoneEntryBean){
        try {
            PhoneEntry phoneEntry = new PhoneEntry();
            phoneEntry.setName(phoneEntryBean.getName());
            phoneEntry.setAddress(phoneEntryBean.getAddress());
            phoneEntry.setPhoneNumber(phoneEntryBean.getPhoneNumber());

            session.delete(phoneEntry);

        }catch(Exception ex) {
            ex.printStackTrace();
            //TODO will throw a proper exception here later.
        }
    }

    public void updateEntry(PhoneEntryBean phoneEntryBean){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        updateEntry(session, phoneEntryBean);
        session.getTransaction().commit();
        session.close();
    }

    private void updateEntry(Session session, PhoneEntryBean phoneEntryBean){
        try{
            PhoneEntry phoneEntry = new PhoneEntry();
            phoneEntry.setName(phoneEntryBean.getName());
            phoneEntry.setAddress(phoneEntryBean.getAddress());
            phoneEntry.setPhoneNumber(phoneEntryBean.getPhoneNumber());

            session.update(phoneEntry);
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

    public PhoneEntryBean getByPhoneNumber(String phoneNumber) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        PhoneEntry phoneEntry = session.get(PhoneEntry.class, phoneNumber);
        PhoneEntryBean result = phoneEntry.toPhoneEntryBean(phoneEntry);
        session.close();

        return result;
    }

    public List<PhoneEntryBean> getPhoneDirectory(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from com.darius.phonesystem.PhoneEntry");
        List<PhoneEntry> entries = query.list();

        List<PhoneEntryBean> phoneDirectory = new ArrayList<PhoneEntryBean>();
        for (PhoneEntry entry : entries)
            phoneDirectory.add(entry.toPhoneEntryBean(entry));

        return phoneDirectory;

    }
}
