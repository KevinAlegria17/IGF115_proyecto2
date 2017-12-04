
package componentes;

import java.io.Serializable;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

public class ComponentePersonal implements DefinicionComponentePersonal, Serializable{

    private ComponentePersonal(){
        
    }
    
    public static ComponentePersonal getInstance(){
        return new ComponentePersonal();
    }
    
    public static void main(String[] args) {
        try {
            ComponentePersonal personal = new ComponentePersonal();
            
            System.setProperty("java.rmi.server.hostname", InetAddress.getLocalHost().getHostAddress());
            
            DefinicionComponentePersonal definicionPersonal = (DefinicionComponentePersonal) UnicastRemoteObject.exportObject(personal, 8000);
            
            Registry registry = LocateRegistry.createRegistry(8000);
            registry.bind("personal", definicionPersonal);
            
            System.out.print("Servidor esperando llamada");
            
            
        } catch (Exception ex) {
            Logger.getLogger(ComponentePersonal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String insertarPersonal(int id, String area, int cantidad, int porcentaje) throws RemoteException {
        Conectar con= new Conectar();
        String respuesta =null;
        try {
            String consulta1= "insert into personal "
                    + "(ID_PERSONAL, AREA, CANTIDAD_PERSONAL_DISPONIBLE, PORCENTAJE_ROTACION)"
                    + "values (?,?,?,?);";
            PreparedStatement prepStatement = con.getConn().prepareStatement(consulta1);
            prepStatement.setInt(1, id);
            prepStatement.setString(2, area);
            prepStatement.setInt(3, cantidad);
            prepStatement.setInt(4, porcentaje);
            prepStatement.executeUpdate();
            
            con.getConn().close();
            respuesta = "\n Datos sobre Personal insertados correctamente!";
            
        } catch (SQLException ex) {
            respuesta = "\nFallo en el proceso"+ex.toString()+"";
            
        }
        return respuesta;
    }

    @Override
    public String[] consultaIndividual(int id) throws RemoteException {
        Conectar con = new Conectar();
        String resultadoconsulta[] = new String[4];
        try{
            String sentenciaSql="SELECT * FROM personal where ID_Personal="+id;
            PreparedStatement statement = con.getConn().prepareStatement(sentenciaSql);
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()){
                resultadoconsulta[0] = Integer.toString(resultado.getInt("ID_PERSONAL"));
                resultadoconsulta[1] = resultado.getString("AREA");
                resultadoconsulta[2] = Integer.toString(resultado.getInt("CANTIDAD_PERSONAL_DISPONIBLE"));
                resultadoconsulta[3] = Integer.toString(resultado.getInt("PORCENTAJE_ROTACION"));
                }
            System.out.print("\n Resultado de consulta de personal enviado");
            return resultadoconsulta;
            
        }catch(SQLException e){
            
            System.err.print("\n Fallo al realizar consulta de personal");
            resultadoconsulta[0] = "Fallo al recuperar los datos pedidos";
            resultadoconsulta[1] = "Esto es una prueba ";
            resultadoconsulta[2] = "Mas pruebas";
            resultadoconsulta[3] = "Mas pruebas ... :/";
            
            return resultadoconsulta;
        }
    }

    @Override
    public String Eliminar(int id) throws RemoteException {
        Conectar con= new Conectar();
        String respuesta =new String();
        try {
            String consulta= "DELETE FROM personal WHERE ID_PERSONAL="+id;
            PreparedStatement prepStatement = con.getConn().prepareStatement(consulta);
            prepStatement.executeUpdate();
            con.getConn().close();
            respuesta = "\n Personal de area con ID "+id+" \n Eliminada correctamente!";
            return respuesta;
        } catch (SQLException ex) {
            respuesta = "\nFallo en el proceso de eliminar Personal de area"+ex.toString()+"";
            return respuesta;
        }
    }

    @Override
    public String Actualizar(int id, String area, int cantidad_personal, int porcentaje) throws RemoteException {
        Conectar con= new Conectar();
        String respuesta = null;
        try {
            String consulta= "UPDATE personal SET AREA=?, CANTIDAD_PERSONAL_DISPONIBLE=?, PORCENTAJE_ROTACION=? WHERE ID_PERSONAL=?";
            PreparedStatement prepStatement = con.getConn().prepareStatement(consulta);
            prepStatement.setString(1, area);
            prepStatement.setInt(2, cantidad_personal);
            prepStatement.setInt(3, porcentaje);
            prepStatement.setInt(4, id);
            prepStatement.executeUpdate();
            con.getConn().close();
            respuesta = "\n Personal de area con id "+id+" ...actualizado correctamente!";
            System.out.print("Actualizado correctamente");
            return respuesta;
            
        } catch (SQLException ex) {
            respuesta = "\nFallo en el proceso de actualizacion de personal "+ex.toString()+"";
            System.out.print("Fallo en la actualizacion");
            return respuesta;
            
        }
    }
}
