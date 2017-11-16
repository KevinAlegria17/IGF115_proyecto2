
package controller.usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import modelos.Conectar;
import modelos.Usuario;
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
@RequestMapping("editUser.htm")
public class editUserController {
    private JdbcTemplate jdbcTemplate;
    
    public editUserController(){
        //Conectar con = new Conectar();
        this.jdbcTemplate=new JdbcTemplate(Conectar.conectar());
    }
    
    public Usuario selectUsuario(int id){
        final Usuario usuario = new Usuario();
        String consulta = "Select * from usuario where id ='"+id+"'";
        return (Usuario) jdbcTemplate.query
        (
                consulta, new ResultSetExtractor<Usuario>()
            {
               public Usuario extractData(ResultSet rs)throws SQLException, DataAccessException{
                   if(rs.next())
                   {
                       usuario.setNombre(rs.getString("nombre"));
                       usuario.setPrestamo(rs.getInt("prestamo"));
                   }
                   return usuario;
               } 
            }
        );
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView form(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        int id=Integer.parseInt(request.getParameter("id"));
        Usuario datos=this.selectUsuario(id);
        mav.setViewName("editUser");
        mav.addObject("usuario", new Usuario(id, datos.getNombre(),datos.getPrestamo()));
        
        return mav;
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("usuario") Usuario u,
                             BindingResult result,
                             SessionStatus status,
                             HttpServletRequest request
            )
    {
            int Id=Integer.parseInt(request.getParameter("id"));
            this.jdbcTemplate.update(
                    "update usuario set nombre=?, prestamo=? where id=?",
                    u.getNombre(), u.getPrestamo(), Id
            );
            return new ModelAndView("redirect:/usuarios.htm");
        }
    
}
