/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.hib.Controller;

import java.util.List;

import lk.ijse.hib.dto.CustomerDTO;

/**
 *
 * @author minoli
 */
public interface CustomerController {
    public boolean add(CustomerDTO dto) throws Exception;
    public boolean delete(int id) throws Exception;
    public CustomerDTO search(int id) throws Exception;
    public boolean update (CustomerDTO dto ) throws Exception;
    public List<CustomerDTO> getAll() throws Exception;
}
