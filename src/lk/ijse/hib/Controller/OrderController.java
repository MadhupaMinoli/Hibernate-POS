/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.hib.Controller;

import java.util.ArrayList;
import lk.ijse.hib.dto.OrderDTO;
import lk.ijse.hib.dto.OrderDetailsDTO;


/**
 *
 * @author minoli
 */
public interface OrderController {
    public boolean placeorder(OrderDTO orderDto,ArrayList<OrderDetailsDTO> array);
}
