package Controllers;

import Modelos.Conectar;
import Modelos.ExistenciaMaterialMedico;
import Modelos.Laboratorio;
import componentes.DefinicionComponenteMaterial;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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

@Controller
public class LaboratorioController {

    private JdbcTemplate jdbcTemplate;
    private ExistenciaMaterialMedico existencia;

    public LaboratorioController() {
        this.jdbcTemplate = new JdbcTemplate(Conectar.conectar());
    }

    @RequestMapping(value = "MaterialLaboratorioAdd.htm", method = RequestMethod.GET)
    public ModelAndView form() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("MaterialLaboratorioAdd");
        mav.addObject("existencia", new ExistenciaMaterialMedico());
        return mav;
    }

    @RequestMapping(value = "MaterialLaboratorioAdd.htm", method = RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("existencia") ExistenciaMaterialMedico emm,
            BindingResult result,
            SessionStatus status
    ) {
        this.existencia = emm;
        return new ModelAndView("redirect:/LaboratorioAdd.htm");
    }

    @RequestMapping(value = "LaboratorioAdd.htm", method = RequestMethod.GET)
    public ModelAndView laboratorioAdd() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("LaboratorioAdd");
        mav.addObject("material-lab", new Laboratorio());
        return mav;
    }

    @RequestMapping(value = "LaboratorioAdd.htm", method = RequestMethod.POST)
    public ModelAndView laboratorioAdd(@ModelAttribute("material-lab") Laboratorio lab,
            BindingResult result,
            SessionStatus status
    ) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("LaboratorioAdd");
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8000);
            DefinicionComponenteMaterial definicionMaterial = (DefinicionComponenteMaterial) registry.lookup("material");

            String response = definicionMaterial.insertarlab(
                    existencia.getId(), existencia.getCodigo(), existencia.getFechaConteo(),
                    existencia.getCantidadExistencia(), existencia.getCantidadFaltante(), existencia.getObservacion(),
                    lab.getId(), lab.getTipoFabricacion(), lab.getUso(), lab.getFormaPresentacion()
            );
            mav.addObject("respuesta", response);
        } catch (Exception ex) {
            Logger.getLogger("Error");
            String respuesta = "Servicio no disponible \n" + ex.toString();
            mav.addObject("respuesta", respuesta);
        }
        return mav;
    }

    /*
    @RequestMapping(value="LaboratorioVer.htm",method=RequestMethod.GET)
    public ModelAndView laboratorioVer(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("LaboratorioVer");
        String error ="Error encontrado";
        try {
                    Registry registry = LocateRegistry.getRegistry("localhost", 8000);
                    DefinicionComponenteMaterial definicionMaterial = (DefinicionComponenteMaterial) registry.lookup("material");
                    error+="Antes de crear lista";
                    List lista = definicionMaterial.verMaterialesLaboratorio();
                    error+="Olvidalo XD";
                    mav.addObject("resultado",lista);
                } catch (Exception ex) {
                    Logger.getLogger("Error");
                    String respuesta="Servicio no disponible \n"+ error + ex.toString();
                    mav.addObject("respuesta", respuesta);
                }
        return mav;
    }
     */
    @RequestMapping(value = "Laboratorios.htm", method = RequestMethod.GET)
    public ModelAndView Laboratorios() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Laboratorios");
        String sql = "select * from existencialaboratorio";
        List laboratorios = this.jdbcTemplate.queryForList(sql);
        mav.addObject("laboratorios", laboratorios);
        return mav;
    }

    @RequestMapping(value = "Prueba000.htm", method = RequestMethod.GET)
    public ModelAndView prueba000() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Prueba000");
        return mav;
    }

    @RequestMapping(value = "LaboratorioVer.htm", method = RequestMethod.GET)
    public ModelAndView laboratorioVer(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int id = Integer.parseInt(request.getParameter("id"));
        mav.setViewName("LaboratorioVer");
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8000);
            DefinicionComponenteMaterial definicionMaterial = (DefinicionComponenteMaterial) registry.lookup("material");
            String response[] = definicionMaterial.consultaIndividual(id);
            mav.addObject("respuesta", response);
        } catch (Exception ex) {
            Logger.getLogger("Error");
            String respuesta = "Servicio no disponible \n" + ex.toString();
            mav.addObject("respuestaerror", respuesta);
        }
        return mav;
    }
    
    @RequestMapping(value = "LaboratorioEliminar.htm", method = RequestMethod.GET)
    public ModelAndView LaboratorioEliminar(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int id = Integer.parseInt(request.getParameter("id"));
        mav.setViewName("LaboratorioEliminar");
        //LLAMAR AL COMPONENTE PARA QUE ELIMINE
            try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8000);
            DefinicionComponenteMaterial definicionMaterial = (DefinicionComponenteMaterial) registry.lookup("material");
            String respuesta = definicionMaterial.Eliminar(id);
            mav.addObject("respuestaEliminar", respuesta);
        } catch (Exception ex) {
            Logger.getLogger("Error");
            String respuesta = "Servicio no disponible \n" + ex.toString();
            mav.addObject("respuestaerror", respuesta);
        }
        return mav;
    }

}
