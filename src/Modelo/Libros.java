
package Modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;


public class Libros {
    private int codigo;
    private String nombre;
    private int anio;
    private String librocol;
    private String estado;
    private TipoLibro tipoLibro;

    
      public Vector arrayToVector(){
        Vector lista = new Vector();
        lista.add(this.codigo);
        lista.add(this.nombre);
        lista.add(this.anio);
        lista.add(this.librocol);
        lista.add(this.estado);
        
        int tipo = tipoLibro.getCodigo();
        tipoLibro = tipoLibro.retornarUno(tipo);
        lista.add(tipoLibro.getTipoLibro());
        
        
        
        return lista;
    }
    
    
    public Libros() {
    }

    public Libros(int codigo, String nombre, int anio, String librocol, String estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.anio = anio;
        this.librocol = librocol;
        this.estado = estado;
    }

    
    
    
    public Libros(String nombre, int anio, String librocol, String estado) {
        this.nombre = nombre;
        this.anio = anio;
        this.librocol = librocol;
        this.estado = estado;
    }

    public Libros(String nombre, int anio, String librocol, String estado, TipoLibro tipoLibro) {
        this.nombre = nombre;
        this.anio = anio;
        this.librocol = librocol;
        this.estado = estado;
        this.tipoLibro = tipoLibro;
    }

    public TipoLibro getTipoLibro() {
        return tipoLibro;
    }

    public void setTipoLibro(TipoLibro tipoLibro) {
        this.tipoLibro = tipoLibro;
    }
    
    
    
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getLibrocol() {
        return librocol;
    }

    public void setLibrocol(String librocol) {
        this.librocol = librocol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString(){
        return nombre+" "+codigo;
    }
    
    private static Conexion conexion;
    public static ArrayList<Libros> retornarTodo(){
        
        try{
            ArrayList<Libros> lista = new ArrayList<Libros>();

            conexion = new Conexion();
            Connection con = conexion.getConnection();
            
            String sql = "SELECT * FROM libro";
            Statement st;
            ResultSet rs;
            
            st = con.createStatement();
            rs = st.executeQuery(sql);
            
         
            
            while(rs.next()){
                Libros lib = new Libros();
                lib.setCodigo(rs.getInt("codigo"));
                lib.setLibrocol(rs.getString("librocol"));
                lib.setNombre(rs.getString("Nombre"));
                lib.setAnio(rs.getInt("anio"));
                lib.setEstado(rs.getString("estado"));
                int codigo = rs.getInt("tipolibro");
                TipoLibro tl = new TipoLibro(codigo);
                lib.setTipoLibro(tl);               
                
                
                lista.add(lib);
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
