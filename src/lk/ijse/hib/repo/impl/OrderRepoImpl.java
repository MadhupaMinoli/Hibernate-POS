/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.hib.repo.impl;

import java.util.ArrayList;
import lk.ijse.hib.configuration.HibernateConfg;
import lk.ijse.hib.core.OrderDetails;
import lk.ijse.hib.core.Orders;
import lk.ijse.hib.core.Item;
import lk.ijse.hib.repo.ItemRepo;
import lk.ijse.hib.repo.OrderRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author minoli
 */
public class OrderRepoImpl implements OrderRepo{
    
SessionFactory sessionFactory =HibernateConfg.getSessionFactory();
ItemRepo itemRepoImpl=new ItemRepoImpl();

    @Override
    public boolean placeorder(Orders order, ArrayList<OrderDetails> array) {
       Session openSession = sessionFactory.openSession();
        Transaction transaction = openSession.beginTransaction();
        
        for (OrderDetails t : array) {
            t.getItem().setQuantity(t.getItem().getQuantity()-t.getQuantity());
            openSession.save(t);
            
        }
        transaction.commit();
        openSession.close();
    return true;
            
        
 }
    
    
}
