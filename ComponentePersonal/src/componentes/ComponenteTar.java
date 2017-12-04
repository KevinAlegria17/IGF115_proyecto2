
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

public class ComponenteTar implements DefinicionComponenteTar, Serializable{

    private ComponenteTar(){
        
    }
    
    public static ComponenteTar getInstance(){
        return new ComponenteTar();
    }

    @Override
    public String insertartar(
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
    public String Actualizar(int id, String nombre, String accion, int linea) throws RemoteException{
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
    public String[] consultaIndividual(int id) throws RemoteException{
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
    public String Eliminar(int id) throws RemoteException {
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

    public static void main(String[] args) {
        try {
            ComponenteTar tar = new ComponenteTar();
            
            System.setProperty("java.rmi.server.hostname", InetAddress.getLocalHost().getHostAddress());
            
            DefinicionComponenteTar definicionTar = (DefinicionComponenteTar) UnicastRemoteObject.exportObject(tar, 8000);
            
            Registry registry = LocateRegistry.createRegistry(8000);
            registry.bind("tar", definicionTar);
            
            System.out.print("Servidor TAR esperando llamada");
            
            
        } catch (Exception ex) {
            Logger.getLogger(ComponenteTar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
