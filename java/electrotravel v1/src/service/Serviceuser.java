/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Role;
import entity.User;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.scene.control.TextField;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.Projectbd;
/**
 *
 * @author Asus
 */
public class Serviceuser implements Iservice<User>{
private Connection cnx ;
public Serviceuser()   {
    
cnx=Projectbd.getInstance().getCnx();    
}

@Override
    public void ajouteruser(User u) {
          
        try {
             String querry="INSERT INTO `user`( `mdp`, `nom`, `prenom`, `email`, `role`, `numtel_user`, `adresse_user`) VALUES ('"+u.getMdp()+"','"+u.getNom()+"','"+u.getPrenom()+"','"+u.getEmail()+"','"+u.getRole()+"','"+u.getNumtel_user()+"','"+u.getAdresse_user()+"')";
            Statement stm =cnx.createStatement();
        
        stm.executeUpdate(querry);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
        
    
    }

    @Override
    public List<User> afficheruser() {
      
        List<User> Users = new ArrayList();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM user";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            User u = new User();
            u.setId_user(rs.getInt(1));
            u.setMdp(rs.getString(2));
            u.setNom(rs.getString(3));
            u.setPrenom(rs.getString(4));
            u.setEmail(rs.getString(5));
            u.setRole(Role.valueOf(rs.getString("role")));
            u.setAdresse_user(rs.getString(8));
            u.setNumtel_user((rs.getInt(7)));
            
            
            Users.add(u);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
        return Users;
   
    }
    public List<User> Rechercheruser(String critere,String rech) {
      
        List<User> Users = new ArrayList();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM user where "+critere+" like '"+rech+"%'";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            User u = new User();
            u.setId_user(rs.getInt(1));
            u.setMdp(rs.getString(2));
            u.setNom(rs.getString(3));
            u.setPrenom(rs.getString(4));
            u.setEmail(rs.getString(5));
            u.setRole(Role.valueOf(rs.getString("role")));
            u.setAdresse_user(rs.getString(8));
            u.setNumtel_user((rs.getInt(7)));
            
            
            Users.add(u);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
        return Users;
   
    }
    public List<User> Trieruser(String critere) {
      
        List<User> Users = new ArrayList();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM user order by "+critere;
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            User u = new User();
            u.setId_user(rs.getInt(1));
            u.setMdp(rs.getString(2));
            u.setNom(rs.getString(3));
            u.setPrenom(rs.getString(4));
            u.setEmail(rs.getString(5));
            u.setRole(Role.valueOf(rs.getString("role")));
            u.setAdresse_user(rs.getString(8));
            u.setNumtel_user((rs.getInt(7)));
            
            
            Users.add(u);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
        return Users;
   
    }
   public void supprimeruser(int id_user) {
        try {
            String req = "DELETE FROM user where id_user=" + id_user;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("user supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
   }


    @Override
    public boolean supprimeruser(User u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modiferuser(User u) {
           try {
           String req = "UPDATE `user` SET  `mdp`='" + u.getMdp()+    "', `email`='" + u.getEmail()+             "' WHERE id_user=" + u.getId_user();
            
          
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Le user  est modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
        
    }

    


    @Override
    public List<User> findByEmail(int email) {
     List<User> users1 = new ArrayList<>();
        try {

            String req = "SELECT * FROM `user` WHERE email = " + email ;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
            User m = new User();
            m.setId_user(rs.getInt(1));
            m.setMdp(rs.getString(2));
            m.setNom(rs.getString(3));
            m.setPrenom(rs.getString(4));
           m.setEmail(rs.getString(5));
           m.setRole(Role.valueOf(rs.getString("role")));

            m.setAdresse_user(rs.getString(7));
            m.setNumtel_user((rs.getInt(8)));
            
            
            users1.add(m);
        }
              rs.close();

            } catch (SQLException ex) {
       System.out.println(ex.getMessage());
    }
          
       
        return users1;
   
    }
   public List<User> sortByEMAIL(){
         List<User> users=afficheruser();
         List<User> resultat=users.stream().sorted(Comparator.comparing(User::getEmail)).collect(Collectors.toList());
         return resultat;
     } 

//    public boolean login(User u) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
   
    public String checkRole(String email) {
        String default_return = "ND";
        try {
            String req;
            req = "select role from user where email=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, email);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                if (rs.getString(1).equals("ADMIN")) {

                    return "Admin";
                }  else if (rs.getString(1).equals("CLIENT")) {
                    return "Client";
              }

            }

        } catch (SQLException ex) {
        }
        return default_return;
    }

    @Override
    public boolean login(String email, String mdp) {
        boolean login = true ;
        String req = "SELECT * FROM  `user` WHERE email='" + email +   "' and mdp ='"+ mdp+ "'" ;
    try {
        //
//        PreparedStatement st = cnx.preparedStatement(req);
     PreparedStatement ps = cnx.prepareStatement(req);
     ResultSet rs = ps.executeQuery();
     if(rs.next()){        
         System.out.println("Authentification validee pour l'utilisateur "+email);
     }else{
         System.err.println("l'utilisateur n'existe pas");
         login = false ;
     }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        login = false ;
    }
    return login;
    }
    public  void excel() throws FileNotFoundException, IOException {

      String req = "select * from user" ;
      
      try {
            PreparedStatement ps = cnx.prepareStatement(req);
      ResultSet rs = ps.executeQuery();
            
            XSSFWorkbook workbook=new XSSFWorkbook();
                    XSSFSheet sheet=workbook.createSheet("Users");
		
		XSSFRow row=sheet.createRow(0);
                row.createCell(0).setCellValue("id_user");
		row.createCell(1).setCellValue("prenom");
		row.createCell(2).setCellValue("email");
                row.createCell(3).setCellValue("adresse_user");
                
		
		int r=1;
		while(rs.next())
		{
                        int id_user=rs.getInt("id_user");
			String prenom=rs.getString("prenom");
			String email=rs.getString("email");
                        String adresse_user=rs.getString("adresse_user");
			
			
			row=sheet.createRow(r++);
			
                        row.createCell(0).setCellValue(id_user);
			row.createCell(1).setCellValue(prenom);
			row.createCell(2).setCellValue(email);
                        row.createCell(3).setCellValue(adresse_user);
			
			
		}
		
		
		  FileOutputStream fos=new FileOutputStream("C:\\Users\\Asus\\Documents\\NetBeansProjects\\Gamingland\\src\\GUI\\user.xlsx");
		workbook.write(fos);
		
		workbook.close();
		fos.close();
		cnx.close();
		
		System.out.println("wait a minute brother !!!");
      }catch(SQLException ex){
          Logger.getLogger(Serviceuser.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
 public List<User> mdpoublier( String email) {
      
        List<User> Users = new ArrayList();
    
        try {
        Statement stm =cnx.createStatement();
          String req =  "select mdp from user where email="+email ;
    
        ResultSet rs= stm.executeQuery(req);
        
        while(rs.next()){
            User u = new User();
            
            u.setMdp(rs.getString(2));
           
            
            
            Users.add(u);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
        return Users;
   
    }

    
    
    
    }
    

