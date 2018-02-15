/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.hib.service.impl;

import java.util.ArrayList;
import lk.ijse.hib.core.OrderDetails;
import lk.ijse.hib.core.Orders;
import lk.ijse.hib.repo.OrderRepo;
import lk.ijse.hib.repo.impl.OrderRepoImpl;
import lk.ijse.hib.service.OrderService;

/**
 *
 * @author minoli
 */
public class OrderServiceImpl implements OrderService{
    OrderRepo orderRepo =new OrderRepoImpl();
    @Override
    public boolean placeOrder(Orders order, ArrayList<OrderDetails> array) {
        orderRepo.placeorder(order, array);
        return true;
}
    
}
