
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;


public class Conexion {
    
    private static String url = "jdbc:mysql://localhost:3306/Biblioteca";
    private static String user = "root";
    private static String password = "";
    private static String driver = "com.mysql.jdbc.Driver";
    private static Connection conexion;
    
    public  Conexion(){
        try {

            Class.forName(driver);
            conexion = DriverManager.getConnection(url,user,password);
            System.out.println("Conecte");
        } catch (Exception ex) {
            System.out.println("Error en la conexion: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public Connection getConnection(){
        return conexion;
    }
    
      
    
            
            
    
}
