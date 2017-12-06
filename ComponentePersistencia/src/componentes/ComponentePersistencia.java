
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

public class ComponentePersistencia implements DefinicionComponentePersistencia, Serializable{

    private ComponentePersistencia(){
        
    }
    
    public static ComponentePersistencia getInstance(){
        return new ComponentePersistencia();
    }

    @Override
    public String InsertarLab(
            Integer id,Integer codigo,String fecha, 
            Integer existencia,Integer faltante,String observacion,
            Integer idLaboratorio,
            String tipoFabricacion, String uso, String presentacion
    ) throws RemoteException 
    {
        Conectar con= new Conectar();
        String respuesta =null;
        try {
            String consulta1= "insert into existenciamaterialmedico "
                    + "(ID_EXIST, CODIGO_EXIST, FECHACONTEO, CANTIDADEXISTENCIA, CANTIDADFALTANTE, OBSERVACION)"
                    + "values (?,?,?,?,?,?);";
            PreparedStatement prepStatement = con.getConn().prepareStatement(consulta1);
            prepStatement.setInt(1, id);
            prepStatement.setInt(2, codigo);
            prepStatement.setString(3, fecha);
            prepStatement.setInt(4, existencia);
            prepStatement.setInt(5, faltante);
            prepStatement.setString(6, observacion);
            prepStatement.executeUpdate();
            
            String consulta2= "insert into existencialaboratorio "
                    + "(TIPOFABRICACION, USO, FORMAPRESENTACION, ID_LABORATORIO, ID_EXIST)"
                    + "values (?,?,?,?,?);";
            PreparedStatement prepStatement2 = con.getConn().prepareStatement(consulta2);
            prepStatement2.setString(1, tipoFabricacion);
            prepStatement2.setString(2, uso);
            prepStatement2.setString(3, presentacion);
            prepStatement2.setInt(4, idLaboratorio);
            prepStatement2.setInt(5, id);
            prepStatement2.executeUpdate();
            
            con.getConn().close();
            System.out.print("\n Material de laboratorio insertado correctamente!");
            respuesta = "\n Material de laboratorio insertado correctamente!";
            
        } catch (SQLException ex) {
            System.out.print("\nFallo en el proceso de insercion");
            respuesta = "\nFallo en el proceso"+ex.toString()+"";
            
        }
        
        System.out.print(respuesta);
        return respuesta;
        
    }
    
    @Override
    public String ActualizarLab(int id, String tipo, String uso, String presentacion) throws RemoteException{
        Conectar con= new Conectar();
        String respuesta = null;
        try {
            String consulta= "UPDATE existencialaboratorio SET TIPOFABRICACION=?, USO=?, FORMAPRESENTACION=? WHERE ID_LABORATORIO=?";
                    
            PreparedStatement prepStatement = con.getConn().prepareStatement(consulta);
            prepStatement.setString(1, tipo);
            prepStatement.setString(2, uso);
            prepStatement.setString(3, presentacion);
            prepStatement.setInt(4, id);
            prepStatement.executeUpdate();
            con.getConn().close();
            respuesta = "\n Material De laboratorio con id "+id+" ...actualizado correctamente!";
            System.out.print("\n Material De laboratorio Actualizado correctamente");
            return respuesta;
            
        } catch (SQLException ex) {
            respuesta = "\nFallo en el proceso de actualizacion de material de laboratorio"+ex.toString()+"";
            System.out.print("\n Fallo en la actualizacion");
            return respuesta;
            
        }
    }
    
    @Override
    public String[] ConsultaIndividualLab(int id) throws RemoteException{
        Conectar con = new Conectar();
        String resultadoconsulta[] = new String[5];
        try{
            String sentenciaSql="SELECT * FROM existencialaboratorio where ID_LABORATORIO="+id;
            PreparedStatement statement = con.getConn().prepareStatement(sentenciaSql);
           
            ResultSet resultado = statement.executeQuery();
            
            while(resultado.next()){
                resultadoconsulta[0] = Integer.toString(resultado.getInt("ID_EXIST"));
                resultadoconsulta[1] = Integer.toString(resultado.getInt("ID_LABORATORIO"));
                resultadoconsulta[2]= resultado.getString("TIPOFABRICACION");
                resultadoconsulta[3]= resultado.getString("USO");
                resultadoconsulta[4]= resultado.getString("FORMAPRESENTACION");
                }
            System.out.print("\n Consulta exitosa material de laboratorio enviado");
            return resultadoconsulta;
            
        }catch(SQLException e){
            
            System.err.print("\n Fallo al leer la base");
            resultadoconsulta[0] = "Fallo al recuperar los datos pedidos";
            resultadoconsulta[1] = "Esto es una prueba ";
            resultadoconsulta[2] = "Mas pruebas";
            resultadoconsulta[3] = "Mas pruebas ... :/";
            
            return resultadoconsulta;
        }
    }
    
    @Override
    public String EliminarLab(int id) throws RemoteException {
        Conectar con= new Conectar();
        String respuesta =new String();
        try {
            String consulta= "DELETE FROM existencialaboratorio WHERE ID_LABORATORIO="+id;
            PreparedStatement prepStatement = con.getConn().prepareStatement(consulta);
            prepStatement.executeUpdate();
            con.getConn().close();
            System.out.print("\n Material de laboratorio eliminado");
            respuesta = "\n Material de laboratorio con ID "+id+" \n Eliminado correctamente!";
            return respuesta;
        } catch (SQLException ex) {
            System.out.print("\n Fallo en el proceso de eliminacion");
            respuesta = "\nFallo en el proceso de eliminar"+ex.toString()+"";
            return respuesta;
        }
    }

    @Override
    public String InsertarTar(
            Integer id,Integer codigo,String fecha, 
            Integer existencia,Integer faltante,String observacion,
            Integer idTar,
            Integer lineaNivel, String nombre, String accionFarmacologica
    ) throws RemoteException 
    {
        Conectar con= new Conectar();
        String respuesta =null;
        try {
            String consulta1= "insert into existenciamaterialmedico "
                    + "(ID_EXIST, CODIGO_EXIST, FECHACONTEO, CANTIDADEXISTENCIA, CANTIDADFALTANTE, OBSERVACION)"
                    + "values (?,?,?,?,?,?);";
            PreparedStatement prepStatement = con.getConn().prepareStatement(consulta1);
            prepStatement.setInt(1, id);
            prepStatement.setInt(2, codigo);
            prepStatement.setString(3, fecha);
            prepStatement.setInt(4, existencia);
            prepStatement.setInt(5, faltante);
            prepStatement.setString(6, observacion);
            prepStatement.executeUpdate();
            
            String consulta2= "insert into existenciatar "
                    + "(NOMBRECOMERCIAL, ACCIONFARMACOLOGICA, ID_TAR, ID_EXIST, ID_LINEA)"
                    + "values (?,?,?,?,?);";
            PreparedStatement prepStatement2 = con.getConn().prepareStatement(consulta2);
            prepStatement2.setString(1, nombre);
            prepStatement2.setString(2, accionFarmacologica);
            prepStatement2.setInt(3, idTar);
            prepStatement2.setInt(4, id);
            prepStatement2.setInt(5, lineaNivel);
            prepStatement2.executeUpdate();
            
            con.getConn().close();
            respuesta = "\n Material TAR insertado correctamente!";
            
        } catch (SQLException ex) {
            respuesta = "\nFallo en el proceso de insercion"+ex.toString()+"";
            
        }
        
        System.out.print(respuesta);
        return respuesta;
        
    }
    
    @Override
    public String ActualizarTar(int id, String nombre, String accion, int linea) throws RemoteException{
        Conectar con= new Conectar();
        String respuesta = null;
        try {
            String consulta= "UPDATE existenciatar SET NOMBRECOMERCIAL=?, ACCIONFARMACOLOGICA=?, ID_LINEA=? WHERE ID_TAR=?";
                    
            PreparedStatement prepStatement = con.getConn().prepareStatement(consulta);
            prepStatement.setString(1, nombre);
            prepStatement.setString(2, accion);
            prepStatement.setInt(3, linea);
            prepStatement.setInt(4, id);
            prepStatement.executeUpdate();
            con.getConn().close();
            respuesta = "\n Material TAR con id "+id+" ...actualizado correctamente!";
            System.out.print("Actualizado correctamente");
            return respuesta;
            
        } catch (SQLException ex) {
            respuesta = "\nFallo en el proceso "+ex.toString()+"";
            System.out.print("Fallo en la actualizacion de TAR");
            return respuesta;
            
        }
    }
    
    @Override
    public String[] ConsultaIndividualTar(int id) throws RemoteException{
        Conectar con = new Conectar();
        String resultadoconsulta[] = new String[5];
        try{
            String sentenciaSql="SELECT * FROM existenciatar where ID_TAR="+id;
            PreparedStatement statement = con.getConn().prepareStatement(sentenciaSql);
           
            ResultSet resultado = statement.executeQuery();
            
            while(resultado.next()){
                resultadoconsulta[0] = resultado.getString("NOMBRECOMERCIAL");
                resultadoconsulta[1] = resultado.getString("ACCIONFARMACOLOGICA");
                resultadoconsulta[2]= Integer.toString(resultado.getInt("ID_TAR"));
                resultadoconsulta[3]= Integer.toString(resultado.getInt("ID_EXIST"));
                resultadoconsulta[4]= Integer.toString(resultado.getInt("ID_LINEA"));
                }
            System.out.print("\n Resultado enviado");
            return resultadoconsulta;
            
        }catch(SQLException e){
            
            System.err.print("\n Fallo al leer la base");
            resultadoconsulta[0] = "Fallo al recuperar los datos pedidos";
            resultadoconsulta[1] = "Esto es una prueba ";
            resultadoconsulta[2] = "Mas pruebas";
            resultadoconsulta[3] = "Mas pruebas ... :/";
            
            return resultadoconsulta;
        }
    }
    
    @Override
    public String EliminarTar(int id) throws RemoteException {
        Conectar con= new Conectar();
        String respuesta =new String();
        try {
            String consulta= "DELETE FROM existenciatar WHERE ID_TAR="+id;
            PreparedStatement prepStatement = con.getConn().prepareStatement(consulta);
            prepStatement.executeUpdate();
            con.getConn().close();
            respuesta = "\n Material TAR con ID "+id+" \n Eliminado correctamente!";
            return respuesta;
        } catch (SQLException ex) {
            respuesta = "\nFallo en el proceso de eliminar TAR"+ex.toString()+"";
            return respuesta;
        }
    }
    
    @Override
    public String InsertarPersonal(int id, String area, int cantidad, int porcentaje) throws RemoteException {
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
    public String[] ConsultaIndividualPersonal(int id) throws RemoteException {
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
    public String EliminarPersonal(int id) throws RemoteException {
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
    public String ActualizarPersonal(int id, String area, int cantidad_personal, int porcentaje) throws RemoteException {
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
    
    public static void main(String[] args) {
        try {
            ComponentePersistencia persistencia = new ComponentePersistencia();
            
            System.setProperty("java.rmi.server.hostname", InetAddress.getLocalHost().getHostAddress());
            
            DefinicionComponentePersistencia definicionPersistencia = (DefinicionComponentePersistencia) UnicastRemoteObject.exportObject(persistencia, 8000);
            
            Registry registry = LocateRegistry.createRegistry(8000);
            registry.bind("persistencia", definicionPersistencia);
            
            System.out.print("Componente Persistencia esperando llamada");
            
            
        } catch (Exception ex) {
            Logger.getLogger(ComponentePersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    @Override
//    public List verMaterialesLaboratorio() throws RemoteException{
//        Conectar con = new Conectar();
//        List<Laboratorio> lista = new ArrayList<Laboratorio>();
//        try{
//            String sentenciaSql="SELECT * FROM existencialaboratorio";
//            Statement statement = con.getConn().createStatement();
//           
//            ResultSet resultado = statement.executeQuery(sentenciaSql);
//            
//            while(resultado.next()){
//                Laboratorio lab = new Laboratorio();
//                
//                lab.setIdExistencia(resultado.getInt("ID_EXIST"));
//                lab.setId(resultado.getInt("ID_LABORATORIO"));
//                lab.setTipoFabricacion(resultado.getString("TIPOFABRICACION"));
//                 System.out.print(lab.getTipoFabricacion());
//                lab.setUso(resultado.getString("USO"));
//                lab.setFormaPresentacion(resultado.getString("FORMAPRESENTACION"));
//                
//                System.out.print(lab.toString());            
//                
//                lista.add(lab);
//            }
//            System.out.print("\n Resultado enviado");
//            return lista;
//            
//        }catch(SQLException e){
//            
//            System.err.print("\n Fallo al leer la base");
//            return lista;
//        }
//        
//    }
//    
}
