/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DataBaseConexion.Persistencia;
import Modelo.PeliculaDao;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.Serializable;

 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*; 
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.imageio.ImageIO;
import org.jboss.weld.resources.spi.ResourceLoadingException;

/**
 *
 * @author JHOAN
 */
@Named(value = "peliculaBean")
@ApplicationScoped

public class PeliculaBean implements Serializable{
    
     Persistencia p = new Persistencia();
     PeliculaDao objpeliculaDao=new PeliculaDao();
     
    private int id_pelicula;    
    private String titulo_pelicula;
    private String descripcion_pelicula;
    private String tipo_pelicula;
    private String imagen;
    private int raptin;
    private boolean  select;
     
   private PeliculaBean selectedPel;
   
    
   
   
    public PeliculaBean(Integer id_pelicula, String titulo_pelicula, String descripcion_pelicula, String tipo_pelicula, String imagen, Integer raptin) {
        this.id_pelicula = id_pelicula;
        this.titulo_pelicula = titulo_pelicula;
        this.descripcion_pelicula = descripcion_pelicula;
        this.tipo_pelicula = tipo_pelicula;
        this.imagen = imagen;
        this.raptin = raptin;
    }
   

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
     
       private UploadedFile file;
      

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
    
    
   
    
    public byte[] extractBytes (String ImageName) throws IOException {
    // open image
    File imgPath = new File(ImageName);
    BufferedImage bufferedImage = ImageIO.read(imgPath);

    // get DataBufferBytes from Raster
    WritableRaster raster = bufferedImage .getRaster();
    DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

    return data.getData() ;
}
    
    
    
    public void  insertar() {
        try {
           
        
          String ruta = file.getFileName();
        Path path = Paths.get(ruta);
 
        // call getFileName() and get FileName path object
        Path fileName = path.getFileName();
 
        // print FileName
       this.imagen = fileName.toString();
     
      
        boolean inserto = false;
        
        
         inserto=objpeliculaDao.insertarPelicula(this.titulo_pelicula,this.descripcion_pelicula, this.tipo_pelicula, this.imagen);
       
        String fromFile =ruta;
        String toFile = "C:\\Users\\JHOAN\\CinePopayan\\web\\imagenes\\"+this.imagen;     
  
        boolean result = this.copyFile(fromFile, toFile);           
        if (inserto== true ) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("pelicula ingresada "));
        }else {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("pelicula no ingresada"));
        } 
           
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
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
           
               
         Object datos[][] =objpeliculaDao.buscarPelicula(this.titulo_pelicula);      
           
           
      
        
        boolean bandera=false;
         try {
             
              for (int i = 0; i <datos.length ; i++) {
                for (int j = 0; j < datos[i].length ; j++) {
                   
                    this.id_pelicula= Integer.parseInt( String.valueOf(datos[i][0]));
                    this.descripcion_pelicula=String.valueOf(datos[i][1]);
                    this.tipo_pelicula=  String.valueOf(datos[i][2]);  
                     if(this.descripcion_pelicula!=null){
                    bandera=true;   
                    }
                }
               
            }
     
    } catch (Exception e) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ejecutado satisfactoriamente"+e.getMessage()));
    }
    
        
        if(bandera==false){
            this.id_pelicula=0;
            this.tipo_pelicula=null;
            this.descripcion_pelicula=null;
            this.titulo_pelicula=null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("pelicula no encontrada "));
        }      
        
       }
       
    
    
          
          
      public void llenar(){
       
        listapelicula =new ArrayList<PeliculaBean>();
             Object datos[][] =objpeliculaDao.mostrarpeliculas();      
           
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
                 elimino=objpeliculaDao.eliminarpelicula(peliculasel.get(i));                                                  
                 if(elimino==true){
                     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Datos eliminados"));
                 }
             }
         }else{
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No hay datos selecionados"));
         }
         
         llenar();
      }   
    public void actualizarPelicula() {
        boolean actualizo = false;   
        try {
            actualizo= objpeliculaDao.actualizarPelicula(selectedPel.titulo_pelicula,
              selectedPel.descripcion_pelicula, selectedPel.tipo_pelicula, selectedPel.id_pelicula);
        
         if(actualizo==true){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Datos actualizado"));
         }
         else{
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error"));
               
         }
        } catch (Exception e) {
            System.out.println("error"+ e.getMessage());
        }
      
    
    }       
  public void onrate() {
       
         boolean actualizo = false;

        actualizo=objpeliculaDao.actualizarRaptin(this.raptin, this.id_pelicula);
        
         if(actualizo==true){
         FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Raptirn","Actualizado:"+this.raptin);
        FacesContext.getCurrentInstance().addMessage(null, message);
         }else{
              FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Raptirn","no actualizado:"+this.raptin+ "  id_pelicula "+this.id_pelicula);
        FacesContext.getCurrentInstance().addMessage(null, message);
         }
     
    }
  
  
   }
    
    

