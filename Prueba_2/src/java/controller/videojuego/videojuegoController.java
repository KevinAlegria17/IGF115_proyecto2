
package controller.videojuego;

import java.util.List;
import modelos.Conectar;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class videojuegoController {
    
    private JdbcTemplate jdbcTemplate;

    public videojuegoController() {
        //Conectar con = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(Conectar.conectar());
    }
   
    @RequestMapping("videojuegos.htm")
    public ModelAndView videojuego(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("videojuegos");
        String sql ="select * from videojuego;";
        List datos = this.jdbcTemplate.queryForList(sql);
        mav.addObject("datos",datos);
        return mav;
    }
    
}
