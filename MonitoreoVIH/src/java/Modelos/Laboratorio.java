package Modelos;

import java.util.Date;

public class Laboratorio extends ExistenciaMaterialMedico {
    private Integer id;
    private Integer idExistencia;
    private String tipoFabricacion;
    private String uso;
    private String formaPresentacion;

    public Laboratorio() {
    }

    public Laboratorio(Integer id, Integer idExistencia, String tipoFabricacion, String uso, String formaPresentacion) {
        this.id = id;
        this.idExistencia = idExistencia;
        this.tipoFabricacion = tipoFabricacion;
        this.uso = uso;
        this.formaPresentacion = formaPresentacion;
    }
    
    public Laboratorio(Integer id, Integer codigo, String fechaConteo, Integer cantidadExistencia, Integer cantidadFaltante, String observacion) {
        super(id, codigo, fechaConteo, cantidadExistencia, cantidadFaltante, observacion);
    }

    public Laboratorio(Integer id, String tipoFabricacion, String uso, String formaPresentacion, Integer idExist, Integer codigo, String fechaConteo, Integer cantidadExistencia, Integer cantidadFaltante, String observacion) {
        super(idExist, codigo, fechaConteo, cantidadExistencia, cantidadFaltante, observacion);
        this.id = id;
        this.idExistencia = idExist;
        this.tipoFabricacion = tipoFabricacion;
        this.uso = uso;
        this.formaPresentacion = formaPresentacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdExistencia() {
        return idExistencia;
    }

    public void setIdExistencia(Integer idExistencia) {
        this.idExistencia = idExistencia;
    }

    public String getTipoFabricacion() {
        return tipoFabricacion;
    }

    public void setTipoFabricacion(String tipoFabricacion) {
        this.tipoFabricacion = tipoFabricacion;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getFormaPresentacion() {
        return formaPresentacion;
    }

    public void setFormaPresentacion(String formaPresentacion) {
        this.formaPresentacion = formaPresentacion;
    }
    
    
    
}
