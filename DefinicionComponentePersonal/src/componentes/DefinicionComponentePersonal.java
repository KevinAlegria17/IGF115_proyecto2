
package componentes;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DefinicionComponentePersonal extends Remote{
    
    public String insertarPersonal(int id, String area, int cantidad, int porcentaje) throws RemoteException;
    
    public String[] consultaIndividual(int id) throws RemoteException;
    
    public String Eliminar(int id) throws RemoteException;
    
    public String Actualizar(int id, String area, int cantidad_personal, int porcentaje) throws RemoteException;

}
