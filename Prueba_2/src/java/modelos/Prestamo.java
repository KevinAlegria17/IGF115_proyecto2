
package modelos;

public class Prestamo {
    private int id;
    private int Videojuego;
    private int Usuario;

    public Prestamo() {
    }

    public Prestamo(int id, int Videojuego, int Usuario) {
        this.id = id;
        this.Videojuego = Videojuego;
        this.Usuario = Usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVideojuego() {
        return Videojuego;
    }

    public void setVideojuego(int Videojuego) {
        this.Videojuego = Videojuego;
    }

    public int getUsuario() {
        return Usuario;
    }

    public void setUsuario(int Usuario) {
        this.Usuario = Usuario;
    }
    
}
