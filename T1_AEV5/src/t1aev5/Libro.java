
package t1aev5;

/**
 *
 * @author Ruben Fayos
 */

// Importing required classes
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "libros")
public class Libro {
    
    @Id 
    @Column (name = "id")
    private int id;
    
    @Column (name = "titulo")
    private String titulo;
    
    @Column (name = "autor")
    private String autor;
    
    @Column (name = "editorial")
    private String editorial;
    
    @Column (name = "añoNacimiento")
    private String añoNacimiento;
    
    @Column (name = "añoPublicacion")
    private String añoPublicacion;
    
    @Column (name = "paginas")
    private String paginas;

    public Libro() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAñoNacimiento() {
        return añoNacimiento;
    }

    public void setAñoNacimiento(String añoNacimiento) {
        this.añoNacimiento = añoNacimiento;
    }

    public String getAñoPublicacion() {
        return añoPublicacion;
    }

    public void setAñoPublicacion(String añoPublicacion) {
        this.añoPublicacion = añoPublicacion;
    }

    public String getPaginas() {
        return paginas;
    }

    public void setPaginas(String paginas) {
        this.paginas = paginas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   

    
      
    
}
