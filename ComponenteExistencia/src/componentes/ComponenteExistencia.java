
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
import java.util.ArrayList;
import java.util.List;

public class ComponenteExistencia implements DefinicionComponenteExistencia, Serializable{

    private ComponenteExistencia(){
        
    }
    
    public static ComponenteExistencia getInstance(){
        return new ComponenteExistencia();
    }

    @Override
    public String insertarlab(
            Integer id,Integer codigo,String nombreExist, String fecha, 
            Integer existencia,Integer faltante,String observacion,
            Integer idLaboratorio,
            String tipoFabricacion, String uso, String presentacion
    ) throws RemoteException 
    {
        Conectar con= new Conectar();
        String respuesta =null;
        try {
            String consulta1= "insert into existenciamaterialmedico "
                    + "(ID_EXIST, CODIGO_EXIST, NOMBRE_MATERIAL, FECHACONTEO, CANTIDADEXISTENCIA, CANTIDADFALTANTE, OBSERVACION)"
                    + "values (?,?,?,?,?,?,?);";
            PreparedStatement prepStatement = con.getConn().prepareStatement(consulta1);
            prepStatement.setInt(1, id);
            prepStatement.setInt(2, codigo);
            prepStatement.setString(3, nombreExist);
            prepStatement.setString(4, fecha);
            prepStatement.setInt(5, existencia);
            prepStatement.setInt(6, faltante);
            prepStatement.setString(7, observacion);
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
            respuesta = "\n Material de laboratorio insertado correctamente!";
            
        } catch (SQLException ex) {
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
            System.out.print("Actualizado correctamente");
            return respuesta;
            
        } catch (SQLException ex) {
            respuesta = "\nFallo en el proceso "+ex.toString()+"";
            System.out.print("Fallo en la actualizacion");
            return respuesta;
            
        }
    }
    
    @Override
    public List verMaterialesLaboratorio() throws RemoteException{
        Conectar con = new Conectar();
        List<Laboratorio> lista = new ArrayList<Laboratorio>();
        try{
            String sentenciaSql="SELECT * FROM existencialaboratorio";
            Statement statement = con.getConn().createStatement();
           
            ResultSet resultado = statement.executeQuery(sentenciaSql);
            
            while(resultado.next()){
                Laboratorio lab = new Laboratorio();
                
                lab.setIdExistencia(resultado.getInt("ID_EXIST"));
                lab.setId(resultado.getInt("ID_LABORATORIO"));
                lab.setTipoFabricacion(resultado.getString("TIPOFABRICACION"));
                 System.out.print(lab.getTipoFabricacion());
                lab.setUso(resultado.getString("USO"));
                lab.setFormaPresentacion(resultado.getString("FORMAPRESENTACION"));
                
                System.out.print(lab.toString());            
                
                lista.add(lab);
            }
            System.out.print("\n Resultado enviado");
            return lista;
            
        }catch(SQLException e){
            
            System.err.print("\n Fallo al leer la base");
            return lista;
        }
        
    }
    
    @Override
    public String[] consultaIndividualLab(int id) throws RemoteException{
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
    public String EliminarLab(int id) throws RemoteException {
        Conectar con= new Conectar();
        String respuesta =new String();
        int temp=0;
        try {
            String sentenciaSql="SELECT * FROM existencialaboratorio where ID_LABORATORIO="+id;
            PreparedStatement statement = con.getConn().prepareStatement(sentenciaSql);
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()){
                temp = resultado.getInt("ID_EXIST");
                }
            
            String consulta= "DELETE FROM existencialaboratorio WHERE ID_LABORATORIO="+id;
            PreparedStatement prepStatement = con.getConn().prepareStatement(consulta);
            prepStatement.executeUpdate();
            
            String consulta2= "DELETE FROM existenciamaterialmedico WHERE ID_EXIST="+temp;
            PreparedStatement prepStatement2 = con.getConn().prepareStatement(consulta2);
            prepStatement2.executeUpdate();
            
            con.getConn().close();
            respuesta = "\n Material de laboratorio con ID "+id+" \n Eliminado correctamente!";
            return respuesta;
        } catch (SQLException ex) {
            respuesta = "\nFallo en el proceso de eliminar"+ex.toString()+"";
            return respuesta;
        }
    }
    
    @Override
    public String insertartar(
            Integer id,Integer codigo,String nombreExist,String fecha, 
            Integer existencia,Integer faltante,String observacion,
            Integer idTar,
            Integer lineaNivel, String nombre, String accionFarmacologica
    ) throws RemoteException 
    {
        Conectar con= new Conectar();
        String respuesta =null;
        try {
            String consulta1= "insert into existenciamaterialmedico "
                    + "(ID_EXIST, CODIGO_EXIST, NOMBRE_MATERIAL, FECHACONTEO, CANTIDADEXISTENCIA, CANTIDADFALTANTE, OBSERVACION)"
                    + "values (?,?,?,?,?,?,?);";
            PreparedStatement prepStatement = con.getConn().prepareStatement(consulta1);
            prepStatement.setInt(1, id);
            prepStatement.setInt(2, codigo);
            prepStatement.setString(3, nombreExist);
            prepStatement.setString(4, fecha);
            prepStatement.setInt(5, existencia);
            prepStatement.setInt(6, faltante);
            prepStatement.setString(7, observacion);
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
    public String[] consultaIndividualTar(int id) throws RemoteException{
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
        int temp=0;
        try {
            String sentenciaSql="SELECT * FROM existenciatar where ID_TAR="+id;
            PreparedStatement statement = con.getConn().prepareStatement(sentenciaSql);
            ResultSet resultado = statement.executeQuery();
            while(resultado.next()){
                temp = resultado.getInt("ID_EXIST");
                }
            
            String consulta= "DELETE FROM existenciatar WHERE ID_TAR="+id;
            PreparedStatement prepStatement = con.getConn().prepareStatement(consulta);
            prepStatement.executeUpdate();
            
            String consulta2 = "DELETE FROM existenciamaterialmedico WHERE ID_EXIST="+temp;
            PreparedStatement prepStatement2 = con.getConn().prepareStatement(consulta2);
            prepStatement2.executeUpdate();
            
            con.getConn().close();
            respuesta = "\n Material TAR con ID "+id+" \n Eliminado correctamente!";
            return respuesta;
        } catch (SQLException ex) {
            respuesta = "\nFallo en el proceso de eliminar TAR"+ex.toString()+"";
            return respuesta;
        }
    }

    public static void main(String[] args) {
        try {
            ComponenteExistencia existencia = new ComponenteExistencia();
            
            System.setProperty("java.rmi.server.hostname", InetAddress.getLocalHost().getHostAddress());
            
            DefinicionComponenteExistencia definicionExistencia = (DefinicionComponenteExistencia) UnicastRemoteObject.exportObject(existencia, 8000);
            
            Registry registry = LocateRegistry.createRegistry(8000);
            registry.bind("existencia", definicionExistencia);
            
            System.out.print("Componente Existencias esperando llamada");
            
            
        } catch (Exception ex) {
            Logger.getLogger(ComponenteExistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
