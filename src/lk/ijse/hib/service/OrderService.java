/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.hib.service;

import java.util.ArrayList;
import lk.ijse.hib.core.OrderDetails;
import lk.ijse.hib.core.Orders;

/**
 *
 * @author minoli
 */
public interface OrderService {
    boolean placeOrder(Orders order,ArrayList<OrderDetails> array);
}
