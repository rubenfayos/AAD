/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t1_aev3;

import java.util.ArrayList;

/**
 *
 * @author DAM 2
 */
public class Biblioteca {
    
    private ArrayList<Libro> libros;

    
    
    public Biblioteca(ArrayList<Libro> libros) {
        
        this.libros = libros;
        
    }
    
    
    public int crearLibro(Libro libro){
        
        libros.add(libro);

        return libro.getId();
        
    }
    
    public String mostrarLibro(Libro libro){
        
        if(libro == null){
            return "El libro no se ha encontrado";
        }
        
        String info = "ID: " + libro.getId()
                + "\nTítulo: " + libro.getTitulo() 
                + "\nAutor: " + libro.getAutor() 
                + "\nAño publicación: " + libro.getAñoPublicacion()
                + "\nEditorial: " + libro.getEditorial()
                + "\nTotal páginas: " + libro.getPaginas() + "\n";
        
        return info;
        
    }
    
    public String mostrarLibros(){
        
        String infoLibros = "";
        
        for(Libro l : libros){
            
            infoLibros += mostrarLibro(l);
            
        }
        
        return infoLibros;
        
    }
    
    public Libro buscarLibro(int id){
        
        for(Libro temp : libros){
            
            if(id == temp.getId()){
                return temp;
            }
            
        }
        
        return null;
           
    }
    
    
}
