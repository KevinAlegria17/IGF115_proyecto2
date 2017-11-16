
package controller.usuario;

import java.util.List;
import modelos.Conectar;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class usuarioController {
    private JdbcTemplate jdbcTemplate;

    public usuarioController() {
        //Conectar con = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(Conectar.conectar());
    }
    
    @RequestMapping("usuarios.htm")
    public ModelAndView usuario(ModelAndView model){
        model.setViewName("usuarios");
        String sql ="select * from usuario;";
        List datos = this.jdbcTemplate.queryForList(sql);
        model.addObject("datos",datos);
        return model;
    }
}
