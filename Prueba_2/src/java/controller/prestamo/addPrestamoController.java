
package controller.prestamo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import modelos.Conectar;
import modelos.Prestamo;
import modelos.Usuario;
import org.apache.jasper.tagplugins.jstl.ForEach;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("addPrestamo.htm")
public class addPrestamoController {
    private JdbcTemplate jdbcTemplate;
    //pruebas
    private DataSource dataSource;
    
    public addPrestamoController(){
        //Conectar con = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(Conectar.conectar());
    }
    //pruebas
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    //pruebas
    public void insert(Usuario customer) {

        String sql = "INSERT INTO CUSTOMER "
                + "(CUST_ID, NAME, AGE) VALUES (?, ?, ?)";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, customer.getPrestamo());
            ps.setString(2, customer.getNombre());
            ps.setInt(3, customer.getPrestamo());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView form(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("addPrestamo");
        mav.addObject("prestamo", new Prestamo());
        
        String sqlcat ="select * from videojuego;";
        List datosvj = this.jdbcTemplate.queryForList(sqlcat);
        mav.addObject("vjs",datosvj);
        
        String sqlcon ="select * from usuario;";
        List datosusu = this.jdbcTemplate.queryForList(sqlcon);
        mav.addObject("users",datosusu);
        
        
        return mav;
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("prestamo") Prestamo p,
                             BindingResult result,
                             SessionStatus status
            ) throws SQLException
    {
        
        String sql ="select prestamo from usuario where id="+p.getUsuario()+";";
        Integer user = this.jdbcTemplate.queryForInt(sql);
        int prestamo = user;
        if(prestamo==1){
            ModelAndView mav = new ModelAndView();
            mav.setViewName("addPrestamo");
            mav.addObject("prestamo", new Prestamo());

            String sqlcat = "select * from videojuego;";
            List datosvj = this.jdbcTemplate.queryForList(sqlcat);
            mav.addObject("vjs", datosvj);

            String sqlcon = "select * from usuario;";
            List datosusu = this.jdbcTemplate.queryForList(sqlcon);
            mav.addObject("users", datosusu);

            return mav;

        }else{
            this.jdbcTemplate.update(
                            "insert into prestamo (videojuego, usuario) values (?,?);",
                            p.getVideojuego(), p.getUsuario()
                    );
        }
        
            return new ModelAndView("redirect:/prestamos.htm");
    }
}
