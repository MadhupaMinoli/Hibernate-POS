/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.hib.view.controller;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hib.Controller.impl.CustomerControllerImpl;
import lk.ijse.hib.core.Customer;
import lk.ijse.hib.dto.CustomerDTO;
import lk.ijse.hib.service.CustomerService;
import lk.ijse.hib.view.ui.tblmodel.CustomerTM;

/**
 * FXML Controller class
 *
 * @author minoli
 */
public class CustomerViewController implements Initializable {

    @FXML
    private Button btnSearch;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<CustomerTM> tblCustomer;
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtFirstName;
    @FXML
    private JFXTextField txtLastName;
    private boolean update;
    private CustomerControllerImpl customerController=new CustomerControllerImpl();
    /**
     * Initializes the controller class.
     */
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tblCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        
        
        try {
            ArrayList<CustomerDTO> customer=(ArrayList<CustomerDTO>) customerController.getAll();
            ArrayList<CustomerTM> customertm=new ArrayList<CustomerTM>();
            
            customer.forEach((dto) -> {
                System.out.println(dto);
                customertm.add(new CustomerTM(String.valueOf(dto.getCustomerId()),dto.getFirstName(),dto.getLastName(),dto.getAddress()));
            });
            
            ObservableList<CustomerTM> allcustomer =FXCollections.observableArrayList(customertm);
            
            tblCustomer.setItems(allcustomer);
            
            
        } catch (Exception ex) {
            Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            tblCustomer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustomerTM>() {
            @Override
            public void changed(ObservableValue<? extends CustomerTM> observable, CustomerTM oldValue, CustomerTM newValue) {
                CustomerTM customer = observable.getValue();
                
                if (customer == null){
                     update = false;
                    return;
                }
                update = true;
                btnDelete.setDisable(!update);
                txtId.setText(customer.getCustomerId());
                txtFirstName.setText(customer.getFirstName());
                txtLastName.setText(customer.getLastName());
                txtAddress.setText(customer.getAddress());
            }
            
        });         
    }    
    
    private void loadAll(){
  
        try {
            ArrayList<CustomerDTO> customer=(ArrayList<CustomerDTO>) customerController.getAll();
            ArrayList<CustomerTM> customertm=new ArrayList<CustomerTM>();
            
            customer.forEach((dto) -> {
                System.out.println(dto);
                customertm.add(new CustomerTM(String.valueOf(dto.getCustomerId()),dto.getFirstName(),dto.getLastName(),dto.getAddress()));
            });
            
            ObservableList<CustomerTM> allcustomer =FXCollections.observableArrayList(customertm);
            
            tblCustomer.setItems(allcustomer);
        } catch (Exception ex) {
            Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    
    }
    private void clearAllTextFileds(){
        txtId.setText("");
        txtFirstName.setText("");
        txtAddress.setText("");
        txtLastName.setText("");
    }

    @FXML
    private void btnSearchMouseClicked(ActionEvent event) {
        CustomerDTO customer;
       
        try { 
            customer = customerController.search(Integer.parseInt(txtId.getText()));
            txtFirstName.setText(customer.getFirstName());
            txtLastName.setText(customer.getLastName());
            txtAddress.setText(customer.getAddress());
            
            
        } catch (Exception ex) {
            Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        
    }

    @FXML
    private void btnUpdateMouseClicked(ActionEvent event) {
        
            
        try {
            
            String id=txtId.getText();
            String firstName=txtFirstName.getText();
            String lastName=txtLastName.getText();
            String address=txtAddress.getText();
            
            CustomerDTO customer=new CustomerDTO(Integer.parseInt(id),firstName,lastName,address);
            customerController.update(customer);
            
            clearAllTextFileds();
            loadAll();
        } catch (Exception ex) {
            Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void btnAddMouseClicked(ActionEvent event) {
        
        
        try {
            String firstName=txtFirstName.getText();
            String address=txtAddress.getText();
            String lastName=txtLastName.getText();
            
            CustomerDTO customer=new CustomerDTO(firstName,lastName,address);
            
            customerController.add(customer);
            
            clearAllTextFileds();
            loadAll();
        } catch (Exception ex) {
            Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnDeleteMouseClicked(ActionEvent event) {
        try {
            customerController.delete(Integer.parseInt(txtId.getText()));
            clearAllTextFileds();
            loadAll();
        } catch (Exception ex) {
            Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
