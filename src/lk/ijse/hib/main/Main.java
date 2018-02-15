/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.hib.main;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.hib.configuration.HibernateConfg;
import lk.ijse.hib.core.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author minoli
 */
public class Main extends Application{
    @Override
   
    public void start(Stage stage) throws IOException, ClassNotFoundException {
      
       
       Class.forName("com.mysql.jdbc.Driver");
    
        Parent root = FXMLLoader.load(getClass().getResource("/lk/ijse/hib/view/ui/Dashboard.fxml"));
        
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

        
        launch(args);
        HibernateConfg.shutDown();
    
    
    
    }
}
