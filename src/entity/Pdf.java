/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import com.itextpdf.text.Document;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import service.Serviceuser;



/**
 *
 * @author Asus
 */
public class Pdf {
  
    public void GeneratePdf(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
      
        Serviceuser su = new Serviceuser() ;        
        List<User> list=su.afficheruser();    
        document.add(new Paragraph("La liste des users :"));
        document.add(new Paragraph("     "));
         for(User u:list)
        {
            
        document.add(new Paragraph("id_user :"+u.getId_user()));
        document.add(new Paragraph("mdp :"+u.getMdp()));
        document.add(new Paragraph("nom:"+u.getNom()));
        document.add(new Paragraph("prenom :"+u.getPrenom()));
        document.add(new Paragraph("email :"+u.getEmail()));
        document.add(new Paragraph("numtel_user:"+u.getNumtel_user()));
        document.add(new Paragraph("adresse_user :"+u.getAdresse_user()));
       

        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        }
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }    
}
