
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Usuario;
import org.springframework.jdbc.core.RowMapper;

public class UsuarioMapper implements RowMapper<Usuario> {

    public Usuario mapRow(ResultSet rs, int i) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getInt("id"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setPrestamo(rs.getInt("prestamo"));
        return usuario;
    }
    
}
