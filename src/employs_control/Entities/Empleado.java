package employs_control.Entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * @author Eduardo Carbajal Reyes
 */
public class Empleado {
    private IntegerProperty id_acceso;
    private IntegerProperty id_empleado;
    private StringProperty nombre;
    private StringProperty fecha;
    private StringProperty hora;
    private StringProperty tipo;
    private StringProperty notas;
    

    
    public Empleado(int id_acceso, int id_empleado, String nombre, String fecha, String hora, String tipo, String notas){
        this.id_acceso = new SimpleIntegerProperty(id_acceso);
        this.id_empleado = new SimpleIntegerProperty(id_empleado);
        this.nombre = new SimpleStringProperty(nombre);
        this.fecha = new SimpleStringProperty(fecha);
        this.hora = new SimpleStringProperty(hora);
        this.tipo = new SimpleStringProperty(tipo);
        this.notas = new SimpleStringProperty(notas);
    }

    public int getId_acceso() {
        return id_acceso.get();
    }

    public int getId_empleado() {
        return id_empleado.get();
    }

    public String getFecha() {
        return fecha.get();
    }

    public String getHora() {
        return hora.get();
    }

    public String getTipo() {
        return tipo.get();
    }

    public String getNotas() {
        return notas.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }
    
    
    

    public void setId_acceso(int id_acceso) {
        this.id_acceso.set(id_acceso);
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado.set(id_empleado);
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }

    public void setHora(String hora) {
        this.hora.set(hora);
    }

    public void setTipo(String tipo) {
        this.tipo.set(tipo);
    }

    public void setNotas(String notas) {
        this.notas.set(notas);
    }
    
    

    
   


}
