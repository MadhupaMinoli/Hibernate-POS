



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.hib.repo.impl;

import java.io.Serializable;
import java.util.List;
import lk.ijse.hib.configuration.HibernateConfg;
import lk.ijse.hib.core.Item;
import lk.ijse.hib.repo.ItemRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author minoli
 */
public class ItemRepoImpl implements ItemRepo{
SessionFactory sessionFactory =HibernateConfg.getSessionFactory();

    @Override
    public boolean add(Item dto) throws Exception {
        Session openSession=sessionFactory.openSession();
        Transaction beginTransaction = openSession.beginTransaction();
        Serializable save = openSession.save(dto);
        beginTransaction.commit();
        openSession.close();
        return save!= null; }

    @Override
    public boolean delete(int id) throws Exception {
        Session openSession=sessionFactory.openSession();
        Transaction beginTransaction = openSession.beginTransaction();
        openSession.delete(search(id));
        beginTransaction.commit();
        openSession.close();
        return true;}

    @Override
    public Item search(int id) throws Exception {
        Session openSession=sessionFactory.openSession();
        Transaction beginTransaction = openSession.beginTransaction();
        Item getItem = (Item) openSession.get(Item.class, id);
        beginTransaction.commit();
        openSession.close();
        return getItem;}

    @Override
    public boolean update(Item dto) throws Exception {
        Session openSession=sessionFactory.openSession();
        Transaction beginTransaction = openSession.beginTransaction();
        openSession.update(dto);
        beginTransaction.commit();
        openSession.close();
        return true;}

    @Override
    public List<Item> getAll() throws Exception {
        Session openSession=sessionFactory.openSession();
        Transaction beginTransaction = openSession.beginTransaction();
        List itemList = openSession.createQuery("from Item item").list();
        beginTransaction.commit();
        openSession.close();
        return itemList;}
    
}
