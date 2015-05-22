package com.pedidovenda.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.pedidovenda.util.jsf.SessionUtil;

@Named
@RequestScoped
public class Teste {

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload(FileUploadEvent event) {
        file = event.getFile();

        if (file != null) {
        	 try {
        		 
        		 
//        		 FacesContext facesContext = FacesContext.getCurrentInstance();  
//        		 ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();  
//        		 String caminho = scontext.getRealPath("/WEB-INF/upload/");  
//        		
        		 
        		 
            File file1 = new File("/home/rudsonporfirio/upload",file.getFileName()); 
           
                FileOutputStream fos = new FileOutputStream(file1);
                fos.write(event.getFile().getContents());
                fos.close();

                FacesContext instance = FacesContext.getCurrentInstance();
                instance.addMessage("mensagens", new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        file.getFileName() + " anexado com sucesso", null));
                
                
                
                SessionUtil.setParam("comprovanteCaminho", "/home/rudsonporfirio/upload"+file.getFileName());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
				System.out.println(e);
			}


        }
    }
}