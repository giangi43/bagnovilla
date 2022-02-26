/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.bagnovilla.Repos;

import it.unica.bagnovilla.Db.DatabaseManager;
import it.unica.bagnovilla.Models.CommonResponse;
import it.unica.bagnovilla.Models.SlotViewModel;
import it.unica.bagnovilla.Models.UserModel;
import it.unica.bagnovilla.model.entity.TimeSlot;
import it.unica.bagnovilla.utils.FactoryUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author fpw
 */
public class BookingRepo {
    private static final Logger LOG = Logger.getLogger(UserRepo.class.getName());
    private static BookingRepo instance;
    
    public BookingRepo() {
    }
    
    // get a BookingRepo instance by Singleton
    public static BookingRepo getInstance(){
        if(instance == null)
            instance = new BookingRepo();
        return instance;
    }
    // 
    public CommonResponse addSlot(TimeSlot reservation){
        // Connection parameters
        Connection conn= null;
        PreparedStatement stmt = null;
        ResultSet set = null; 
        LocalDate date = null;
        boolean is_morning = false;
        
        try{
            // Opening Connection
            conn = DatabaseManager.getInstance().getDbConnection();
            // Controllo se esiste gia uno slot e prendo l'id per farci un update sopra
            String query = "select booking_date, is_morning from time_slot where booking_date = ? and is_morning = ?;";
            stmt = conn.prepareStatement(query);
            stmt.setObject(1, reservation.getBooking_date());
            stmt.setBoolean(2, reservation.isIs_morning());
            // mando la query a db 
            set = stmt.executeQuery();
            // fetcho l'id che ho ricevuto
            if (set.next()) {
                date = FactoryUtil.dateToLocalDate(set.getDate("booking_date"));
                is_morning = set.getBoolean("is_morning");
            }
            
            /* se lo slot esiste già aggiungo i posti richiesti */
            if(date != null) {
                query = "update time_slot set aviable_spots = ? where booking_date = ? and is_morning = ?;";
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, reservation.getAviable_spots());
                stmt.setDate(2, FactoryUtil.localDateToDate(date));
                stmt.setBoolean(3, is_morning);
                stmt.executeUpdate();
            } else {
                /* se lo slot non esisteva prima allora lo inserisco */
                query = "insert into time_slot (booking_date, is_morning, aviable_spots) values (?, ?, ?);";
                stmt = conn.prepareStatement(query);
                stmt.setDate(1, FactoryUtil.localDateToDate(reservation.getBooking_date()));
                stmt.setBoolean(2, reservation.isIs_morning());
                stmt.setInt(3, reservation.getAviable_spots());

                /* executing and returning success */
                stmt.executeUpdate();
            }
            return new CommonResponse(true,"Ok", null);
            
        }catch(SQLException e){
            Logger.getLogger(UserRepo.class.getName()).severe(e.getMessage());
            return new CommonResponse(false,e.getMessage(),e);
        } finally {
            try{ set.close();} catch(Exception e){}
            try{ stmt.close();} catch(Exception e){}
            try{ conn.close();} catch(Exception e){}
        }
    }
    public CommonResponse getSlotCalendar(LocalDate monthYear){
        /* se cosi non fosse gia forzo ad usare il primo giorno del mese*/
        monthYear = monthYear.withDayOfMonth(1);
               
        // Connection parameters
        Connection conn= null;
        PreparedStatement stmt = null;
        ResultSet set = null; 
        
        try{
            // Opening Connection
            conn = DatabaseManager.getInstance().getDbConnection();
            String query = "select ts1.*,sum(booked_places)"
                    + " from time_slot ts1"
                    + " left Join service_details "
                    + " on ts1.booking_date = service_details.booking_date AND ts1.is_morning = service_details.is_morning "
                    + " where extract('month' from ts1.booking_date) = ? "
                    + " group by ts1.booking_date, ts1.is_morning "
                    + " order by ts1.booking_date, is_morning";
            stmt = conn.prepareStatement(query);
            stmt.setObject(1, monthYear.getMonthValue());
            
            LOG.info("getting timeslots :\n" + stmt.toString());
        
            /* fetching dei risultati dalla query nel mio modello */
            set = stmt.executeQuery();
            TimeSlot slot = new TimeSlot();
            Queue<TimeSlot> querySlots = new LinkedList<TimeSlot>();
            while(set.next()){
                /* creo lo slot e lo metto nel dizionario */
                slot.setBooking_date(FactoryUtil.dateToLocalDate(set.getDate("booking_date")));
                slot.setAviable_spots(set.getInt("aviable_spots"));
                slot.setIs_morning(set.getBoolean("is_morning"));            
                try{
                    slot.setEmptySpots(slot.getAviable_spots() - set.getInt("sum"));
                }catch (Exception e){
                    slot.setEmptySpots(slot.getAviable_spots());
                }
                querySlots.add(slot);
            }
            
            
            
            
            /* il mio range di date è dal primo del mese all'ultimo */
            LocalDate iterDate = monthYear;
            LocalDate endDate = monthYear.withDayOfMonth(monthYear.lengthOfMonth());
            
            /* costruisco una collezione contenente tutti gli slot del mese ma vuoti */
            ArrayList<SlotViewModel> fullSlots = new ArrayList<SlotViewModel>();
            String timeAmPm = "AM"; // variabile cambia tra AM e PM
            while(iterDate.isBefore(endDate)) {
                int currDay = iterDate.getDayOfMonth();
                SlotViewModel s = new SlotViewModel(currDay, timeAmPm, 0, 0){};
                fullSlots.add(s);
                
                /* vado avanti di un girno e inverte am pm e viceversa */
                iterDate = iterDate.withDayOfMonth(currDay+1);
                timeAmPm = timeAmPm.equals("AM")?"PM":"AM"; // inverto AM-PM e viceversa
            }
            /* tratto separatamente l'ultimo del mese */
            SlotViewModel s = new SlotViewModel(iterDate.getDayOfMonth(), "AM", 0, 0){};
            fullSlots.add(s);
            s = new SlotViewModel(iterDate.getDayOfMonth(), "PM", 0, 0){};
            fullSlots.add(s);
            
            /* Ora faccio una "join" per mettere i valori sugli slot presenti */
            iterDate = monthYear;
            while(!querySlots.isEmpty()) {
                /* tolgo l'elemento da spostare dalla coda */
                TimeSlot dbSlot = querySlots.poll();

                /* prendo l'elemento corrispondente dalla lista completa */
                SlotViewModel matchingSlot = fullSlots.stream()
                        .filter(x -> x.day == dbSlot.getBooking_date().getDayOfMonth())
                        .findAny()
                        .orElse(null);
                
                /* controllo se am o pm e setto il numero di slot disponibili 
                 * nel posto giusto */
                if(dbSlot.isIs_morning()) {
                    matchingSlot.numAm = dbSlot.getEmptySpots();
                } else {
                    matchingSlot.numPm = dbSlot.getEmptySpots();
                }
                int a =1;
                a=a+2;
            }
            return new CommonResponse(true, "Ok", fullSlots);
        }catch(SQLException e){
            Logger.getLogger(UserRepo.class.getName()).severe(e.getMessage());
            return new CommonResponse(false,e.getMessage(),e);
        } finally {
            try{ set.close();} catch(Exception e){}
            try{ stmt.close();} catch(Exception e){}
            try{ conn.close();} catch(Exception e){}
        }
    }

    public CommonResponse bookSlots(LocalDate date, boolean isMorning, int numReservedSlots, String username) {
         // Connection parameters
        Connection conn= null;
        PreparedStatement stmt = null;
        ResultSet set = null; 
        
        try {
             // Opening Connection
            conn = DatabaseManager.getInstance().getDbConnection();
            // Prepearing the query ordered by date and then day part so that it will be AM then PM
            String query = "select ts1.*,sum(booked_places)"
                    + " from time_slot ts1"
                    + " left Join service_details "
                    + " on ts1.booking_date = service_details.booking_date AND ts1.is_morning = service_details.is_morning "
                    + " where ts1.booking_date = ? and  is_morning = ?"
                    + " group by ts1.booking_date, ts1.is_morning "
                    + " order by ts1.booking_date, is_morning";
            stmt = conn.prepareStatement(query);
            stmt.setObject(1, date);
            stmt.setObject(2, isMorning);
            
            LOG.info("getting timeslots in that range :\n" + stmt.toString());
        
            /* fetching dei risultati dalla query nel mio modello */
            set = stmt.executeQuery();
            TimeSlot slot = new TimeSlot();
            if(set.next()){
                /* creo lo slot e lo metto nel dizionario */
                slot.setBooking_date(FactoryUtil.dateToLocalDate(set.getDate("booking_date")));
                slot.setAviable_spots(set.getInt("aviable_spots"));
                slot.setIs_morning(set.getBoolean("is_morning"));            
                try{
                    slot.setEmptySpots(slot.getAviable_spots() - set.getInt("sum"));
                }catch (Exception e){
                    slot.setEmptySpots(slot.getAviable_spots());
                }
            }else{
                slot=null;
            }
            
                      
            
                    /* se non sono stati trovati lancio un eccezione */
            if (slot == null ) {
                throw new Exception("Errore: non e stato trovato lo slot "
                        + "in data " + date.toString());
            }
            /* se non bastano i posti lancio un eccezione */
            if (slot.getEmptySpots() < numReservedSlots) {
                throw new Error("Errore: non sono disponibili sufficienti " + slot.getEmptySpots());
            }
            int id = 0;
            query ="select max(id) from service_details;";
            stmt = conn.prepareStatement(query);
            set = stmt.executeQuery();
            id = 1 + set.getInt("max");
            
                           
            /* se siamo arrivati qua allora ci sono i posti necessari per effettuare la prenotazione */
            query = "INSERT INTO public.service_details " 
                    + " (id, booking_date, is_morning, price, booked_places, service_description) " 
                    + " VALUES" 
                    + " (?, ?, ?, 0, ?, 'none');"
                    + "INSERT INTO public.booking" 
                    + "	(id, username, id_service)" 
                    + "	VALUES (default, ?, ?) ";
            
            /* preparo e lancio la query */
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.setDate(2, FactoryUtil.localDateToDate(date));
            stmt.setBoolean(3, isMorning);
            stmt.setInt(4, numReservedSlots);
            stmt.setString(5, username);
            stmt.setInt(6, id);
            stmt.executeQuery();
            
            
            return new CommonResponse(true,"Prenotazione avvenuta con successo", null);
                       
        }catch(Exception e){
            Logger.getLogger(UserRepo.class.getName()).severe(e.getMessage());
            return new CommonResponse(false,e.getMessage(),e);
        } finally {
            try{ set.close();} catch(Exception e){}
            try{ stmt.close();} catch(Exception e){}
            try{ conn.close();} catch(Exception e){}
        }
    }
    
}
