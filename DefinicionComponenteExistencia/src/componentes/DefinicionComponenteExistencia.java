
package componentes;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface DefinicionComponenteExistencia extends Remote{
    
    public String insertarlab(
            Integer id,Integer codigo,String nombreExist, String fecha, 
            Integer existencia,Integer faltante,String observacion,
            Integer idLaboratorio,
            String tipoFabricacion, String uso, String presentacion
    ) throws RemoteException;
    
    public List verMaterialesLaboratorio() throws RemoteException;
    
    public String[] consultaIndividualLab(int id) throws RemoteException;
    
    public String EliminarLab(int id) throws RemoteException;
    
    public String ActualizarLab(int id, String tipo, String uso, String presentacion) throws RemoteException;

     public String insertartar(
            Integer id,Integer codigo,String nombreExist,String fecha, 
            Integer existencia,Integer faltante,String observacion,
            Integer idTar,
            Integer lineaNivel, String nombre, String accionFarmacologica
    ) throws RemoteException;
    
    public String[] consultaIndividualTar(int id) throws RemoteException;
    
    public String EliminarTar(int id) throws RemoteException;
    
    public String ActualizarTar(int id, String nombre, String accion, int linea) throws RemoteException;

}
