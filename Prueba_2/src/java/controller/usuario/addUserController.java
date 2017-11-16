
package controller.usuario;

import java.util.List;
import modelos.Conectar;
import modelos.Usuario;
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
@RequestMapping("addUser.htm")
public class addUserController {
    private JdbcTemplate jdbcTemplate;
    
    public addUserController(){
        //Conectar con = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(Conectar.conectar());
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView form(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("addUser");
        mav.addObject("usuario", new Usuario());
        return mav;
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("usuario") Usuario u,
                             BindingResult result,
                             SessionStatus status
            )
    {
            this.jdbcTemplate.update(
                    "insert into usuario (nombre, prestamo) values (?,?);",
                    u.getNombre(), u.getPrestamo()
            );
            return new ModelAndView("redirect:/usuarios.htm");
    }
}
