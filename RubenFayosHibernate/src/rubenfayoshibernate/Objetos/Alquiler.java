/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rubenfayoshibernate.Objetos;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Ruben Fayos
 */
@Entity
@Table (name="alquileres")
public class Alquiler {
    
    @Id @Column(name="idalquiler") private int idAlquiler;
    @Column (name="idcliente") private int idCliente;
    @Column (name="idpelicula") private int idPelicula;
    @Column (name="precio") private Double precio;
    @Column (name="fechaalquiler") private Timestamp fechaAlquiler;

    public Alquiler() {
    }

    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Timestamp getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(Timestamp fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }
    
    
    
}
