/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;


import controllers.Index;
import java.io.FileNotFoundException;
import java.io.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author gyerby
 */
public final class FileUploadHelper {

    /**
     *
     * @param context
     * @param request
     * @param ownerID
     * @return
     * @throws IOException
     * @throws ServletException
     */
    public static String setImageFilePath(ServletContext context, HttpServletRequest request, int ownerID) throws IOException, ServletException {
        String fileName = String.valueOf(ownerID);
        String outFilePath = null;
        String  recipeImageFilepath = (String)context.getAttribute("RecipeImageRootFilePath");
        String realPath = context.getRealPath("/" + recipeImageFilepath);
    try {
        final Part filePart;
              
        filePart = request.getPart("RecipeImage");
        if(filePart == null || !filePart.getContentType().startsWith("image")) return null;
                
        OutputStream out = null;
        InputStream filecontent = null;
                
        try {
            outFilePath = realPath + File.separator + fileName + getExtension(filePart);
            out = new FileOutputStream(new File(outFilePath));
            filecontent = filePart.getInputStream();
            
            int read = 0;
            final byte[] bytes = new byte[1024];
            
            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
          
        } catch (FileNotFoundException fne) {
             Logger.getLogger(Index.class.getName()).log(Level.SEVERE, null, fne);
            throw fne;
                    
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
          
        }
        
    } catch (IOException | ServletException ex) {
            Logger.getLogger(FileUploadHelper.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
    }
        if(outFilePath == null) return null;
       File newfile = new File(outFilePath);
       if(!newfile.exists()) return null;
           return recipeImageFilepath + "/" + newfile.getName();
       
    }
    private static String getExtension(Part part){
       // String filepath = part.getSubmittedFileName();
        //filepath.substring(0,filepath.lastIndexOf())
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for(String s: items){
            if(s.trim().startsWith("filename")){
                return s.substring(s.lastIndexOf("."), s.length()-1);
            }
        }
        return "";
    }
}