/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.hib.service.impl;

import java.util.List;
import lk.ijse.hib.core.Customer;
import lk.ijse.hib.repo.CustomerRepo;
import lk.ijse.hib.repo.impl.CustomerRepoImpl;
import lk.ijse.hib.service.CustomerService;

/**
 *
 * @author minoli
 */
public class CustomerServiceImpl implements CustomerService{
    
    private CustomerRepo customerRepo = new CustomerRepoImpl();
    
    
    @Override
    public boolean addCustomer(Customer dto) throws Exception {
        
        return customerRepo.add(dto);
}

    @Override
    public boolean deleteCustomer(int id) throws Exception {
        return customerRepo.delete(id);
    }

    @Override
    public Customer searchCustomer(int id) throws Exception {
        return customerRepo.search(id);}

    @Override
    public boolean updateCustomer(Customer dto) throws Exception {
        return customerRepo.update(dto);}

    @Override
    public List<Customer> getCustomerList() throws Exception {
        return customerRepo.getAll();}
    
}
