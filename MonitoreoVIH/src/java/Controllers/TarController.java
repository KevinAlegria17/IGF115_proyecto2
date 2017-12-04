
package Controllers;

import Modelos.Conectar;
import Modelos.ExistenciaMaterialMedico;
import Modelos.Laboratorio;
import Modelos.TAR;
import componentes.DefinicionComponenteMaterial;
import componentes.DefinicionComponenteTar;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

public class TarController {
    private JdbcTemplate jdbcTemplate;
    private ExistenciaMaterialMedico existencia;

    public TarController() {
        this.jdbcTemplate =  new JdbcTemplate(Conectar.conectar());
    }
    
    @RequestMapping(value="MaterialTarAdd.htm",method=RequestMethod.GET)
    public ModelAndView form(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("MaterialLaboratorioAdd");
        mav.addObject("existencia", new ExistenciaMaterialMedico());
        return mav;
    }
    
    @RequestMapping(value="MaterialTarAdd.htm",method=RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("existencia") ExistenciaMaterialMedico emm,
                             BindingResult result,
                             SessionStatus status
            )
    {
            this.existencia=emm;
            return new ModelAndView("redirect:/TarAdd.htm");
    }
    
    @RequestMapping(value="TarAdd.htm",method=RequestMethod.GET)
    public ModelAndView TarAdd(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Tar/TarAdd");
        mav.addObject("material-tar", new TAR());
        
        String sqllin ="select * from linea_de_nivel;";
        List datoslineas = this.jdbcTemplate.queryForList(sqllin);
        mav.addObject("lineas",datoslineas);
        
        return mav;
    }
    
     @RequestMapping(value="TarAdd.htm",method=RequestMethod.POST)
    public ModelAndView TarAdd(@ModelAttribute("material-tar") TAR tar,
                             BindingResult result,
                             SessionStatus status
            )
    {
                ModelAndView mav = new ModelAndView();
                mav.setViewName("Tar/TarAdd");
                try {
                    Registry registry = LocateRegistry.getRegistry("localhost", 8000);
                    DefinicionComponenteTar definicionTar = (DefinicionComponenteTar) registry.lookup("tar");
                    
                    String response = definicionTar.insertartar(
                            existencia.getId(), existencia.getCodigo(), existencia.getFechaConteo(), 
                            existencia.getCantidadExistencia(), existencia.getCantidadFaltante(), existencia.getObservacion(),
                            tar.getId(), tar.getIdLineaNivel(), tar.getNombreComercial(),tar.getAccionFarmacologica()
                    );
                      mav.addObject("respuesta",response);
                } catch (Exception ex) {
                    Logger.getLogger("Error");
                    String respuesta= "Servicio no disponible \n" + ex.toString();
                    mav.addObject("respuesta", respuesta);
                }
            return mav;
    }
    
    @RequestMapping(value = "Tars.htm", method = RequestMethod.GET)
    public ModelAndView Tars() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Tar/Tars");
        String sql = "select * from existenciatar";
        List<String> tarserror = new ArrayList<>();
        List tars = null;
        try{
            tars = this.jdbcTemplate.queryForList(sql);
        }catch(Exception ex){
            tarserror.add("Error");
            tarserror.add("No hay acceso a la base");
            tarserror.add(ex.toString());
            
        }
        
        mav.addObject("tars", tars);
        mav.addObject("tarserror", tarserror);
        return mav;
    }

    @RequestMapping(value = "TarVer.htm", method = RequestMethod.GET)
    public ModelAndView laboratorioVer(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int id = Integer.parseInt(request.getParameter("id"));
        mav.setViewName("Tar/TarVer");
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8000);
            DefinicionComponenteTar definicionTar = (DefinicionComponenteTar) registry.lookup("tar");
            String response[] = definicionTar.consultaIndividual(id);
            mav.addObject("respuesta", response);
        } catch (Exception ex) {
            Logger.getLogger("Error");
            String respuesta = "Servicio no disponible \n" + ex.toString();
            mav.addObject("respuestaerror", respuesta);
        }
        return mav;
    }

    @RequestMapping(value = "TarEliminar.htm", method = RequestMethod.GET)
    public ModelAndView TarEliminar(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int id = Integer.parseInt(request.getParameter("id"));
        mav.setViewName("Tar/TarEliminar");
        //LLAMAR AL COMPONENTE PARA QUE ELIMINE
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8000);
            DefinicionComponenteTar definicionTar = (DefinicionComponenteTar) registry.lookup("tar");
            String respuesta = definicionTar.Eliminar(id);
            mav.addObject("respuestaEliminar", respuesta);
        } catch (Exception ex) {
            Logger.getLogger("Error");
            String respuesta = "Servicio no disponible \n" + ex.toString();
            mav.addObject("respuestaerror", respuesta);
        }
        return mav;
    }

    @RequestMapping(value = "TarEditar.htm", method = RequestMethod.GET)
    public ModelAndView laboratorioEditar(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int id = Integer.parseInt(request.getParameter("id"));
        mav.setViewName("Tar/TarEditar");
        String sqllin ="select * from linea_de_nivel;";
        List datoslineas = this.jdbcTemplate.queryForList(sqllin);
        mav.addObject("lineas",datoslineas);
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8000);
            DefinicionComponenteTar definicionTar = (DefinicionComponenteTar) registry.lookup("tar");
            String response[] = definicionTar.consultaIndividual(id);
            String nombre = response[0];
            String accion = response[1];
            String idTar = response[2];
            int idtar =Integer.parseInt(idTar);
            String idExist = response[3];
            int idexist = Integer.parseInt(idExist);
            String idLinea = response[4];
            int idlinea = Integer.parseInt(idLinea);
            
            TAR tar = new TAR(id, idexist, idlinea, nombre, accion);
            mav.addObject("tar", tar);
        } catch (Exception ex) {
            Logger.getLogger("Error");
            String respuesta = "Servicio no disponible \n" + ex.toString();
            mav.addObject("respuestaerror", respuesta);
        }
        return mav;
    }
    
    @RequestMapping(value = "TarEditar.htm", method = RequestMethod.POST)
    public ModelAndView laboratorioEditarPost(@ModelAttribute("tar") TAR tar,
            BindingResult result,
            SessionStatus status,
            HttpServletRequest request
    ) {
        int id = Integer.parseInt(request.getParameter("id"));
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Tar/TarEditarExito");
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8000);
            DefinicionComponenteTar definicionTar = (DefinicionComponenteTar) registry.lookup("tar");
            String response = definicionTar.Actualizar(
                    id, tar.getNombreComercial(),
                    tar.getAccionFarmacologica(), tar.getIdLineaNivel()
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
