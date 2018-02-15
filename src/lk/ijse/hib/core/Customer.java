/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.hib.core;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 *
 * @author minoli
 */
@Entity
public class Customer {

   
    
    private int customerId;
    private String firstName;
    private String lastName;
    private String address;
    private Set<Orders> order =new HashSet<Orders>();

    /**
     * @return the customerId
     */
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the order
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    public Set<Orders> getOrder() {
        return order;
   }

 
 /**
     * @param order the order to set
     */
    public void setOrder(Set<Orders> order) {
        this.order = order;
    }

   

}
