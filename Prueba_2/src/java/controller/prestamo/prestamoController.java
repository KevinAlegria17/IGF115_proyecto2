
package controller.prestamo;

import java.util.List;
import modelos.Conectar;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class prestamoController {
    private JdbcTemplate jdbcTemplate;

    public prestamoController() {
        //Conectar con = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(Conectar.conectar());
    }
    
    @RequestMapping("prestamos.htm")
    public ModelAndView prestamo(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("prestamos");
        String sql ="select * from prestamo;";
        List prestamos = this.jdbcTemplate.queryForList(sql);
        mav.addObject("prestamos",prestamos);
        
        return mav;
    }
}
