/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.hib.Controller;

import java.util.List;
import lk.ijse.hib.dto.ItemDTO;

/**
 *
 * @author minoli
 */
public interface ItemController {
     public boolean add(ItemDTO dto) throws Exception;
    public boolean delete(int id) throws Exception;
    public ItemDTO search(int id) throws Exception;
    public boolean update (ItemDTO dto ) throws Exception;
    public List<ItemDTO> getAll() throws Exception;
}
