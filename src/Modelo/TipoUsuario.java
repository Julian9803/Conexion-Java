
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class TipoUsuario {
    private int  codigot;
    private String TipoUsuario;

    public TipoUsuario() {
    }

    public TipoUsuario(int codigot) {
        this.codigot = codigot;
    }

    
    
    public TipoUsuario(int codigot, String TipoUsuario) {
        this.codigot = codigot;
        this.TipoUsuario = TipoUsuario;
    }

    public int getCodigot() {
        return codigot;
    }

    public void setCodigot(int codigot) {
        this.codigot = codigot;
    }

    public String getTipoUsuario() {
        return TipoUsuario;
    }

    public void setTipoUsuario(String TipoUsuario) {
        this.TipoUsuario = TipoUsuario;
    }
    
    @Override
    public String toString(){
        return TipoUsuario;
    }
    
    
    private static Conexion conexion;
    
    public static boolean Insertar(String tipoUsuario){
        try{
            conexion = new Conexion();
            Connection con = conexion.getConnection();
            Statement st;
            
            String sql = "";
            sql += "INSERT INTO tipousuario(TipoUsuario) values ('"+tipoUsuario+"')";
            
            st = con.createStatement();
            st.executeUpdate(sql);
            con.close();
            st.close();
            return true;
        }catch(Exception ex){
            System.out.println("Error al guardar: "+ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
    
    public static TipoUsuario retornarUno(int codigo){
        
        
        try{
            TipoUsuario us = new TipoUsuario();

            conexion = new Conexion();
            Connection con = conexion.getConnection();
            
            String sql = "SELECT * FROM tipousuario WHERE Codigo = "+codigo;
            Statement st;
            ResultSet rs;
            
            st = con.createStatement();
            rs = st.executeQuery(sql);
            
         
            
            while(rs.next()){

                us.setCodigot(rs.getInt("codigo"));
                us.setTipoUsuario(rs.getString("TipoUsuario"));
                
            }
            
            con.close();
            st.close();
            
            return us;
        
        }catch(Exception ex){
            System.out.println("Error al rtoronar tipo usuario: "+ex.getMessage());
            ex.printStackTrace();
            return null;
        }
        
      
    }
    
    
    public static ArrayList<TipoUsuario> retornarTodo(){
        
        try{
            ArrayList<TipoUsuario> lista = new ArrayList<TipoUsuario>();

            conexion = new Conexion();
            Connection con = conexion.getConnection();
            
            String sql = "SELECT * FROM tipousuario";
            Statement st;
            ResultSet rs;
            
            st = con.createStatement();
            rs = st.executeQuery(sql);
            
         
            
            while(rs.next()){
                TipoUsuario tu = new TipoUsuario();
                tu.setCodigot(rs.getInt("codigo"));
                tu.setTipoUsuario(rs.getString("TipoUsuario"));
                lista.add(tu);
            }
            
            con.close();
            st.close();
            return lista;
        
        }catch(Exception ex){
            System.out.println("Error al rtoronar tipo usuario: "+ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
    
}
