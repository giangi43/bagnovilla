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
public class TimeSlotFactory {
    
    private static TimeSlotFactory instance;

    public TimeSlotFactory() {}
    
    public static TimeSlotFactory getInstance(){
        if(instance == null)
            instance = new TimeSlotFactory();
        return instance;
    }
    
    public List<TimeSlot> getAllUsers(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        List<TimeSlot> slots = new ArrayList<>();
        
        try{
            conn = DatabaseManager.getInstance().getDbConnection();
            String query = "SELECT * FROM Time_slot";
            stmt = conn.prepareStatement(query);
            set = stmt.executeQuery();
            
            while(set.next()){
                TimeSlot slot = new TimeSlot();
                
                slot.setId(set.getLong("id"));
                
                Timestamp ts = set.getTimestamp("booking_date");                
                LocalDateTime localDt = null;
                if(ts != null){
                    localDt = LocalDateTime.ofInstant(
                            Instant.ofEpochMilli(ts.getTime()),
                            ZoneOffset.UTC);
                }
                slot.setBooking_date(localDt);
                slot.setIs_morning(set.getBoolean("is_morning"));
                slot.setAviable_spots(set.getInt("aviable_spots"));
                
               
                slots.add(slot);
            }
            return slots;
        }catch(SQLException e){
            Logger.getLogger(TimeSlotFactory.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            try{ set.close(); } catch (Exception e){}
            try{ stmt.close();} catch(Exception e){}
            try{ conn.close();} catch(Exception e){}
        }
        return null;
    }
    
}
