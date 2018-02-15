/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.hib.repo;

import java.util.List;

import lk.ijse.hib.core.Item;

/**
 *
 * @author minoli
 */
public interface ItemRepo {
    public boolean add(Item dto) throws Exception;
    public boolean delete(int id) throws Exception;
    public Item search(int id) throws Exception;
    public boolean update (Item dto ) throws Exception;
    public List<Item> getAll() throws Exception;
}
