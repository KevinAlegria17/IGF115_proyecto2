package controller.videojuego;

import java.util.List;
import modelos.Conectar;
import modelos.Videojuego;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("VJadd.htm")
public class addController {
    private JdbcTemplate jdbcTemplate;
    
    public addController(){
        //Conectar con = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(Conectar.conectar());
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView form(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("VJadd");
        mav.addObject("videojuego", new Videojuego());
        
        String sqlcat ="select * from categoria;";
        List datoscat = this.jdbcTemplate.queryForList(sqlcat);
        mav.addObject("cats",datoscat);
        
        String sqlcon ="select * from consola;";
        List datoscon = this.jdbcTemplate.queryForList(sqlcon);
        mav.addObject("cons",datoscon);
        
        return mav;
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("videojuego") Videojuego vj,
                             BindingResult result,
                             SessionStatus status
            )
    {
            this.jdbcTemplate.update(
                    "insert into videojuego (categoria, consola, nombre) values (?,?,?)",
                    vj.getCategoria(), vj.getConsola(), vj.getNombre()
            );
            return new ModelAndView("redirect:/videojuegos.htm");
    }
}
