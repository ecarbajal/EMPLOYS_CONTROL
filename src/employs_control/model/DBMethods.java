/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package employs_control.model;

import employs_control.DB.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Eduardo Carbajal Reyes
 */
public class DBMethods {
    private DBMethods(){}

    private static final DBMethods DBM = new DBMethods();
    private final DBConnection dbc = new DBConnection();

    public static DBMethods getGeneric() {
        return DBM;
    }

    
    
    public String getUserName(String clave) {
        dbc.EstablecerConexion();
        String nombre = "";
        try {
            ResultSet cdr = dbc.EjecutarSentencia("SELECT nombre FROM empleado WHERE clave = '"+clave + "';");
            while (cdr.next()) {                
                nombre = cdr.getString("nombre");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBMethods.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nombre;
    }
    
    public String getUserID(String clave) {
        dbc.EstablecerConexion();
        String nombre = "";
        try {
            ResultSet cdr = dbc.EjecutarSentencia("SELECT id_empleado FROM empleado WHERE clave = '"+clave + "';");
            while (cdr.next()) {                
                nombre = cdr.getString("id_empleado");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBMethods.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nombre;
    }
    
    public boolean validateUser(String clave, String contrasenia){
        dbc.EstablecerConexion();
        boolean valido = false;
        try {
            ResultSet cdr = dbc.EjecutarSentencia("SELECT nombre FROM empleado WHERE clave = '"+clave + "' AND contrasena = '"+contrasenia+"'");
            String nombre = null;
            
            while (cdr.next()) {                
                nombre = cdr.getString("nombre");
            }
            
            valido = (nombre != null && !nombre.equals(""));
            
        } catch (SQLException ex) {
            Logger.getLogger(DBMethods.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valido;
    }
    
    public void execute(String query){
        try {
            dbc.EstablecerConexion();
            dbc.EjecutarSentencia(query);
            System.out.println("Inserto en BD OK");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }

}
