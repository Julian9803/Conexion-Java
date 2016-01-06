
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class TipoLibro {
    private int codigo;
    private String TipoLibro;

    public TipoLibro(int codigo, String TipoLibro) {
        this.codigo = codigo;
        this.TipoLibro = TipoLibro;
    }

    public TipoLibro(int codigo) {
        this.codigo = codigo;
    }

    
    
    public TipoLibro() {

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTipoLibro() {
        return TipoLibro;
    }

    public void setTipoLibro(String TipoLibro) {
        this.TipoLibro = TipoLibro;
    }
    
    
    private static Conexion conexion;
    public TipoLibro retornarUno(int id){
        try {
            
            TipoLibro us = new TipoLibro();

            conexion = new Conexion();
            Connection con = conexion.getConnection();
            
            String SQL = "SELECT * FROM TipoLibro WHERE codigo = "+id;
            Statement st;
            ResultSet rs;
            
            st = con.createStatement();
            rs = st.executeQuery(SQL);
            
         
            
            while(rs.next()){
                
                us.setCodigo(rs.getInt("codigo"));
                us.setTipoLibro(rs.getString("tipolibro"));
                
            }
            
            con.close();
            st.close();
            return us;
            
        } catch (Exception e) {
            System.out.println("Error en retornar todo: "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    
    
}
