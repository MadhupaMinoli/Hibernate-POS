/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.hib.Controller.impl;

import java.util.ArrayList;
import java.util.List;
import lk.ijse.hib.Controller.ItemController;
import lk.ijse.hib.core.Item;
import lk.ijse.hib.dto.ItemDTO;
import lk.ijse.hib.service.ItemService;
import lk.ijse.hib.service.impl.ItemServiceImpl;

/**
 *
 * @author minoli
 */
public class ItemControllerImpl implements ItemController{
    
    private final ItemService itemService=new ItemServiceImpl();
    
    
    @Override
    public boolean add(ItemDTO dto) throws Exception {
        
        Item item=new Item();
        item.setName(dto.getItemName());
        item.setUnitPrice(dto.getUnitPrice());
        item.setQuantity(dto.getQtyOnHand());
        
        boolean addItem =itemService.addItem(item);
            if (addItem){
                return true;
                
            }
        return false;
   }

    @Override
    public boolean delete(int id) throws Exception {
       itemService.deleteItem(id);
       return false;
}

    @Override
    public ItemDTO search(int id) throws Exception {
        Item item=itemService.searchItem(id);
        ItemDTO dto = new ItemDTO();
        dto.setItemId(item.getId());
        dto.setItemName(item.getName());
        dto.setUnitPrice(item.getUnitPrice());
        dto.setQtyOnHand(item.getQuantity());
        
        return dto;
        
   
    }

    @Override
    public boolean update(ItemDTO dto) throws Exception {
        Item item =new Item();
        item.setName(dto.getItemName());
        item.setUnitPrice(dto.getUnitPrice());
        item.setQuantity(dto.getQtyOnHand());
        item.setId(dto.getItemId());
        return itemService.updateItem(item);
    }

    @Override
    public List<ItemDTO> getAll() throws Exception {
       ArrayList<ItemDTO> allItems = new ArrayList<>();
        List<Item> allItem = itemService.getItemList();
        for (Item item : allItem) {
            ItemDTO dto = new ItemDTO();
            
            dto.setItemName(item.getName());
           dto.setUnitPrice(item.getUnitPrice());
           dto.setQtyOnHand(item.getQuantity());
           dto.setItemId(item.getId());
            allItems.add(dto);
        }
        return allItems;
    
    }
    
}
