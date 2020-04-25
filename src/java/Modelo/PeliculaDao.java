/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import Control.PeliculaBean;
import DataBaseConexion.Persistencia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author JHOAN
 */
public class PeliculaDao {
      Persistencia p = new Persistencia();    
       
          public Object[][] mostrarpeliculas(){
           Object data[][] = new Object [contarpeliculas()][6];
           ResultSet res = null ;
           String sql = "SELECT id_pelicula, titulo_pelicula, descripcion_pelicula, tipo_pelicula, imagen_foto, raptin From pelicula ORDER BY raptin  DESC; ";
           
           res =p.ejecutarconsulta(sql);
           
        try {
            int i = 0;
            while(res.next()){
               data [i][0] = res.getString(1);
               data [i][1] = res.getString(2);
               data [i][2] = res.getString(3);
                data [i][3] = res.getString(4);
                data [i][4] = res.getString(5);
                data [i][5] = res.getInt(6);
                
                 i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeliculaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return  data;
       
    }
          
      public int contarpeliculas(){
        int numero = 0 ;
          String sql = "SELECT count(p.id_pelicula) FROM pelicula p;";
            ResultSet res=p.ejecutarconsulta(sql);
        try {
            while(res.next()){
                   numero = res.getInt(1);
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeliculaBean.class.getName()).log(Level.SEVERE, null, ex);
         }
        return numero;
        }
      
      
      public boolean eliminarpelicula( int id_pelicula){
           boolean elimino=false;
          String sql="Delete from pelicula where id_pelicula="+id_pelicula;
                 elimino=p.ejecutarDML(sql);   
          return elimino;
      }
      
      
      public boolean actualizarPelicula(String titulo_pelicula, String descripcion_pelicula, String tipo_pelicula, int id_pelicula){
            boolean actualizo = false;
            
        String sql = "update pelicula set titulo_pelicula ='" + titulo_pelicula + "', descripcion_pelicula='"+descripcion_pelicula+"', "
                + " tipo_pelicula='"+tipo_pelicula+"'  "
                + "  " + "where id_pelicula=" + id_pelicula + "";
        actualizo = p.ejecutarDML(sql);      
         return actualizo;
        
      }
      
      
       public boolean insertarPelicula(String titulo_pelicula, String descripcion_pelicula, String tipo_pelicula, String imagen){
       boolean inserto = false;
       
        String sql = "insert into pelicula(titulo_pelicula, descripcion_pelicula, tipo_pelicula,  imagen_foto) values "
                + "('"+titulo_pelicula+"','"+descripcion_pelicula+"','"+tipo_pelicula+"', '"+imagen+"')";  
       
        inserto = p.ejecutarDML(sql);    
        
        return inserto;
       }
       
       
     public Object[][] buscarPelicula(String titulo_pelicula){
        Object data[][] = new Object[1][3];
        ResultSet res = null;
        boolean bandera=false;
        String sql = "Select id_pelicula, descripcion_pelicula, tipo_pelicula from  pelicula where titulo_pelicula = '" + titulo_pelicula+"'";
        res = p.ejecutarconsulta(sql);
        try {
             int i = 0;
            while(res.next()){
                            
               data[i][0] = res.getString(1);
                data[i][1] = res.getString(2);
                data[i][2] = res.getString(3);  
              
                           
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeliculaBean.class.getName()).log(Level.SEVERE, null, ex);
        }        
          return data;
        
       }
     
     
        public boolean actualizarRaptin(int raptin, int id_pelicula) {
       
         boolean actualizo = false;
        String sql = "update pelicula set raptin =" + raptin +   "  where id_pelicula=" + id_pelicula + "";
        actualizo = p.ejecutarDML(sql);
        return actualizo; 
        }
      
      
    
}
