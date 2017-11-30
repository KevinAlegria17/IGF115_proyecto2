
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

public class ComponenteMaterial implements DefinicionComponenteMaterial, Serializable{

    private ComponenteMaterial(){
        
    }
    
    public static ComponenteMaterial getInstance(){
        return new ComponenteMaterial();
    }

    @Override
    public String insertarlab(
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
            respuesta = "\n Material de laboratorio insertado correctamente!";
            
        } catch (SQLException ex) {
            respuesta = "\nFallo en el proceso"+ex.toString()+"";
            
        }
        
        System.out.print(respuesta);
        return respuesta;
        
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
            respuesta = "\n Material TAR: "+nombre+" ...insertado correctamente!";
            
        } catch (SQLException ex) {
            respuesta = "\nFallo en el proceso"+ex.toString()+"";
            
        }
        
        System.out.print(respuesta);
        return respuesta;
        
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
    public String[] consultaIndividual(int id) throws RemoteException{
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
    public String Eliminar(int id) throws RemoteException {
        Conectar con= new Conectar();
        String respuesta =new String();
        try {
            String consulta= "DELETE FROM existencialaboratorio WHERE ID_LABORATORIO="+id;
            PreparedStatement prepStatement = con.getConn().prepareStatement(consulta);
            prepStatement.executeUpdate();
            con.getConn().close();
            respuesta = "\n Material de laboratorio con ID "+id+" \n Eliminado correctamente!";
            return respuesta;
        } catch (SQLException ex) {
            respuesta = "\nFallo en el proceso de eliminar"+ex.toString()+"";
            return respuesta;
        }
    }

    public static void main(String[] args) {
        try {
            ComponenteMaterial material = new ComponenteMaterial();
            
            System.setProperty("java.rmi.server.hostname", InetAddress.getLocalHost().getHostAddress());
            
            DefinicionComponenteMaterial definicionMaterial = (DefinicionComponenteMaterial) UnicastRemoteObject.exportObject(material, 8000);
            
            Registry registry = LocateRegistry.createRegistry(8000);
            registry.bind("material", definicionMaterial);
            
            System.out.print("Servidor esperando llamada");
            
            
        } catch (Exception ex) {
            Logger.getLogger(ComponenteMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
