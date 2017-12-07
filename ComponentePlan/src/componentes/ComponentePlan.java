
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

public class ComponentePlan implements DefinicionComponentePlan, Serializable{

    private ComponentePlan(){
        
    }
    
    public static ComponentePlan getInstance(){
        return new ComponentePlan();
    }

    @Override
    public String insertarCompra(int id, String periodo, String fechaInicio, String fechaFin) throws RemoteException 
    {
        Conectar con= new Conectar();
        String respuesta =null;
        try {
            String consulta1= "insert into planificacion (ID_PLANIFICACION ,FECHA_INICIO, FECHA_FIN)"
                    + "values (?,?,?);";
            PreparedStatement prepStatement = con.getConn().prepareStatement(consulta1);
            prepStatement.setInt(1, id);
            prepStatement.setString(2, fechaInicio);
            prepStatement.setString(3, fechaFin);
            prepStatement.executeUpdate();
            
            String consulta2= "insert into compra (PERIODO_COMPRA, ID_PLANIFICACION)"
                    + "values (?,?);";
            PreparedStatement prepStatement2 = con.getConn().prepareStatement(consulta2);
            prepStatement2.setString(1, periodo);
            prepStatement2.setInt(2, id);
            prepStatement2.executeUpdate();
            
            con.getConn().close();
            respuesta = "\n Plan de compra insertado correctamente!";
            
        } catch (SQLException ex) {
            respuesta = "\nFallo en el proceso"+ex.toString()+"";
            
        }
        
        System.out.print(respuesta);
        return respuesta;
        
    }
    
    public static void main(String[] args) {
        try {
            ComponentePlan plan = new ComponentePlan();
            
            System.setProperty("java.rmi.server.hostname", InetAddress.getLocalHost().getHostAddress());
            
            DefinicionComponentePlan definicionPlan = (DefinicionComponentePlan) UnicastRemoteObject.exportObject(plan, 8000);
            
            Registry registry = LocateRegistry.createRegistry(8000);
            registry.bind("plan", definicionPlan);
            
            System.out.print("Componente Planificacion esperando llamada");
            
            
        } catch (Exception ex) {
            Logger.getLogger(ComponentePlan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
