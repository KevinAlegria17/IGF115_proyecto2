
package componentes;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface DefinicionComponentePersistencia extends Remote{
    
    //CRUD LABORATORIO
    
    public String InsertarLab(
            Integer id,Integer codigo,String fecha, 
            Integer existencia,Integer faltante,String observacion,
            Integer idLaboratorio,
            String tipoFabricacion, String uso, String presentacion
    ) throws RemoteException;
    
    public String[] ConsultaIndividualLab(int id) throws RemoteException;
    
    public String EliminarLab(int id) throws RemoteException;
    
    public String ActualizarLab(int id, String tipo, String uso, String presentacion) throws RemoteException;

    //CRUD TAR
    
    public String InsertarTar(
            Integer id,Integer codigo,String fecha, 
            Integer existencia,Integer faltante,String observacion,
            Integer idTar,
            Integer lineaNivel, String nombre, String accionFarmacologica
    ) throws RemoteException;
    
    public String[] ConsultaIndividualTar(int id) throws RemoteException;
    
    public String EliminarTar(int id) throws RemoteException;
    
    public String ActualizarTar(int id, String nombre, String accion, int linea) throws RemoteException;

    //CRUD PERSONAL
    
    public String InsertarPersonal(int id, String area, int cantidad, int porcentaje) throws RemoteException;
    
    public String[] ConsultaIndividualPersonal(int id) throws RemoteException;
    
    public String EliminarPersonal(int id) throws RemoteException;
    
    public String ActualizarPersonal(int id, String area, int cantidad_personal, int porcentaje) throws RemoteException;

    
    //public List verMaterialesLaboratorio() throws RemoteException;
    
}
