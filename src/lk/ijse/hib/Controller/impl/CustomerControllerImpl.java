/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.hib.Controller.impl;

import java.util.ArrayList;
import java.util.List;
import lk.ijse.hib.Controller.CustomerController;
import lk.ijse.hib.core.Customer;
import lk.ijse.hib.dto.CustomerDTO;
import lk.ijse.hib.service.CustomerService;
import lk.ijse.hib.service.impl.CustomerServiceImpl;

/**
 *
 * @author minoli
 */
public class CustomerControllerImpl implements CustomerController{
    
    
private CustomerService customerService= new CustomerServiceImpl();

    @Override
    public boolean add(CustomerDTO dto) throws Exception {
    Customer c=new Customer();
    
    c.setAddress(dto.getAddress());
    c.setFirstName(dto.getFirstName());
    c.setLastName(dto.getLastName());
        
    boolean addCustomer =customerService.addCustomer(c);
        if (addCustomer) {
            return true;
        }
    
    return false;
    }

    @Override
    public boolean delete(int id) throws Exception {
    customerService.deleteCustomer(id);
    return false;
    
 
    }

    @Override
    public CustomerDTO search(int id) throws Exception {
        Customer customer =customerService.searchCustomer(id);
        CustomerDTO dto = new CustomerDTO();
            dto.setAddress(customer.getAddress());
            dto.setFirstName(customer.getFirstName());
            dto.setLastName(customer.getLastName());
            dto.setCustomerId(customer.getCustomerId());
        return dto;
}

    @Override
    public boolean update(CustomerDTO dto) throws Exception {
        Customer c=new Customer();
        c.setCustomerId(dto.getCustomerId());
        c.setAddress(dto.getAddress());
        c.setFirstName(dto.getFirstName());
        c.setLastName(dto.getLastName());
        return customerService.updateCustomer(c);
    
    }

    @Override
    public List<CustomerDTO> getAll() throws Exception {
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        List<Customer> allCustomer = customerService.getCustomerList();
        for (Customer customer : allCustomer) {
            CustomerDTO dto = new CustomerDTO();
            dto.setAddress(customer.getAddress());
            dto.setFirstName(customer.getFirstName());
            dto.setLastName(customer.getLastName());
            dto.setCustomerId(customer.getCustomerId());
            allCustomers.add(dto);
        }
        return allCustomers;}
    
}
