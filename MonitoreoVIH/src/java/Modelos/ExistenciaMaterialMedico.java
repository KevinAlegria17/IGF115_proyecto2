package Modelos;

import java.util.Date;

public class ExistenciaMaterialMedico {
    private Integer id;
    private Integer codigo;
    private String fechaConteo;
    private Integer cantidadExistencia;
    private Integer cantidadFaltante;
    private String observacion;

    public ExistenciaMaterialMedico() {
    }

    public ExistenciaMaterialMedico(Integer id, Integer codigo, String fechaConteo, Integer cantidadExistencia, Integer cantidadFaltante, String observacion) {
        this.id = id;
        this.codigo = codigo;
        this.fechaConteo = fechaConteo;
        this.cantidadExistencia = cantidadExistencia;
        this.cantidadFaltante = cantidadFaltante;
        this.observacion = observacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getFechaConteo() {
        return fechaConteo;
    }

    public void setFechaConteo(String fechaConteo) {
        this.fechaConteo = fechaConteo;
    }

    public Integer getCantidadExistencia() {
        return cantidadExistencia;
    }

    public void setCantidadExistencia(Integer cantidadExistencia) {
        this.cantidadExistencia = cantidadExistencia;
    }

    public Integer getCantidadFaltante() {
        return cantidadFaltante;
    }

    public void setCantidadFaltante(Integer cantidadFaltante) {
        this.cantidadFaltante = cantidadFaltante;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    
    
}
