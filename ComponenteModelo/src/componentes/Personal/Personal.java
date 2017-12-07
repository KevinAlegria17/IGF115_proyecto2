/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes.Personal;

/**
 *
 * @author Kevin Posada Alegría
 */
public class Personal {
    private Integer id;
    private String area;
    private Integer cantidad_personal_disponible;
    private Integer porcentaje;

    public Personal() {
    }

    public Personal(Integer id, String area, Integer cantidad_personal_disponible, Integer porcentaje) {
        this.id = id;
        this.area = area;
        this.cantidad_personal_disponible = cantidad_personal_disponible;
        this.porcentaje = porcentaje;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getCantidad_personal_disponible() {
        return cantidad_personal_disponible;
    }

    public void setCantidad_personal_disponible(Integer cantidad_personal_disponible) {
        this.cantidad_personal_disponible = cantidad_personal_disponible;
    }

    public Integer getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Integer porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    
}
