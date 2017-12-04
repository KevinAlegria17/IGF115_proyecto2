
package componentes;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DefinicionComponenteTar extends Remote{
    
    public String insertartar(
            Integer id,Integer codigo,String fecha, 
            Integer existencia,Integer faltante,String observacion,
            Integer idTar,
            Integer lineaNivel, String nombre, String accionFarmacologica
    ) throws RemoteException;
    
    public String[] consultaIndividual(int id) throws RemoteException;
    
    public String Eliminar(int id) throws RemoteException;
    
    public String Actualizar(int id, String nombre, String accion, int linea) throws RemoteException;

}
