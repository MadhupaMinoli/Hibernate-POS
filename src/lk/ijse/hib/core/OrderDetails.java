/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.hib.core;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Transient;

/**
 *
 * @author minoli
 */
@Entity
public class OrderDetails implements Serializable{

    
   @EmbeddedId
    private OrderId_ItemId orderIdItemId=new OrderId_ItemId();
    
    private int quantity;
    
    @MapsId("itemId")
    @ManyToOne(cascade = CascadeType.ALL)
    private Item item;
    
    @MapsId("orderId")
    @ManyToOne(cascade = CascadeType.ALL)
    private Orders order;
   
    



    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the orderIdItemId
     */
    public OrderId_ItemId getOrderIdItemId() {
        return orderIdItemId;
    }

    /**
     * @param orderIdItemId the orderIdItemId to set
     */
    public void setOrderIdItemId(OrderId_ItemId orderIdItemId) {
        this.orderIdItemId = orderIdItemId;
    }

   /**
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * @return the order
     */
    public Orders getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(Orders order) {
        this.order = order;
    }
    
    
    
}
