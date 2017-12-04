/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Modelos.Conectar;
import Modelos.Personal;
import componentes.DefinicionComponentePersonal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Kevin Posada Alegría
 */
@Controller
public class PersonalController {
    private JdbcTemplate jdbcTemplate;
    
    public PersonalController() {
        this.jdbcTemplate =  new JdbcTemplate(Conectar.conectar());
    }
    
    @RequestMapping(value = "Personals.htm", method = RequestMethod.GET)
    public ModelAndView Personals() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Personal/Personals");
        String sql = "select * from personal";
        List<String> personalserror = new ArrayList<>();
        List personals = null;
        try{
            personals = this.jdbcTemplate.queryForList(sql);
        }catch(Exception ex){
            personalserror.add("Error");
            personalserror.add("No hay acceso a la base");
            personalserror.add(ex.toString());
            
        }
        
        mav.addObject("personals", personals);
        mav.addObject("personalserror", personalserror);
        return mav;
    }
    
    @RequestMapping(value = "PersonalVer.htm", method = RequestMethod.GET)
    public ModelAndView personalVer(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int id = Integer.parseInt(request.getParameter("id"));
        mav.setViewName("Personal/PersonalVer");
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8000);
            DefinicionComponentePersonal definicionPersonal = (DefinicionComponentePersonal) registry.lookup("personal");
            String response[] = definicionPersonal.consultaIndividual(id);
            mav.addObject("respuesta", response);
        } catch (Exception ex) {
            Logger.getLogger("Error");
            String respuesta = "Servicio no disponible \n" + ex.toString();
            mav.addObject("respuestaerror", respuesta);
        }
        return mav;
    }
    
    @RequestMapping(value = "PersonalEliminar.htm", method = RequestMethod.GET)
    public ModelAndView personalEliminar(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int id = Integer.parseInt(request.getParameter("id"));
        mav.setViewName("Personal/PersonalEliminar");
        //LLAMAR AL COMPONENTE PARA QUE ELIMINE
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8000);
            DefinicionComponentePersonal definicionPersonal = (DefinicionComponentePersonal) registry.lookup("personal");
            String respuesta = definicionPersonal.Eliminar(id);
            mav.addObject("respuestaEliminar", respuesta);
        } catch (Exception ex) {
            Logger.getLogger("Error");
            String respuesta = "Servicio no disponible \n" + ex.toString();
            mav.addObject("respuestaerror", respuesta);
        }
        return mav;
    }
    
    @RequestMapping(value = "PersonalEditar.htm", method = RequestMethod.GET)
    public ModelAndView personalEditar(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int id = Integer.parseInt(request.getParameter("id"));
        mav.setViewName("Personal/PersonalEditar");
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8000);
            DefinicionComponentePersonal definicionPersonal = (DefinicionComponentePersonal) registry.lookup("personal");
            String response[] = definicionPersonal.consultaIndividual(id);
            String idArea = response[0];
            int idarea = Integer.parseInt(idArea);
            String areaPersonal = response[1];
            String cantidadPersonal = response[2];
            int cantidadpersonal = Integer.parseInt(cantidadPersonal);
            String porcentajePersonal = response[3];
            int porcentajepersonal = Integer.parseInt(porcentajePersonal);
            Personal per = new Personal(idarea, areaPersonal, cantidadpersonal, porcentajepersonal);
            mav.addObject("personal", per);
        } catch (Exception ex) {
            Logger.getLogger("Error");
            String respuesta = "Servicio no disponible \n" + ex.toString();
            mav.addObject("respuestaerror", respuesta);
        }
        return mav;
    }
    
    @RequestMapping(value = "PersonalEditar.htm", method = RequestMethod.POST)
    public ModelAndView personalEditarPost(@ModelAttribute("personal") Personal per,
            BindingResult result,
            SessionStatus status, 
            HttpServletRequest request
    ) {
        int id = Integer.parseInt(request.getParameter("id"));
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Personal/PersonalEditarExito");
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8000);
            DefinicionComponentePersonal definicionPersonal = (DefinicionComponentePersonal) registry.lookup("personal");
            String response = definicionPersonal.Actualizar(
                    id, per.getArea(),
                    per.getCantidad_personal_disponible(), per.getPorcentaje()
            );
            mav.addObject("respuestaEditar", response);
        } catch (Exception ex) {
            Logger.getLogger("Error");
            String respuesta = "Servicio no disponible \n "+ ex.toString();
            mav.addObject("respuestaerror", respuesta);
        }
        return mav;
    }
    
    @RequestMapping(value = "PersonalAdd.htm", method = RequestMethod.GET)
    public ModelAndView PersonalAdd() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Personal/PersonalAdd");
        mav.addObject("personal", new Personal());
        return mav;
    }
    
    @RequestMapping(value = "PersonalAdd.htm", method = RequestMethod.POST)
    public ModelAndView PersonalAddPost(@ModelAttribute("personal") Personal per,
            BindingResult result,
            SessionStatus status
    ) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Personal/PersonalAdd");
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8000);
            DefinicionComponentePersonal definicionPersonal = (DefinicionComponentePersonal) registry.lookup("personal");
            String response = definicionPersonal.insertarPersonal(
                    per.getId(), per.getArea(),
                    per.getCantidad_personal_disponible(), per.getPorcentaje()
            );
            mav.addObject("respuesta", response);
        } catch (Exception ex) {
            Logger.getLogger("Error");
            String respuesta = "Servicio no disponible \n "+ ex.toString();
            mav.addObject("respuesta", respuesta);
        }
        return mav;
    }
    
}
