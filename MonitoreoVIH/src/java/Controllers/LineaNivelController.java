
package Controllers;

import Modelos.ExistenciaMaterialMedico;
import Modelos.LineaNivel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LineaNivelController {
    private JdbcTemplate jdbcTemplate;
    
    @RequestMapping(value="MaterialLaboratorioAdd.htm",method=RequestMethod.GET)
    public ModelAndView form(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("LineaNivelAdd");
        mav.addObject("linea", new LineaNivel());
        return mav;
    }
}
