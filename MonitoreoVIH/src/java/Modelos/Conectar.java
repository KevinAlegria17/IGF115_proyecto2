
package Modelos;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class Conectar {
    private static DriverManagerDataSource dataSource;
    private Conectar(){}
    
    public static DriverManagerDataSource conectar(){
        
        if(dataSource==null){
        dataSource= new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/dbmonitoreovih");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        
        }
        return dataSource;
    }
}
