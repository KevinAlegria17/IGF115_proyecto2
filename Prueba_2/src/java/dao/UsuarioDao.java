package dao;

import java.util.List;
import javax.sql.DataSource;
import modelos.Usuario;

public interface UsuarioDao {
    public void setDataSource(DataSource ds);
    public void Agregar(String nombre, Integer prestamo);
    public Usuario getUsuario(Integer id);
    public List<Usuario> listarUsuarios();
    public void Eliminar(Integer id);
    public void Actualizar(Integer id, Integer prestamo);
    
}

/*  Codigo para poner en el dispatcher servlet 
<bean id="dataSource"
      class="org.springframework.jdbc.datasource.DriverManagerDataSource">
      <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
      <property name="url" value="jdbc:mysql://localhost/prueba_2"/>
      <property name="username" value="root"/>
      <property name="password" value=""/>
    </bean>
    
    <bean id="UsuarioJDBCTemplate" class="dao.UsuarioJDBCTemplate">
      <property name="dataSource"  ref="dataSource" />    
    </bean>
*/