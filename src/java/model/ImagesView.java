/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Nishimwe Elysee
 */
@ManagedBean
@RequestScoped
public class ImagesView {

    private List<String> images =new ArrayList<>();
    public List<String> getImages() {
        String relativePath="/resources/images";
        String absolutePath=   FacesContext.getCurrentInstance().getExternalContext().getRealPath(relativePath);
        try{
            File directoryPath = new File(absolutePath);
            File filesList[] = directoryPath.listFiles();
            for (File file : filesList) {
                images.add(file.getName());
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return images;
    }
}
