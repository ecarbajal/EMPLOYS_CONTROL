/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package employs_control.Generic;

import javafx.scene.control.Alert;

/**
 * 
 * @author Eduardo Carbajal Reyes
 */
public class Elements {
    
     public static void showAlert(Alert.AlertType alertType,String headerText){
        Alert alert = new Alert(alertType);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(headerText);
        alert.showAndWait();
    }

}
