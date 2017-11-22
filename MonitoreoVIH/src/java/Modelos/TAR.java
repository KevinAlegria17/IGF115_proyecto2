package Modelos;

import java.util.Date;

public class TAR extends ExistenciaMaterialMedico {
    private Integer id;
    private Integer idExistencia;
    private Integer idLineaNivel;
    private String nombreComercial;
    private String accionFarmacologica;

    public TAR() {
    }

    public TAR(Integer id, Integer codigo, Date fechaConteo, Integer cantidadExistencia, Integer cantidadFaltante, String observacion) {
        super(id, codigo, fechaConteo, cantidadExistencia, cantidadFaltante, observacion);
    }

    public TAR(Integer id, Integer idLineaNivel, String nombreComercial, String accionFarmacologica, Integer idExist, Integer codigo, Date fechaConteo, Integer cantidadExistencia, Integer cantidadFaltante, String observacion) {
        super(idExist, codigo, fechaConteo, cantidadExistencia, cantidadFaltante, observacion);
        this.id = id;
        this.idExistencia = idExist;
        this.idLineaNivel = idLineaNivel;
        this.nombreComercial = nombreComercial;
        this.accionFarmacologica = accionFarmacologica;
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

    public Integer getIdLineaNivel() {
        return idLineaNivel;
    }

    public void setIdLineaNivel(Integer idLineaNivel) {
        this.idLineaNivel = idLineaNivel;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getAccionFarmacologica() {
        return accionFarmacologica;
    }

    public void setAccionFarmacologica(String accionFarmacologica) {
        this.accionFarmacologica = accionFarmacologica;
    }
    
    
    
}
