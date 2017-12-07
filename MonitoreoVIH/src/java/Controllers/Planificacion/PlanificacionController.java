/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Planificacion;

import Modelos.Conectar;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Kevin Posada Alegría
 */
public class PlanificacionController {
    private JdbcTemplate jdbcTemplate;

    public PlanificacionController() {
        this.jdbcTemplate =  new JdbcTemplate(Conectar.conectar());
    }
    
    @RequestMapping(value = "PlanificacionPanel.htm", method = RequestMethod.GET)
    public ModelAndView PlanificacionPanel() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Planificacion/PlanificacionPanel");
        return mav;
    }
}
