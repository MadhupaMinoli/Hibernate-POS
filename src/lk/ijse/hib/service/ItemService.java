/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.hib.service;

import java.util.List;
import lk.ijse.hib.core.Item;

/**
 *
 * @author minoli
 */
public interface ItemService {
    public boolean addItem(Item dto) throws Exception;
    public boolean deleteItem(int id) throws Exception;
    public Item searchItem(int id) throws Exception;
    public boolean updateItem (Item dto ) throws Exception;
    public List<Item> getItemList() throws Exception;
}
