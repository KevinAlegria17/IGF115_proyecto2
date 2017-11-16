
package controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import modelos.Categoria;
import modelos.Conectar;
import modelos.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class categoriaController {
    private JdbcTemplate jdbcTemplate;

    public categoriaController() {
        //Conectar con = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(Conectar.conectar());
    }
    
    
    @RequestMapping(value="categorias.htm")
    public ModelAndView categoria(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("categorias");
        String sql ="select * from categoria;";
        List datos = this.jdbcTemplate.queryForList(sql);
        mav.addObject("datos",datos);
        return mav;
    }

    @RequestMapping(value="deleteCat.htm",method=RequestMethod.GET)
    public ModelAndView form(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("id"));
        this.jdbcTemplate.update(
                "delete from categoria where id=?", id
        );
        return new ModelAndView("redirect:/categorias.htm");
    }
    
    @RequestMapping(value="categoriaAdd.htm",method=RequestMethod.GET)
    public ModelAndView form(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("categoriaAdd");
        mav.addObject("categoria", new Categoria());
        return mav;
    }
    
    @RequestMapping(value="categoriaAdd.htm",method=RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("categoria") Categoria c,
                             BindingResult result,
                             SessionStatus status
            )
    {
            this.jdbcTemplate.update(
                    "insert into categoria (nombre, codigo) values (?,?);",
                    c.getNombre(), c.getCodigo()
            );
            return new ModelAndView("redirect:/categorias.htm");
    }
    
}
