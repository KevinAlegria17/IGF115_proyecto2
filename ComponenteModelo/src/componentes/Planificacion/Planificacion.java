/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes.Planificacion;

/**
 *
 * @author Kevin Posada Alegría
 */
public class Planificacion {
    private Integer idPlanificacion;
    private String fechaInicio;
    private String fechaFin;

    public Planificacion() {
    }

    public Planificacion(Integer idPlanificacion, String fechaInicio, String fechaFin) {
        this.idPlanificacion = idPlanificacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Integer getIdPlanificacion() {
        return idPlanificacion;
    }

    public void setIdPlanificacion(Integer idPlanificacion) {
        this.idPlanificacion = idPlanificacion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    
}
