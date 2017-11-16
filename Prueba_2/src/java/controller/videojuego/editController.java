
package controller.videojuego;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import modelos.Conectar;
import modelos.Videojuego;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("VJedit.htm")
public class editController {
    private JdbcTemplate jdbcTemplate;
    
    public editController(){
        //Conectar con = new Conectar();
        this.jdbcTemplate=new JdbcTemplate(Conectar.conectar());
    }
    
    public Videojuego selectVideojuego(int id){
        final Videojuego videojuego = new Videojuego();
        String consulta = "Select * from videojuego where id ='"+id+"'";
        return (Videojuego) jdbcTemplate.query
        (
                consulta, new ResultSetExtractor<Videojuego>()
            {
               public Videojuego extractData(ResultSet rs)throws SQLException, DataAccessException{
                   if(rs.next())
                   {
                       videojuego.setNombre(rs.getString("nombre"));
                       videojuego.setCategoria(rs.getInt("categoria"));
                       videojuego.setConsola(rs.getInt("consola"));
                   }
                   return videojuego;
               } 
            }
        );
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView form(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        int id=Integer.parseInt(request.getParameter("id"));
        Videojuego datos=this.selectVideojuego(id);
        mav.setViewName("VJedit");
        mav.addObject("videojuego", new Videojuego(id, datos.getCategoria(),datos.getConsola(), datos.getNombre()));
        
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
                             SessionStatus status,
                             HttpServletRequest request
            )
    {
            int Id=Integer.parseInt(request.getParameter("id"));
            this.jdbcTemplate.update(
                    "update videojuego set categoria=?, consola=?, nombre=? where id=?",
                    vj.getCategoria(), vj.getConsola(), vj.getNombre(), Id
            );
            return new ModelAndView("redirect:/videojuegos.htm");
        }
}

