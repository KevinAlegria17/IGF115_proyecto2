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
public class Compra extends Planificacion{
    private Integer idCompra;
    private String periodo;

    public Compra() {
    }

    public Compra(Integer idCompra, String periodo, Integer idPlanificacion, String fechaInicio, String fechaFin) {
        super(idPlanificacion, fechaInicio, fechaFin);
        this.idCompra = idCompra;
        this.periodo = periodo;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    
    
}
