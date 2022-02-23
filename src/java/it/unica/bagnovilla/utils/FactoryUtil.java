/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.bagnovilla.utils;

import it.unica.bagnovilla.db.DatabaseManager;
import it.unica.bagnovilla.exceptions.SqlException;
import it.unica.bagnovilla.model.entity.User;
import it.unica.bagnovilla.model.factory.UserFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fpw
 */
public class FactoryUtil {
    public static void insertInTable(String table, String[] attributes, Object[] values) throws SqlException{
        Connection conn = null;
        PreparedStatement stmt = null;
        
        int i;
        
        String query = "INSERT INTO " +table+ " (";
            String valuesSql =" VALUES(";
            
            for(i=0; i<attributes.length-1; i++){
                query = query + attributes[i]+",";
                valuesSql = valuesSql + "?,";
            }
            valuesSql = valuesSql + "?)";
            query = query + attributes[i] + valuesSql;
               
        try {

            conn = DatabaseManager.getInstance().getDbConnection();
            
            stmt = conn.prepareStatement(query);
            
            
            for (i=0; i<attributes.length; i++){
                stmt.setObject(i, values[i]);
                /*
                try{
                    int n =(Integer) values[i];
                    stmt.setInt(i, n);
                    return;
                }catch(RuntimeException e){
                }
                try{
                    boolean n =(boolean) values[i];
                    stmt.setBoolean(i, n);
                    return;
                }catch(RuntimeException e){           

                    if(values[i] instanceof String){
                        stmt.setString(i, (String) values[i]);
                    }else if(values[i] instanceof LocalDate){
                        stmt.setDate(i, Utils.localDateToDate((LocalDate) values[i]));
                    }else if(values[i] instanceof LocalDate){
                        stmt.setDate(i, Utils.localDateToDate((LocalDate) values[i]));
                    }
                }*/
            }

            stmt.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(FactoryUtil.class.getName()).log(Level.SEVERE, null, e);
            throw new SqlException(e.getMessage());
        } 
        
    }
    
    
    public static void updateTable(String table, String[] attributes, Object[] values, String[] conditions) throws SqlException{
        Connection conn = null;
        PreparedStatement stmt = null;
        
        int i;
        
        String query = "UPDATE " +table+ " SET (";
                    
        for(i=0; i<attributes.length-1; i++){
            query = query + attributes[i]+" = ?, ";

        }
        query = query + attributes[i]+" = ? WHERE";
        for (i=0; i<conditions.length;i++){
            query = query + conditions[i]+" ";
        } 
               
        try {

            conn = DatabaseManager.getInstance().getDbConnection();
            
            stmt = conn.prepareStatement(query);
            
            
            for (i=0; i<attributes.length; i++){
                stmt.setObject(i, values[i]);
                /*
                try{
                    int n =(Integer) values[i];
                    stmt.setInt(i, n);
                    return;
                }catch(RuntimeException e){
                }
                try{
                    boolean n =(boolean) values[i];
                    stmt.setBoolean(i, n);
                    return;
                }catch(RuntimeException e){           

                    if(values[i] instanceof String){
                        stmt.setString(i, (String) values[i]);
                    }else if(values[i] instanceof LocalDate){
                        stmt.setDate(i, Utils.localDateToDate((LocalDate) values[i]));
                    }else if(values[i] instanceof LocalDate){
                        stmt.setDate(i, Utils.localDateToDate((LocalDate) values[i]));
                    }
                }*/
            }

            stmt.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(FactoryUtil.class.getName()).log(Level.SEVERE, null, e);
            throw new SqlException(e.getMessage());
        } 
        
    }
    
    
    /*
    public static PreparedStatement customStmt(String[] selection, String table, String[] conditions, Object... args) throws SqlException{
         Connection conn = null;
        PreparedStatement stmt = null;
        int i=0;
        List<ResultSet> resultSet = new ArrayList<>();

        String query = "SELECT ";
        for (i=0; i<selection.length-1;i++){
            query = query + selection[i]+",";
        }
        query = query + selection[i] +" FROM " + table + " WHERE ";
        for (i=0; i<conditions.length;i++){
            query = query + conditions[i]+" ";
        }       
     
        
        try {
            conn = DatabaseManager.getInstance().getDbConnection();

            
            stmt = conn.prepareStatement(query);
            for (i=0; i<conditions.length; i++){
                stmt.setObject(i, args[i]);
            }
            
            
            return stmt;
        } catch (SQLException ex) {
            Logger.getLogger(FactoryUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
            return null;    
    
    }
    
    public static List<ResultSet> customSelect(String[] selection, String table, String[] conditions, Object... args) throws SqlException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int i=0;
        List<ResultSet> resultSet = new ArrayList<>();

        String query = "SELECT ";
        for (i=0; i<selection.length-1;i++){
            query = query + selection[i]+",";
        }
        query = query + selection[i] +" FROM " + table + " WHERE ";
        for (i=0; i<conditions.length;i++){
            query = query + conditions[i]+" ";
        }       
     
        
        try {
            conn = DatabaseManager.getInstance().getDbConnection();

            
            stmt = conn.prepareStatement(query);
            for (i=0; i<conditions.length; i++){
                stmt.setObject(i, args[i]);
            }
            
            set = stmt.executeQuery();
            
            while(set.next()){
                resultSet.add(set);
            }
            return resultSet;
            
        } catch (SQLException e) {
            Logger.getLogger(FactoryUtil.class.getName()).log(Level.SEVERE, null, e);
            throw new SqlException(e.getMessage());
        } finally {
            try {
                set.close();
            } catch (Exception e) {
            }
            try {
                stmt.close();
            } catch (Exception e) {
            }
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
    
    }
    
    private static void stmtSet (PreparedStatement stmt, int index, Object o) throws SqlException {
        
        
    }

*/
    public static String convertTime(long time){
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.setTimeInMillis(time);
        return (cal.get(Calendar.YEAR)+"_"+(cal.get(Calendar.MONTH)+1)+"_"
                + cal.get(Calendar.DAY_OF_MONTH)+"_"+ cal.get(Calendar.HOUR_OF_DAY)+":"
                + cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND));
    }
    
    public static LocalDateTime timestampToLocalDateTime (Timestamp ts){
        LocalDateTime localDt = null;
                if(ts != null){
                    localDt = LocalDateTime.ofInstant(
                            Instant.ofEpochMilli(ts.getTime()),
                            ZoneOffset.UTC);
                }
        return localDt;
    }
    
    public static LocalDate sqlTimestampToLocalDate(Timestamp ts){
     return ts.toLocalDateTime().toLocalDate();
    }
    
    public static Timestamp localDateToTimestamp(LocalDate ld){
        return Timestamp.valueOf(ld.atStartOfDay());
    }
    public static Timestamp localDateTimeToTimestamp(LocalDateTime ldt){
        return Timestamp.valueOf(ldt);
    }
    public static Date localDateToDate(LocalDate ld){
        return Date.valueOf(ld);
    }
    public static LocalDate dateToLocalDate(Date d){
        return d.toLocalDate();
    }
    
}

