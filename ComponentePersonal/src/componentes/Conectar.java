
package componentes;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conectar {
    private static Connection conn;
    private static final String driver="com.mysql.jdbc.Driver";
    private static final String url="jdbc:mysql://localhost:3306/dbmonitoreovih";
    private static final String usuario="root";
    private static final String password="";

    public Conectar(){
        conn=null;
        try{
            Class.forName(driver);
            conn=DriverManager.getConnection(url, usuario, password);
            if(conn != null){
                System.out.print("Conexion exitosa!");
            }else{
                System.out.print("Conexion fallida!");
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        return conn;
    }

    public static void setConn(Connection conn) {
        Conectar.conn = conn;
    }
    
    

}