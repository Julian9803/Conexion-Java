
package Modelo;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;


public class Prestamos {
    private Libros libro;
    private Usuarios usuario;
    private String fechaprestamo;
    private String fechadevolucion;

    public Prestamos(Libros libro, Usuarios usuario, String fechaprestamo, String fechadevolucion) {
        this.libro = libro;
        this.usuario = usuario;
        this.fechaprestamo = fechaprestamo;
        this.fechadevolucion = fechadevolucion;
    }

    public Libros getLibro() {
        return libro;
    }

    public void setLibro(Libros libro) {
        this.libro = libro;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String getFechaprestamo() {
        return fechaprestamo;
    }

    public void setFechaprestamo(String fechaprestamo) {
        this.fechaprestamo = fechaprestamo;
    }

    public String getFechadevolucion() {
        return fechadevolucion;
    }

    public void setFechadevolucion(String fechadevolucion) {
        this.fechadevolucion = fechadevolucion;
    }
    
    private static Conexion conexion;
    Statement st;
    ResultSet rs;
    
    public  boolean Insertar(Prestamos pr){
        
        try {
            
            conexion = new Conexion();
            Connection con = conexion.getConnection();
            
            String SQL = "";
            System.out.println("El id de libro es: "+pr.getLibro().getCodigo());
            SQL += "INSERT INTO prestamo(libro,usuario,fechaprestamo,fechadevolucion) VALUES("+pr.getLibro().getCodigo()+","+pr.getUsuario().getIdUsuario()+",'"+pr.getFechaprestamo()+"','"+pr.getFechadevolucion()+"')";
            
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
    
}
