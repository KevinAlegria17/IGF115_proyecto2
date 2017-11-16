
package modelos;

public class Usuario {
    private int id;
    private String nombre;
    private int prestamo;

    public Usuario() {
    }

    public Usuario(int id, String nombre, int prestamo) {
        this.id = id;
        this.nombre = nombre;
        this.prestamo = prestamo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(int prestamo) {
        this.prestamo = prestamo;
    }
 
}
