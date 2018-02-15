/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.hib.view.controller;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hib.Controller.ItemController;
import lk.ijse.hib.Controller.impl.ItemControllerImpl;
import lk.ijse.hib.dto.ItemDTO;
import lk.ijse.hib.view.ui.tblmodel.CustomerTM;
import lk.ijse.hib.view.ui.tblmodel.ItemTM;

/**
 * FXML Controller class
 *
 * @author minoli
 */
public class ItemViewController implements Initializable {

    @FXML
    private TableView<ItemTM> tblItem;
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtUnitPrice;
    @FXML
    private JFXTextField txtQtyOnHand;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnAdd;
    
    private boolean update;
    
    
    private ItemControllerImpl itemController = new ItemControllerImpl();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tblItem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemId"));
        tblItem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblItem.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblItem.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        
        
        
        try {
             ArrayList<ItemDTO> item = (ArrayList<ItemDTO>) itemController.getAll();
            ArrayList<ItemTM> itemtm=new ArrayList<ItemTM>();
           
            item.forEach((dto) -> {
                itemtm.add(new ItemTM(String.valueOf(dto.getItemId()),dto.getItemName(),dto.getUnitPrice(),dto.getQtyOnHand()));
            });
            
            ObservableList<ItemTM> allitem =FXCollections.observableArrayList(itemtm);
            
            tblItem.setItems(allitem);
        } catch (Exception ex) {
            Logger.getLogger(ItemViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblItem.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ItemTM>() {
            @Override
            public void changed(ObservableValue<? extends ItemTM> observable, ItemTM oldValue,ItemTM newValue) {
                ItemTM item = observable.getValue();
                
                if (item == null){
                     update = false;
                    return;
                }
                update = true;
                btnDelete.setDisable(!update);
                txtId.setText(item.getItemId());
                txtName.setText(item.getItemName());
                txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
                txtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
            }
            
        });             
         
    }    
    private void loadAll(){
        try {
            ArrayList<ItemDTO> item = (ArrayList<ItemDTO>) itemController.getAll();
            ArrayList<ItemTM> itemtm=new ArrayList<ItemTM>();
           
            item.forEach((dto) -> {
                itemtm.add(new ItemTM(String.valueOf(dto.getItemId()),dto.getItemName(),dto.getUnitPrice(),dto.getQtyOnHand()));
            });
            
            ObservableList<ItemTM> allitem =FXCollections.observableArrayList(itemtm);
            
            tblItem.setItems(allitem);
        } catch (Exception ex) {
            Logger.getLogger(ItemViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
    private void clearAllTextFileds(){
        txtId.setText("");
        txtName.setText("");
        txtUnitPrice.setText("");
        txtQtyOnHand.setText("");
    }

    @FXML
    private void btnSearchMouseClicked(ActionEvent event) {
        ItemDTO item;
        try {
            item = itemController.search(Integer.parseInt(txtId.getText()));
            txtName.setText(item.getItemName());
            txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
            txtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
        } catch (Exception ex) {
            Logger.getLogger(ItemViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @FXML
    private void btnDeleteMouseClicked(ActionEvent event) {
        try {
            itemController.delete(Integer.parseInt(txtId.getText()));
            loadAll();
        } catch (Exception ex) {
            Logger.getLogger(ItemViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnUpdateMouseClicked(ActionEvent event) {
        try {
            String id=txtId.getText();
            String name=txtName.getText();
            int qtyOnHand=Integer.parseInt(txtQtyOnHand.getText());
            double unitPrice=Double.parseDouble(txtUnitPrice.getText());
            ItemDTO item=new ItemDTO(Integer.parseInt(id),name,unitPrice,qtyOnHand);
           
            itemController.update(item);
            clearAllTextFileds();
            loadAll();
        } catch (Exception ex) {
            Logger.getLogger(ItemViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnAddMouseClicked(ActionEvent event) {
        try {
           
            String name=txtName.getText();
            double unitprice=Double.parseDouble(txtUnitPrice.getText());
            int qtyOnHand=Integer.parseInt(txtQtyOnHand.getText());
            
            ItemDTO item=new ItemDTO(name,unitprice,qtyOnHand);
            itemController.add(item);
            clearAllTextFileds();
            loadAll();
        } catch (Exception ex) {
            Logger.getLogger(ItemViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
