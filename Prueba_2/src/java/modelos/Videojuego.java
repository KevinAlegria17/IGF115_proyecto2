
package modelos;

public class Videojuego {
    private int id;
    private int categoria;
    private int consola;
    private String nombre;

    public Videojuego() {
    }

    public Videojuego(int id, int categoria, int consola, String nombre) {
        this.id = id;
        this.categoria = categoria;
        this.consola = consola;
        this.nombre = nombre;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getConsola() {
        return consola;
    }

    public void setConsola(int consola) {
        this.consola = consola;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
