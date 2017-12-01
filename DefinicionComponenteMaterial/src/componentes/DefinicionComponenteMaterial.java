
package componentes;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface DefinicionComponenteMaterial extends Remote{
    public String insertarlab(
            Integer id,Integer codigo,String fecha, 
            Integer existencia,Integer faltante,String observacion,
            Integer idLaboratorio,
            String tipoFabricacion, String uso, String presentacion
    ) throws RemoteException;
    
    public String insertartar(
            Integer id,Integer codigo,String fecha, 
            Integer existencia,Integer faltante,String observacion,
            Integer idTar,
            Integer lineaNivel, String nombre, String accionFarmacologica
    ) throws RemoteException;
    
    public List verMaterialesLaboratorio() throws RemoteException;
    
    public String[] consultaIndividual(int id) throws RemoteException;
    
    public String Eliminar(int id) throws RemoteException;
    
    public String Actualizar(int id, int idExist, String tipo, String uso, String presentacion) throws RemoteException;

}
