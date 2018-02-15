/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.hib.view.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author minoli
 */
public class DashboardViewController implements Initializable {

    @FXML
    private JFXButton btnManageCustomer;
    @FXML
    private JFXButton btnManageItem;
    
    @FXML
    private JFXButton btnPlaceOrder;
   
    @FXML
    private AnchorPane anchorDashboard;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                
    }  
   
    
    private void loadAnchor(String page){
       
    anchorDashboard.getChildren().clear();
    
            Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/lk/ijse/hib/view/ui/"+page+".fxml"));
            anchorDashboard.getChildren().add(root);
            Node node=(Node)root;
            AnchorPane.setTopAnchor(node,0.0);
            AnchorPane.setRightAnchor(node,0.0);
            AnchorPane.setLeftAnchor(node,0.0);
            AnchorPane.setBottomAnchor(node,0.0);
            
        } catch (IOException ex) {
            Logger.getLogger(DashboardViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
    }


    @FXML
    private void btnManageCustomerMouseClicked(ActionEvent event) {
        loadAnchor("Customer");
    }

    @FXML
    private void btnManageItemMouseClicked(ActionEvent event) {
        loadAnchor("Item");
        
    }

    @FXML
    private void btnPlaceOrderMouseClicked(ActionEvent event) {
        loadAnchor("PlaceOrder");
    }

    @FXML
    private void btnReportMouseClicked(ActionEvent event) {
        loadAnchor("Reports");
    }

    
}
