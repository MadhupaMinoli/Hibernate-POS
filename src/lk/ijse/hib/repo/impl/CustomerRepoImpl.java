/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.hib.repo.impl;

import java.io.Serializable;
import java.util.List;
import lk.ijse.hib.configuration.HibernateConfg;
import lk.ijse.hib.core.Customer;
import lk.ijse.hib.repo.CustomerRepo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author minoli
 */
public class CustomerRepoImpl implements CustomerRepo{
    
    SessionFactory sessionFactory =HibernateConfg.getSessionFactory();
    
   

    @Override
    public boolean add(Customer dto) throws Exception {
        
        Session openSession=sessionFactory.openSession(); 
        Transaction beginTransaction = openSession.beginTransaction();
        Serializable save = openSession.save(dto);
        beginTransaction.commit();
        openSession.close();
        return save!= null;
    
    }

    @Override
    public boolean update(Customer dto) throws Exception {
        Session openSession=sessionFactory.openSession();
        Transaction beginTransaction = openSession.beginTransaction();
        openSession.update(dto);
        beginTransaction.commit();
        openSession.close();
        return true;
        
    }
    @Override
    public List<Customer> getAll() throws Exception {
        Session openSession=sessionFactory.openSession();
        Transaction beginTransaction = openSession.beginTransaction();
        List customerList = openSession.createQuery("from Customer customer").list();
        beginTransaction.commit();
        openSession.close();
        return customerList;
        }

    @Override
    public boolean delete(int id) throws Exception {
        Session openSession=sessionFactory.openSession();
        Transaction beginTransaction = openSession.beginTransaction();
        openSession.delete(search(id));
        beginTransaction.commit();
        openSession.close();
        return true;}

    @Override
    public Customer search(int id) throws Exception {
        Session openSession=sessionFactory.openSession();
        Transaction beginTransaction = openSession.beginTransaction();
        Customer getCustomer = (Customer) openSession.get(Customer.class, id);
        beginTransaction.commit();
        openSession.close();
        return getCustomer;}
    
}
