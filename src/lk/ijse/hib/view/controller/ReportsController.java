/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.hib.view.controller;

import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.Connection;
import java.io.InputStream;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import lk.ijse.hib.dbconnection.DBConnection;
import lk.ijse.hib.main.Main;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author minoli
 */
public class ReportsController implements Initializable {

    @FXML
    private Button btnView;
    @FXML
    private JFXTextField txtOrderId;
    @FXML
    private Button btnViewCustomers;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnViewMouseClicked(ActionEvent event) {
        try {
            JasperReport compiledReport=(JasperReport) 
                    JRLoader.loadObject(Main.class.getResourceAsStream("/lk/ijse/hib/report/subReports.jasper"));
            
            JasperReport subReport=(JasperReport)
                    JRLoader.loadObject(Main.class.getResourceAsStream("/lk/ijse/hib/report/orderDetail.jasper"));
            HashMap<String,Object> reportParams=new HashMap<>();
            reportParams.put("orderId",txtOrderId.getText());
            reportParams.put("subreport",subReport);
            
            JasperPrint filledReport = JasperFillManager.fillReport
        (compiledReport,reportParams,DBConnection.getInstance().getConnection());
            
            JasperViewer.viewReport(filledReport);
        } catch (JRException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    @FXML
    private void btnViewCustomersMouseClicked(ActionEvent event) {
        try {
            JasperReport compiledReport=(JasperReport) JRLoader.loadObject(Main.class.getResourceAsStream("/lk/ijse/hib/report/Customers.jasper"));
            JasperPrint filledReport = JasperFillManager.fillReport(compiledReport, new HashMap<>(),DBConnection.getInstance().getConnection());
            
            JasperViewer.viewReport(filledReport);
        } catch (JRException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
