
package Controllers;

import Modelos.Conectar;
import Modelos.ExistenciaMaterialMedico;
import Modelos.Laboratorio;
import Modelos.TAR;
import componentes.DefinicionComponenteMaterial;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
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
        mav.setViewName("TarAdd");
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
                mav.setViewName("TarAdd");
                try {
                    Registry registry = LocateRegistry.getRegistry("localhost", 8000);
                    DefinicionComponenteMaterial definicionMaterial = (DefinicionComponenteMaterial) registry.lookup("material");
                    
                    String response = definicionMaterial.insertartar(
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
}
