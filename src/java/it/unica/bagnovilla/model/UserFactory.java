/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.bagnovilla.model;

import it.unica.bagnovilla.db.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fpw
 */
public class UserFactory {
    
    private static UserFactory instance;

    public UserFactory() {}
    
    public static UserFactory getInstance(){
        if(instance == null)
            instance = new UserFactory();
        return instance;
    }
    
    
    public User getUserByUsernamePassword(String username, String password){
        Connection conn= null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        
        try{
            conn = DatabaseManager.getInstance().getDbConnection();
            String query = "SELECT * FROM user_ WHERE username = ? AND password = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            set = stmt.executeQuery();
            
            if(set.next()){
                User user = new User();
                
                user.setId(set.getLong("id"));
                user.setUsername(set.getString("username"));
                user.setWant_invoice(set.getBoolean("want_invoice"));
                user.setIs_admin(set.getBoolean("is_admin"));
                user.setName(set.getString("name"));
                user.setLast_name(set.getString("last_name"));
                
                Timestamp ts = set.getTimestamp("birth_date");                
                LocalDateTime localDt = null;
                if(ts != null){
                    localDt = LocalDateTime.ofInstant(
                            Instant.ofEpochMilli(ts.getTime()),
                            ZoneOffset.UTC);
                }
                user.setBirth_date(localDt);
                
                user.setFiscal_code(set.getString("fiscal_code"));
                user.setSex(set.getString("sex"));
                user.setEmail(set.getString("email"));
                user.setTelephone(set.getString("telephone"));
                user.setPassword(set.getString("password"));
                
                return user;
            }else{
                return null;
            }
        }catch(SQLException e){
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try{ set.close();} catch(Exception e){}
            try{ stmt.close();} catch(Exception e){}
            try{ conn.close();} catch(Exception e){}
        }
        
        return null;
    }
    
    
    public List<User> getAllUsers(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        List<User> users = new ArrayList<>();
        
        try{
            conn = DatabaseManager.getInstance().getDbConnection();
            String query = "SELECT * FROM User";
            stmt = conn.prepareStatement(query);
            set = stmt.executeQuery();
            
            while(set.next()){
                User user = new User();
                
                user.setId(set.getLong("id"));
                user.setUsername(set.getString("username"));
                user.setWant_invoice(set.getBoolean("want_invoice"));
                user.setIs_admin(set.getBoolean("is_admin"));
                user.setName(set.getString("name"));
                user.setLast_name(set.getString("last_name"));
                
                Timestamp ts = set.getTimestamp("birth_date");                
                LocalDateTime localDt = null;
                if(ts != null){
                    localDt = LocalDateTime.ofInstant(
                            Instant.ofEpochMilli(ts.getTime()),
                            ZoneOffset.UTC);
                }
                user.setBirth_date(localDt);
                
                user.setFiscal_code(set.getString("fiscal_code"));
                user.setSex(set.getString("sex"));
                user.setEmail(set.getString("email"));
                user.setTelephone(set.getString("telephone"));
                             
                users.add(user);
            }
            return users;
        }catch(SQLException e){
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            try{ set.close(); } catch (Exception e){}
            try{ stmt.close();} catch(Exception e){}
            try{ conn.close();} catch(Exception e){}
        }
        return null;
    }
    
}
