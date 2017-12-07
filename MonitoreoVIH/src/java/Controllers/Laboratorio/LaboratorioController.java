package Controllers.Laboratorio;

import Modelos.Conectar;
import componentes.DefinicionComponenteMaterial;

import componentes.Laboratorio.Laboratorio;
import componentes.Laboratorio.ExistenciaMaterialMedico;

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
        mav.setViewName("Laboratorio/LaboratorioAdd");
        mav.addObject("material-lab", new Laboratorio());
        return mav;
    }

    @RequestMapping(value = "LaboratorioAdd.htm", method = RequestMethod.POST)
    public ModelAndView laboratorioAdd(@ModelAttribute("material-lab") Laboratorio lab,
            BindingResult result,
            SessionStatus status
    ) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Laboratorio/LaboratorioAdd");
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

    @RequestMapping(value = "Laboratorios.htm", method = RequestMethod.GET)
    public ModelAndView Laboratorios() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Laboratorio/Laboratorios");
        String sql = "select * from existencialaboratorio";
        List<String> laboratorioserror = new ArrayList<>();
        List laboratorios = null;
        try{
            laboratorios = this.jdbcTemplate.queryForList(sql);
        }catch(Exception ex){
            laboratorioserror.add("Error");
            laboratorioserror.add("No hay acceso a la base");
            laboratorioserror.add(ex.toString());
            
        }
        
        mav.addObject("laboratorios", laboratorios);
        mav.addObject("laboratorioserror", laboratorioserror);
        return mav;
    }

    @RequestMapping(value = "LaboratorioVer.htm", method = RequestMethod.GET)
    public ModelAndView laboratorioVer(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int id = Integer.parseInt(request.getParameter("id"));
        mav.setViewName("Laboratorio/LaboratorioVer");
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
        mav.setViewName("Laboratorio/LaboratorioEliminar");
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

    @RequestMapping(value = "LaboratorioEditar.htm", method = RequestMethod.GET)
    public ModelAndView laboratorioEditar(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int id = Integer.parseInt(request.getParameter("id"));
        mav.setViewName("Laboratorio/LaboratorioEditar");
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8000);
            DefinicionComponenteMaterial definicionMaterial = (DefinicionComponenteMaterial) registry.lookup("material");
            String response[] = definicionMaterial.consultaIndividual(id);
            String idExist = response[0];
            System.out.print("LLega bien hasta aca");
            int idExist2 = Integer.parseInt(idExist);
            String idLab = response[1];
            int idLab2 = Integer.parseInt(idLab);
            String tipoFabricacion = response[2];
            String uso = response[3];
            String formaPresentacion = response[4];
            Laboratorio lab = new Laboratorio(id, idExist2, tipoFabricacion, uso, formaPresentacion);
            mav.addObject("material", lab);
        } catch (Exception ex) {
            Logger.getLogger("Error");
            String respuesta = "Servicio no disponible \n" + ex.toString();
            mav.addObject("respuestaerror", respuesta);
        }
        return mav;
    }
    
    @RequestMapping(value = "LaboratorioEditar.htm", method = RequestMethod.POST)
    public ModelAndView laboratorioEditarPost(@ModelAttribute("material") Laboratorio lab,
            BindingResult result,
            SessionStatus status, 
            HttpServletRequest request
    ) {
        int id = Integer.parseInt(request.getParameter("id"));
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Laboratorio/LaboratorioEditarExito");
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8000);
            DefinicionComponenteMaterial definicionMaterial = (DefinicionComponenteMaterial) registry.lookup("material");
            String response = definicionMaterial.Actualizar(
                    id, lab.getTipoFabricacion(),
                    lab.getUso(), lab.getFormaPresentacion()
            );
            mav.addObject("respuestaEditar", response);
        } catch (Exception ex) {
            Logger.getLogger("Error");
            String respuesta = "Servicio no disponible \n "+ ex.toString();
            mav.addObject("respuestaerror", respuesta);
        }
        return mav;
    }
}
    