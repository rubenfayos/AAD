/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rubenfayoshibernate.Objetos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Ruben Fayos
 */
@Entity
@Table (name="peliculas")
public class Pelicula {
    
    @Id @Column (name="idpelicula") private int id;
    @Column (name="idgenero") private int idGenero;
    @Column (name="nombrepeli") private String nombrePeli;
    @Column (name="detalle") private String detalle;
    @Column (name="protagonista") private String protagonista;
    @Column (name="estado") private String estado;
    @Column (name="precio") private double precio;

    public Pelicula() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getNombrePeli() {
        return nombrePeli;
    }

    public void setNombrePeli(String nombrePeli) {
        this.nombrePeli = nombrePeli;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getProtagonista() {
        return protagonista;
    }

    public void setProtagonista(String protagonista) {
        this.protagonista = protagonista;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
    
}
