
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
    
    @Id @Column (name = "id") private int id;
    @Column (name = "titulo") private String titulo;  
    @Column (name = "autor") private String autor;  
    @Column (name = "editorial") private String editorial;   
    @Column (name = "anyoNacimiento") private String anyoNacimiento;   
    @Column (name = "anyoPublicacion") private String anyoPublicacion;   
    @Column (name = "paginas") private String paginas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAnyoNacimiento() {
        return anyoNacimiento;
    }

    public void setAnyoNacimiento(String anyoNacimiento) {
        this.anyoNacimiento = anyoNacimiento;
    }

    public String getAnyoPublicacion() {
        return anyoPublicacion;
    }

    public void setAnyoPublicacion(String anyoPublicacion) {
        this.anyoPublicacion = anyoPublicacion;
    }

    public String getPaginas() {
        return paginas;
    }

    public void setPaginas(String paginas) {
        this.paginas = paginas;
    }
    
    
}
