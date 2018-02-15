/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.hib.Controller.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.ijse.hib.Controller.OrderController;
import lk.ijse.hib.core.OrderDetails;
import lk.ijse.hib.core.Orders;
import lk.ijse.hib.dto.OrderDTO;
import lk.ijse.hib.dto.OrderDetailsDTO;
import lk.ijse.hib.service.CustomerService;
import lk.ijse.hib.service.ItemService;
import lk.ijse.hib.service.OrderService;
import lk.ijse.hib.service.impl.CustomerServiceImpl;
import lk.ijse.hib.service.impl.ItemServiceImpl;
import lk.ijse.hib.service.impl.OrderServiceImpl;

/**
 *
 * @author minoli
 */
public class OrderControllerImpl implements OrderController{
    OrderService orderService =new OrderServiceImpl();
    ItemService itemService=new ItemServiceImpl();
    CustomerService customerService=new CustomerServiceImpl();

    @Override
    public boolean placeorder(OrderDTO orderDto, ArrayList<OrderDetailsDTO> array) {
        
        
     try {
            Orders orders=new Orders();
            orders.setTotal(orderDto.getTotal());
            orders.setDate(orderDto.getDate());
            orders.setCustomer(customerService.searchCustomer(orderDto.getCustomerId()));
            
            ArrayList<OrderDetails> ordersArray=new ArrayList<>();
            
            array.forEach((t) -> {
               
                try {
                    OrderDetails od=new OrderDetails();
                    
                   od.setItem(itemService.searchItem(t.getItemId()));
                    od.setOrder(orders);
                    od.setQuantity(t.getQuantity());
                    ordersArray.add(od);
                } catch (Exception ex) {
                    Logger.getLogger(OrderControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            });
            
            
            orderService.placeOrder(orders, ordersArray);
            return false;
        } catch (Exception ex) {
            Logger.getLogger(OrderControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
        return false;
       
    }
    
}
