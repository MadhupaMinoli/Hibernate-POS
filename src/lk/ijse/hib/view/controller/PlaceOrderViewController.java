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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.hib.Controller.impl.CustomerControllerImpl;
import lk.ijse.hib.Controller.impl.ItemControllerImpl;
import lk.ijse.hib.Controller.impl.OrderControllerImpl;
import lk.ijse.hib.dto.CustomerDTO;
import lk.ijse.hib.dto.ItemDTO;
import lk.ijse.hib.dto.OrderDTO;
import lk.ijse.hib.dto.OrderDetailsDTO;
import lk.ijse.hib.view.ui.tblmodel.PlaceOrderTM;

/**
 * FXML Controller class
 *
 * @author minoli
 */
public class PlaceOrderViewController implements Initializable {

    @FXML
    private TableView<PlaceOrderTM> tblPlaceOrder;
    @FXML
    private ComboBox<Integer> cmbCustomerId;
    @FXML
    private ComboBox<Integer> cmbItemId;
    @FXML
    private DatePicker txtDate;
    @FXML
    private JFXTextField txtOrderId;
    @FXML
    private JFXTextField txtUnitPrice;
    @FXML
    private JFXTextField txtCustomerName;
    @FXML
    private JFXTextField txtItemName;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private JFXTextField txtQtyOnHand;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnSubmit;
    @FXML
    private JFXTextField txtNetPrice;
    private ArrayList<CustomerDTO> customerList;
    private ArrayList<ItemDTO> itemList;
    private final OrderControllerImpl orderController;
    private final ItemControllerImpl itemController;
    private final CustomerControllerImpl customerController;
    private ObservableList<PlaceOrderTM> orderDetails;
    private boolean update;

    public PlaceOrderViewController() {
        orderController = new OrderControllerImpl();
        itemController = new ItemControllerImpl();
        customerController = new CustomerControllerImpl();
        
        
        try {
            customerList=(ArrayList<CustomerDTO>) customerController.getAll();
            itemList=(ArrayList<ItemDTO>) itemController.getAll();
        } catch (Exception ex) {
            Logger.getLogger(PlaceOrderViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblPlaceOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemId"));
        tblPlaceOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblPlaceOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblPlaceOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblPlaceOrder.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));
        orderDetails = FXCollections.observableArrayList();
        tblPlaceOrder.setItems(orderDetails);
        
        orderDetails=tblPlaceOrder.getItems();
        for (ItemDTO item : itemList) {
            cmbItemId.getItems().add(item.getItemId());
        }
        
        for (CustomerDTO cust : customerList) {
            cmbCustomerId.getItems().add(cust.getCustomerId());
        }
        
        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                
               try {
                    int customerID = observable.getValue();
                    if (customerID == 0) {
                        txtCustomerName.setText("");
                        return;
                    }
                    CustomerDTO customer = customerController.search(customerID);
                    txtCustomerName.setText(customer.getFirstName());
                } catch (Exception ex) {
                    Logger.getLogger(PlaceOrderViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                 });
        cmbItemId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
           

            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                
                try {
                    int itemId = observable.getValue();

                if (itemId == 0) {
                    txtItemName.setText("");
                    txtQtyOnHand.setText("");
                    txtUnitPrice.setText("");
                    txtQty.setText("");
                    return;
                }
                    ItemDTO item = itemController.search(itemId);
                    txtItemName.setText(item.getItemName());
                    txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
                    txtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
                    
                } catch (Exception ex) {
                    Logger.getLogger(PlaceOrderViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        tblPlaceOrder.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PlaceOrderTM>() {
            @Override
            public void changed(ObservableValue<? extends PlaceOrderTM> observable, PlaceOrderTM oldValue, PlaceOrderTM newValue) {

                PlaceOrderTM currentRow = observable.getValue();

                if (currentRow == null) {
                    cmbItemId.getSelectionModel().clearSelection();
                    update = false;
                    btnDelete.setDisable(true);
                    return;
                }

                update = true;
                String itemCode = currentRow.getItemId();
                btnDelete.setDisable(false);

                cmbItemId.getSelectionModel().select(Integer.parseInt(itemCode));
                txtQty.setText(currentRow.getQty() + "");

            }
        });

        btnDelete.setDisable(true); 
        

         tblPlaceOrder.getItems().addListener(new ListChangeListener<PlaceOrderTM>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends PlaceOrderTM> c) {
                
                double total = 0.0;
                
                for (PlaceOrderTM orderDetail : orderDetails) {
                    total += orderDetail.getTotal();
                }
                
                txtNetPrice.setText(String.valueOf(total));
                
            }
        });           
                 
    }

    @FXML
    private void btnAddMouseClicked(ActionEvent event) {
        int itemCode =cmbItemId.getSelectionModel().getSelectedItem();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
    
            for (PlaceOrderTM orderDetail : orderDetails) {
                if (orderDetail.getItemId().equals(itemCode)) {
                    
                    
                  Alert error = new Alert(Alert.AlertType.ERROR, "Please update the item instead of adding", ButtonType.OK);
                    error.setHeaderText("Duplicate Entry Found");
                    error.setTitle("Duplicate Error");
                    error.show();
                    return;
                }
            }
        

        PlaceOrderTM orderDetail = new PlaceOrderTM(
                String.valueOf(itemCode),
                txtItemName.getText(),
                unitPrice,qty,
                qty * unitPrice);

            orderDetails.add(orderDetail);
            tblPlaceOrder.setItems(orderDetails);
        
            
            
            
//        cmbItemId.getSelectionModel().clearSelection();
//        cmbItemId.requestFocus();
    }

    @FXML
    private void btnDeleteMouseClicked(ActionEvent event) {
        PlaceOrderTM selectedRow = tblPlaceOrder.getSelectionModel().getSelectedItem();
        
        orderDetails.remove(selectedRow);
    }

    @FXML
    private void btnSbmitMouseClicked(ActionEvent event) {
        List<PlaceOrderTM> placeOrderTM=tblPlaceOrder.getItems();
        ArrayList<OrderDetailsDTO> orderArray=new ArrayList<>();
        for(PlaceOrderTM order : placeOrderTM){
                orderArray.add(new OrderDetailsDTO(Integer.parseInt(txtOrderId.getText()), Integer.parseInt(order.getItemId()),  Integer.parseInt(txtQty.getText())));
  
        }
       OrderDTO orderDto=new OrderDTO(Integer.parseInt(txtOrderId.getText()),txtDate.getEditor().getText(),cmbCustomerId.getSelectionModel().getSelectedItem(),Double.parseDouble(txtNetPrice.getText()));
       orderController.placeorder(orderDto, orderArray);
        
    }
    
}
