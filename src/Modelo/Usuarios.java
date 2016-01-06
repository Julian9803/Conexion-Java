
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;


public class Usuarios {
    
    private int idUsuario;
    private long documento;
    private String nombre;
    private String direccion;
    private long telefono;
    public TipoUsuario tipoUsuario;

    
    
    public Vector arrayToVector(){
        Vector lista = new Vector();
        lista.add(this.idUsuario);
        lista.add(this.documento);
        lista.add(this.nombre);
        lista.add(this.direccion);
        lista.add(this.telefono);
        
        int tipo = tipoUsuario.getCodigot();
        tipoUsuario = tipoUsuario.retornarUno(tipo);
        lista.add(tipoUsuario.getTipoUsuario());
        
        
        
        return lista;
    }
    
    public Usuarios() {
    }
    
    

    public Usuarios(long documento, String nombre, String direccion, long telefono, TipoUsuario tipoUsuario) {
        this.idUsuario = idUsuario;
        this.documento = documento;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipoUsuario = tipoUsuario;
    }

    
    
    public Usuarios(int idUsuario, long documento, String nombre, String direccion, long telefono, TipoUsuario tipoUsuario) {
        this.idUsuario = idUsuario;
        this.documento = documento;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipoUsuario = tipoUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getDocumento() {
        return documento;
    }

    public void setDocumento(long documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    private static Conexion conexion;
    
    Statement st;
    ResultSet rs;
    
    public  boolean Insertar(Usuarios us){
        
        try {
            
            conexion = new Conexion();
            Connection con = conexion.getConnection();
            
            String SQL = "";
            SQL += "INSERT INTO usuario(documento,nombre,direccion,telefono,TipoUsuario) VALUES("+us.getDocumento()+",'"+us.getNombre()+"','"+us.getDireccion()+"',"+us.getTelefono()+","+us.getTipoUsuario().getCodigot()+")";
            
            st = con.createStatement();
            st.executeUpdate(SQL);
            
            con.close();
            st.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error al guardar: "+e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<Usuarios> retornarTodoUsuarios(){
        try {
            
             ArrayList<Usuarios> lista = new ArrayList<Usuarios>();

            conexion = new Conexion();
            Connection con = conexion.getConnection();
            
            String sql = "SELECT * FROM usuario";
            Statement st;
            ResultSet rs;
            
            st = con.createStatement();
            rs = st.executeQuery(sql);
            
         
            
            while(rs.next()){
                Usuarios us = new Usuarios();
                us.setIdUsuario(rs.getInt("idUsuario"));
                us.setDocumento(rs.getLong("documento"));
                us.setNombre(rs.getString("nombre"));
                us.setDireccion(rs.getString("direccion"));
                us.setTelefono(rs.getLong("telefono"));         
                int codigo = rs.getInt("TipoUsuario");
                System.out.println("El codigo es: "+codigo);
                TipoUsuario tu = new TipoUsuario(codigo);
                us.setTipoUsuario(tu);
                System.out.println("El codigo object es: "+us.tipoUsuario.getCodigot());
                

                lista.add(us);
            }
            
            con.close();
            st.close();
            return lista;
            
        } catch (Exception e) {
            System.out.println("Error en retornar todo: "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Usuarios> retornarTodoSQL(String sql){
        try {
            
             ArrayList<Usuarios> lista = new ArrayList<Usuarios>();

            conexion = new Conexion();
            Connection con = conexion.getConnection();
            

            Statement st;
            ResultSet rs;
            
            st = con.createStatement();
            rs = st.executeQuery(sql);
            
         
            
            while(rs.next()){
                Usuarios us = new Usuarios();
                us.setIdUsuario(rs.getInt("idUsuario"));
                us.setDocumento(rs.getLong("documento"));
                us.setNombre(rs.getString("nombre"));
                us.setDireccion(rs.getString("direccion"));
                us.setTelefono(rs.getLong("telefono"));         
                int codigo = rs.getInt("TipoUsuario");
                System.out.println("El codigo es: "+codigo);
                TipoUsuario tu = new TipoUsuario(codigo);
                us.setTipoUsuario(tu);
                System.out.println("El codigo object es: "+us.tipoUsuario.getCodigot());
                

                lista.add(us);
            }
            
            con.close();
            st.close();
            return lista;
            
        } catch (Exception e) {
            System.out.println("Error en retornar todo: "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    
    public boolean Actualizar(Usuarios us){
        try {
            
            conexion = new Conexion();
            Connection con = conexion.getConnection();
            
            Statement st;
            
            String SQL = "";
            
            SQL = "UPDATE usuario SET documento= "+us.getDocumento()+", nombre = '"+us.getNombre()+"', direccion = '"+us.getDireccion()+"', telefono = "+us.getTelefono()+", TipoUsuario = "+us.tipoUsuario.getCodigot()+" WHERE idUsuario = "+us.getIdUsuario()+"  ";
            
            st = con.createStatement();
            int opcion = st.executeUpdate(SQL);
            
            if(opcion ==1){
                con.close();
                st.close();
                return true;
            }else{
                con.close();
                st.close();
                return false;
            }
            
            
        } catch (Exception e) {
            System.out.println("Error al actualizar: "+e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
            
    public Usuarios retornarUno(int id){
        try {
            
            Usuarios us = new Usuarios();

            conexion = new Conexion();
            Connection con = conexion.getConnection();
            
            String SQL = "SELECT * FROM Usuario WHERE idUsuario = "+id;
            Statement st;
            ResultSet rs;
            
            st = con.createStatement();
            rs = st.executeQuery(SQL);
            
         
            
            while(rs.next()){
                
                us.setIdUsuario(rs.getInt("idUsuario"));
                us.setDocumento(rs.getLong("documento"));
                us.setNombre(rs.getString("nombre"));
                us.setDireccion(rs.getString("direccion"));
                us.setTelefono(rs.getLong("telefono"));


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
    
    public boolean eliminar(int idUsuario){
        try {
            conexion = new Conexion();
            Connection con = conexion.getConnection();
            
            Statement st;
            
            String SQL = "";
            
            SQL += "DELETE FROM usuario WHERE idUsuario = "+idUsuario;
            
            st = con.createStatement();
            int opcion = st.executeUpdate(SQL);
            
            if(opcion ==1){
                con.close();
                st.close();
                return true;
            }else{
                con.close();
                st.close();
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar: "+e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
