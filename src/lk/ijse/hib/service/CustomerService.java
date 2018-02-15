/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.hib.service;

import java.util.List;
import lk.ijse.hib.core.Customer;

/**
 *
 * @author minoli
 */
public interface CustomerService {
    public boolean addCustomer(Customer dto) throws Exception;
    public boolean deleteCustomer(int id) throws Exception;
    public Customer searchCustomer(int id) throws Exception;
    public boolean updateCustomer (Customer dto ) throws Exception;
    public List<Customer> getCustomerList() throws Exception;
}
