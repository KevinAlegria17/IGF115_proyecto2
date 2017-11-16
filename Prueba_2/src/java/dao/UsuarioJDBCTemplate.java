
package dao;

import java.util.List;
import javax.sql.DataSource;
import modelos.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;

public class UsuarioJDBCTemplate implements UsuarioDao{
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void Agregar(String nombre, Integer prestamo) {
        String SQL = "insert into usuario (nombre, prestamo) values (?, ?)";
        jdbcTemplateObject.update(SQL, nombre, prestamo);
        return;
    }

    public Usuario getUsuario(Integer id) {
        String SQL = "select * from usuario where id = ?";
        Usuario usuario = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{id}, new UsuarioMapper());
        return usuario;
    }

    public List<Usuario> listarUsuarios() {
        String SQL = "select * from usuario";
        List<Usuario> usuarios = jdbcTemplateObject.query(SQL,
                new UsuarioMapper());
        return usuarios;
    }

    public void Eliminar(Integer id) {
        String SQL = "delete from usuario where id = ?";
        jdbcTemplateObject.update(SQL, id);
        return;
    }

    public void Actualizar(Integer id, Integer prestamo) {
        String SQL = "update usuario set prestamo = ? where id = ?";
        jdbcTemplateObject.update(SQL, prestamo, id);
        return;
    }
    
    
}
