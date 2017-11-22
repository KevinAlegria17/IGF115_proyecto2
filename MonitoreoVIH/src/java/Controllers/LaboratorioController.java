
package Controllers;

import Modelos.Conectar;
import Modelos.ExistenciaMaterialMedico;
import Modelos.Laboratorio;
import java.util.List;
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
        this.jdbcTemplate =  new JdbcTemplate(Conectar.conectar());
    }
    
    @RequestMapping(value="MaterialLaboratorioAdd.htm",method=RequestMethod.GET)
    public ModelAndView form(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("MaterialLaboratorioAdd");
        mav.addObject("existencia", new ExistenciaMaterialMedico());
        return mav;
    }
    
    @RequestMapping(value="MaterialLaboratorioAdd.htm",method=RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("existencia") ExistenciaMaterialMedico emm,
                             BindingResult result,
                             SessionStatus status
            )
    {
            /*this.jdbcTemplate.update(
                    "insert into existenciamaterialmedico "
                    + "(ID_EXIST, CODIGO_EXIST, FECHACONTEO, CANTIDADEXISTENCIA, CANTIDADFALTANTE, OBSERVACION)"
                    + "values (?,?,?,?,?,?);",
                    emm.getId(), emm.getCodigo(), emm.getFechaConteo(), emm.getCantidadExistencia(), emm.getCantidadFaltante(), emm.getObservacion()
            );*/
            this.existencia=emm;
            return new ModelAndView("redirect:/LaboratorioAdd.htm");
    }
    
    @RequestMapping(value="LaboratorioAdd.htm",method=RequestMethod.GET)
    public ModelAndView laboratorioAdd(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("LaboratorioAdd");
        mav.addObject("material-lab", new Laboratorio());
        return mav;
    }
    
     @RequestMapping(value="LaboratorioAdd.htm",method=RequestMethod.POST)
    public ModelAndView laboratorioAdd(@ModelAttribute("material-lab") Laboratorio lab,
                             BindingResult result,
                             SessionStatus status
            )
    {
            this.jdbcTemplate.update(
                    "insert into existenciamaterialmedico "
                    + "(ID_EXIST, CODIGO_EXIST, FECHACONTEO, CANTIDADEXISTENCIA, CANTIDADFALTANTE, OBSERVACION)"
                    + "values (?,?,?,?,?,?);",
                    existencia.getId(), existencia.getCodigo(), existencia.getFechaConteo(), 
                    existencia.getCantidadExistencia(), existencia.getCantidadFaltante(), existencia.getObservacion()
            );
            this.jdbcTemplate.update(
                    "insert into existencialaboratorio "
                    + "(TIPOFABRICACION, USO, FORMAPRESENTACION, ID_LABORATORIO, ID_EXIST)"
                    + "values (?,?,?,?,?);",
                    lab.getTipoFabricacion(), lab.getUso(),lab.getFormaPresentacion(), 
                    lab.getId(), existencia.getId()
            );
            
            return new ModelAndView("redirect:/index.htm");
    }
}
