/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.hib.repo;

import java.util.ArrayList;
import lk.ijse.hib.core.OrderDetails;
import lk.ijse.hib.core.Orders;


/**
 *
 * @author minoli
 */
public interface OrderRepo {
    public boolean placeorder(Orders order,ArrayList<OrderDetails> array);
}
