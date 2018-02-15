/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.hib.service.impl;

import java.util.List;
import lk.ijse.hib.core.Item;
import lk.ijse.hib.repo.ItemRepo;
import lk.ijse.hib.repo.impl.ItemRepoImpl;
import lk.ijse.hib.service.ItemService;

/**
 *
 * @author minoli
 */
public class ItemServiceImpl implements ItemService{
private ItemRepo itemRepo = new ItemRepoImpl();
       
    @Override
    public boolean addItem(Item dto) throws Exception {
        return itemRepo.add(dto); }

    @Override
    public boolean deleteItem(int id) throws Exception {
       return itemRepo.delete(id); }

    @Override
    public Item searchItem(int id) throws Exception {
       return itemRepo.search(id); }

    @Override
    public boolean updateItem(Item dto) throws Exception {
       return itemRepo.update(dto);}

    @Override
    public List<Item> getItemList() throws Exception {
        return itemRepo.getAll();}
    
}
