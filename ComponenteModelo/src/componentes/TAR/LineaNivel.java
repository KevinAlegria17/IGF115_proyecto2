package componentes.TAR;

public class LineaNivel {
    private Integer id;
    private String nombreLinea;
    private String descripcion;

    public LineaNivel() {
    }

    public LineaNivel(Integer id, Integer idTar, String nombreLinea, String descripcion) {
        this.id = id;
        this.nombreLinea = nombreLinea;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreLinea() {
        return nombreLinea;
    }

    public void setNombreLinea(String nombreLinea) {
        this.nombreLinea = nombreLinea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
