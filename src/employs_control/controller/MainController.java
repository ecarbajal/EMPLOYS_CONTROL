/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employs_control.controller;

import employs_control.DB.DBConnection;
import employs_control.Entities.Empleado;
import employs_control.Generic.Elements;
import employs_control.Generic.Utils;
import employs_control.model.DBMethods;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

/**
 *
 * @author Eduardo Carbajal
 */
public class MainController implements Initializable {

    @FXML
    private Label lblFecha;
    @FXML
    private Label lblHora;
    @FXML
    private TextField txtClave;
    @FXML
    private TextField txtContrasenia;
    @FXML
    private Label lblNombre;
    
    @FXML
    private TableView<Empleado> tblAccesos;
    @FXML
    private TableColumn<String, Empleado> clES;
    @FXML
    private TableColumn<String, Empleado> clNombre;
    @FXML
    private TableColumn<String, Empleado> clHora;
    @FXML
    private TableColumn<String, Empleado> clNotas;
    
    private final ObservableList<Empleado> accessData = FXCollections.observableArrayList();
    
    private final DBMethods dbm = DBMethods.getGeneric();
    private final DBConnection dbc = new DBConnection();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Timeline clock = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {

            lblHora.setText(Utils.getHourGreeting());
            lblFecha.setText("Hoy es " + Utils.getFechaLetra());
        }),
                new KeyFrame(javafx.util.Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
        
        txtClave.setOnKeyPressed(k -> {
            final KeyCombination ENTER = new KeyCodeCombination(KeyCode.TAB);
            if (ENTER.match(k)) {
                lblNombre.setText(dbm.getUserName(txtClave.getText()));
            }
        });

        clES.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        clNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        clHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        clNotas.setCellValueFactory(new PropertyValueFactory<>("notas"));

        tblAccesos.setItems(accessData);

        loadAccessData();
    }
    
    @FXML
    public void registrarAcceso(){
        String clave = txtClave.getText().trim();
        String contrasenia = txtContrasenia.getText();
        
        if(dbm.validateUser(clave, contrasenia) != true){
            
            Elements.showAlert(Alert.AlertType.ERROR, "Los datos ingresados son incorrectos.");
            txtClave.setText("");
            txtContrasenia.setText("");
            lblNombre.setText("");
            Platform.runLater(() -> txtClave.requestFocus());
            
        }else{
            
            try {
                String query = "INSERT INTO RegAcceso values(?,?,?,?,?,?);";
                PreparedStatement ps = dbc.getConnection().prepareStatement(query);
                
                ps.setInt(1, java.sql.Types.NULL);
                ps.setString(2,dbm.getUserID(clave));
                ps.setDate(3, Utils.getCurrentDate());
                ps.setTime(4, java.sql.Time.valueOf(Utils.getHour()));
                ps.setString(5, "Acceso");
                ps.setString(6, "OK");
                
                ps.executeUpdate();
                
                System.out.println("Inserto registro correctamente");
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            
            
            Elements.showAlert(Alert.AlertType.INFORMATION, "Acceso correcto");
            txtClave.setText("");
            txtContrasenia.setText("");
            lblNombre.setText("");
            Platform.runLater(() -> txtClave.requestFocus());

        }
        
        
    }

    public void loadAccessData(){
        try {
            dbc.EstablecerConexion();
            ResultSet rs = dbc.EjecutarSentencia("SELECT RegAcceso.id_acceso, RegAcceso.id_empleado, empleado.nombre, RegAcceso.fecha, RegAcceso.hora, RegAcceso.tipo, RegAcceso.notas FROM empleado INNER JOIN RegAcceso ON empleado.[id_empleado] = RegAcceso.[id_empleado];");
            while (rs.next()) {                
                accessData.add(new Empleado(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getTime(5).toString(),
                        rs.getString(6),
                        rs.getString(7))
                ); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
