
package controller;

import java.util.List;
import modelos.Conectar;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class consolaController {
    private JdbcTemplate jdbcTemplate;

    public consolaController() {
        //Conectar con = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(Conectar.conectar());
    }
    
    
    @RequestMapping("consolas.htm")
    public ModelAndView consola(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("consolas");
        String sql ="select * from consola;";
        List datos = this.jdbcTemplate.queryForList(sql);
        mav.addObject("datos",datos);
        return mav;
    }
}
