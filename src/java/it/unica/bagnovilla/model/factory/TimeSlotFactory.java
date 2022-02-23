/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.bagnovilla.model.factory;

import it.unica.bagnovilla.model.entity.TimeSlot;
import it.unica.bagnovilla.db.DatabaseManager;
import it.unica.bagnovilla.utils.FactoryUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
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
    
    public TimeSlot getTimeSlotByDatePeriod(LocalDate date, Boolean period){
        Connection conn= null;
        PreparedStatement stmt = null;
        ResultSet set = null;
        
        
        try{
            Timestamp timestamp = FactoryUtil.localDateToTimestamp(date);
            conn = DatabaseManager.getInstance().getDbConnection();
            String query = "SELECT * FROM Time_slot WHERE Booking_date = ? AND Is_morning = ?";
            stmt = conn.prepareStatement(query);
            stmt.setTimestamp(1, timestamp);
            stmt.setBoolean(2, period);
            
            set = stmt.executeQuery();
            
            if(set.next()){
                TimeSlot slot = new TimeSlot();
               
                
                slot.setIs_morning(set.getBoolean("is_morning"));
   
                slot.setBooking_date(FactoryUtil.sqlTimestampToLocalDate(set.getTimestamp("booking_date")));                
               
                slot.setAviable_spots(set.getInt("aviable_spots"));
                
                slot.setIdLifeguard(set.getInt("id_lifeguard"));
                
                return slot;
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
    
    public List<TimeSlot> getAllTimeSlots(){
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
                
                slot.setIs_morning(set.getBoolean("is_morning"));
   
                slot.setBooking_date(FactoryUtil.sqlTimestampToLocalDate(set.getTimestamp("booking_date")));                
               
                slot.setAviable_spots(set.getInt("aviable_spots"));
                
                slot.setIdLifeguard(set.getInt("id_lifeguard"));               
               
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
