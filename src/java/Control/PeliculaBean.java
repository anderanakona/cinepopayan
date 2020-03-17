/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import Modelo.Persistencia;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;


import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.*;
import org.primefaces.model.UploadedFile;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
/**
 *
 * @author JHOAN
 */
@Named(value = "peliculaBean")
@ApplicationScoped

public class PeliculaBean implements Serializable{
    
     Persistencia p = new Persistencia();
     
   private PeliculaBean selectedPel;
   

    public PeliculaBean getSelectedPel() {
        return selectedPel;
    }

    public void setSelectedPel(PeliculaBean selectedPel) {
        this.selectedPel = selectedPel;
    }
      
      
      

    public ArrayList<PeliculaBean> getListapelicula() {
        return listapelicula;
    }

    public void setListapelicula(ArrayList<PeliculaBean> listapelicula) {
        this.listapelicula = listapelicula;
    }
     
   ArrayList<PeliculaBean> listapelicula ;
    ArrayList<Integer> peliculasel ;
   
   
    public PeliculaBean() {
       
        llenar();
      }
      private Integer id_pelicula;
      private String titulo_pelicula;
      private String descripcion_pelicula;
      private String tipo_pelicula;
      private boolean  select;
      private String imagen;
       private UploadedFile file;
       private Integer raptin;

    public Integer getRaptin() {
        return raptin;
    }

    public void setRaptin(Integer raptin) {
        this.raptin = raptin;
    }
       
       
       
       

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }


    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
     

    
    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public PeliculaBean(Integer id_pelicula, String titulo_pelicula, String descripcion_pelicula, String tipo_pelicula, String imagen, Integer raptin) {
        this.id_pelicula = id_pelicula;
        this.titulo_pelicula = titulo_pelicula;
        this.descripcion_pelicula = descripcion_pelicula;
        this.tipo_pelicula = tipo_pelicula;
        this.imagen = imagen;
        this.raptin = raptin;
    }
      
      
      
  
      

    public Integer getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(Integer id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

      
      
    public String getTipo_pelicula() {
        return tipo_pelicula;
    }

    public void setTipo_pelicula(String tipo_pelicula) {
        this.tipo_pelicula = tipo_pelicula;
    }
    
      
    

    public String getTitulo_pelicula() {
        return titulo_pelicula;
    }

    public void setTitulo_pelicula(String titulo_pelicula) {
        this.titulo_pelicula = titulo_pelicula;
    }

    public String getDescripcion_pelicula() {
        return descripcion_pelicula;
    }

    public void setDescripcion_pelicula(String descripcion_pelicula) {
        this.descripcion_pelicula = descripcion_pelicula;
    }
    
    
    
    public void  insertar() {
        
          String ruta = file.getFileName();
        Path path = Paths.get(ruta);
 
        // call getFileName() and get FileName path object
        Path fileName = path.getFileName();
 
        // print FileName
       this.imagen = fileName.toString();
     
      
        boolean inserto = false;

        String sql = "insert into pelicula(titulo_pelicula, descripcion_pelicula, tipo_pelicula,  imagen_foto) values "
                + "('"+this.titulo_pelicula+"','"+this.descripcion_pelicula+"','"+this.tipo_pelicula+"', '"+this.imagen+"')";
        inserto = p.ejecutarDML(sql);
        
         
       
        
          String fromFile =ruta;
         String toFile = "C:\\Users\\JHOAN\\CinePopayan\\web\\imagenes\\"+this.imagen;
        
        
        boolean result = this.copyFile(fromFile, toFile);
        
        
         if (inserto== true ) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("pelicula ingresada "));

        }else {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("pelicula no ingresada"));

        }
         
        llenar();
        
    }
    
    
    
   
    
      public boolean copyFile(String fromFile, String toFile) {
        File origin = new File(fromFile);
        File destination = new File(toFile);
        if (origin.exists()) {
            try {
                InputStream in = new FileInputStream(origin);
                OutputStream out = new FileOutputStream(destination);
                // We use a buffer for the copy (Usamos un buffer para la copia).
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                return true;
            } catch (IOException ioe) {
                ioe.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }
    
    
    
       public void buscarPelicula(){
        Object data[][] = new Object[1][3];
        ResultSet res = null;
        boolean bandera=false;
        String sql = "Select id_pelicula, descripcion_pelicula, tipo_pelicula from  pelicula where titulo_pelicula = '" + this.titulo_pelicula+"'";
        res = p.ejecutarconsulta(sql);
        try {
            while(res.next()){
               this.id_pelicula = res.getInt(1);
               this.descripcion_pelicula = res.getString(2);
               this.tipo_pelicula = res.getString(3); 
               if(this.descripcion_pelicula!=null){
                    bandera=true;   
               }
                           
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeliculaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(bandera==false){
            this.id_pelicula=0;
            this.tipo_pelicula=null;
            this.descripcion_pelicula=null;
            this.titulo_pelicula=null;
            
        }
       
        
       }
       
     public int contarpeliculas(){
        int numero = 0 ;
          String sql = "SELECT count(p.id_pelicula) FROM pelicula p;";
            ResultSet res=p.ejecutarconsulta(sql);
        try {
            while(res.next()){
                   numero = res.getInt(1);
               
            }} catch (SQLException ex) {
            Logger.getLogger(PeliculaBean.class.getName()).log(Level.SEVERE, null, ex);
         }
        return numero;
        }
       
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
            Logger.getLogger(PeliculaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return  data  ;
       
    }
          
          
      public void llenar(){
       
        listapelicula =new ArrayList<PeliculaBean>();
             Object datos[][] =mostrarpeliculas();      
           
            for (int i = 0; i <datos.length ; i++) {
                for (int j = 0; j < datos[i].length ; j++) {
                   
                    this.id_pelicula= Integer.parseInt( String.valueOf(datos[i][0]));
                    this.titulo_pelicula=String.valueOf(datos[i][1]);
                    this.descripcion_pelicula=  String.valueOf(datos[i][2]);
                    this.tipo_pelicula=String.valueOf(datos[i][3]);
                    this.imagen=String.valueOf(datos[i][4]);
                    this.raptin= Integer.parseInt( String.valueOf(datos[i][5]));
                }
               
                 listapelicula.add(new PeliculaBean(this.id_pelicula, this.titulo_pelicula , this.descripcion_pelicula,this.tipo_pelicula, this.imagen, this.raptin));
            }
            
          this.descripcion_pelicula=null;
         this.titulo_pelicula=null;
    }
       
      
      public void seleccioneEliminar(){
          peliculasel =new ArrayList<Integer>();
          for (PeliculaBean p : listapelicula) {
              if(p.isSelect()){
                  peliculasel.add(p.getId_pelicula());
              }
          }
          
      }
      
      public void eliminarPelicula(){
         seleccioneEliminar();
         
         
         
         if(!peliculasel.isEmpty()){
             for (int i = 0; i < peliculasel.size(); i++) {
                 boolean elimino=false;
                 String sql="Delete from pelicula where id_pelicula="+peliculasel.get(i);
                 elimino=p.ejecutarDML(sql);
                                 
                 if(elimino==true){
                     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Datos eliminados"));
                 }
             }
         }else{
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("no hay Datos"));
         }
         
         llenar();
      }
      
           public void actualizarPelicula() {

        boolean actualizo = false;

        String sql = "update pelicula set titulo_pelicula ='" + selectedPel.titulo_pelicula + "', descripcion_pelicula='"+selectedPel.descripcion_pelicula+"', "
                + " tipo_pelicula='"+selectedPel.tipo_pelicula+"'  "
                + "  " + "where id_pelicula=" + selectedPel.id_pelicula + "";

        actualizo = p.ejecutarDML(sql);
        
        
         if(actualizo==true){
                     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Datos actualizado"));
            }
         else{
             
                     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error"));
               
         }
        

    }
           
           
           
  public void onrate() {
       
         boolean actualizo = false;

        String sql = "update pelicula set raptin =" + this.raptin +   "  where id_pelicula=" + this.id_pelicula + "";
        
        
         actualizo = p.ejecutarDML(sql);
         if(actualizo==true){
         FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Raptirn","Actualizado:"+this.raptin);
        FacesContext.getCurrentInstance().addMessage(null, message);
         }else{
              FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Raptirn","no actualizado:"+this.raptin);
        FacesContext.getCurrentInstance().addMessage(null, message);
         }
        

        
    }
  
  
     
   }
    
    

