
package componentes;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DefinicionComponentePlan extends Remote{
    
    public String insertarCompra(int id, String periodo, String fechaInicio, String fechaFin) throws RemoteException;
    
//    public String[] consultaIndividual(int id) throws RemoteException;
//    
//    public String Eliminar(int id) throws RemoteException;
//    
//    public String Actualizar(int id, String nombre, String accion, int linea) throws RemoteException;

}
