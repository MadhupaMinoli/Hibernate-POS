/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.hib.repo;

import java.util.List;
import lk.ijse.hib.core.Customer;

/**
 *
 * @author minoli
 */
public interface CustomerRepo {
    public boolean add(Customer dto) throws Exception;
    public boolean delete(int id) throws Exception;
    public Customer search(int id) throws Exception;
    public boolean update (Customer dto ) throws Exception;
    public List<Customer> getAll() throws Exception;
    
}
