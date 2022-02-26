/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.bagnovilla.model.factory;

import it.unica.bagnovilla.model.entity.User;
import it.unica.bagnovilla.Db.DatabaseManager;
import it.unica.bagnovilla.exceptions.SqlException;
import it.unica.bagnovilla.utils.FactoryUtil;
import static it.unica.bagnovilla.utils.FactoryUtil.insertInTable;
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
import it.unica.bagnovilla.utils.Utils;

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
    public void registerUser(User user) throws SqlException {
          
        insertInTable("user_",user.getAllAttributesNames(),user.getUserAsArray());
    }
    
    public void updateUser(User user, String username) throws SqlException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try {

            conn = DatabaseManager.getInstance().getDbConnection();

            String query = "UPDATE user_ SET "
                    + "username = ?, "
                    + "want_invoice = ?, "
                    + "is_admin = ?, "
                    + "name = ?, "
                    + "last_name = ?, "
                    + "birth_date = ?, "
                    + "fiscal_code = ?, "
                    + "sex = ?, "
                    + "email = ?, "
                    + "telephone = ?, "
                    + "password = ?, "
                    + "foto = ? "
                    + "WHERE username = ?";

            stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getUsername());
            stmt.setBoolean(2, user.isWant_invoice());
            stmt.setBoolean(3,user.isIs_admin());
            stmt.setString(4, user.getName());
            stmt.setString(5,user.getLast_name());
            stmt.setDate(6, FactoryUtil.localDateToDate(user.getBirth_date()));
            stmt.setString(7,user.getFiscal_code());
            stmt.setString(8, user.getSex());
            stmt.setString(9, user.getEmail());
            stmt.setString(10, user.getTelephone());
            stmt.setString(11, user.getPassword());
            stmt.setString(12,user.getFoto());
            stmt.setString(13, username);

            stmt.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, e);
            throw new SqlException(e.getMessage());
        } finally {
            try{ set.close(); } catch (Exception e){}
            try{ stmt.close();} catch(Exception e){}
            try{ conn.close();} catch(Exception e){}
        }
    }
    
    public User getUserByUsernamePassword(String username, String password) throws SqlException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;

        try {
            conn = DatabaseManager.getInstance().getDbConnection();

            String query = "SELECT * FROM user_ WHERE username = ? AND password = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            set = stmt.executeQuery();

            if (set.next()) {
                User user = new User();
                user.setUsername(set.getString("username"));
                user.setWant_invoice(set.getBoolean("want_invoice"));
                user.setIs_admin(set.getBoolean("is_admin"));
                user.setName(set.getString("name"));
                user.setLast_name(set.getString("last_name"));               
                
                user.setBirth_date(FactoryUtil.sqlTimestampToLocalDate(set.getTimestamp("birth_date")));
                
                user.setFiscal_code(set.getString("fiscal_code"));
                user.setSex(set.getString("sex"));
                user.setEmail(set.getString("email"));
                user.setTelephone(set.getString("telephone"));
                user.setPassword(set.getString("password"));
                user.setFoto(set.getString("foto"));
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            Logger.getLogger(UserFactory.class.getName()).log(Level.SEVERE, null, e);
            throw new SqlException(e.getMessage());
        } finally {
            try{ set.close(); } catch (Exception e){}
            try{ stmt.close();} catch(Exception e){}
            try{ conn.close();} catch(Exception e){}
        } 
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
                
                user.setUsername(set.getString("username"));
                user.setWant_invoice(set.getBoolean("want_invoice"));
                user.setIs_admin(set.getBoolean("is_admin"));
                user.setName(set.getString("name"));
                user.setLast_name(set.getString("last_name"));
                user.setBirth_date(FactoryUtil.sqlTimestampToLocalDate(set.getTimestamp("birth_date")));
                
                user.setFiscal_code(set.getString("fiscal_code"));
                user.setSex(set.getString("sex"));
                user.setEmail(set.getString("email"));
                user.setTelephone(set.getString("telephone"));
                user.setFoto(set.getString("foto"));
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
