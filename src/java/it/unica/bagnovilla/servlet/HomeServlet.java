/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.bagnovilla.servlet;

import it.unica.bagnovilla.exceptions.SqlException;
import it.unica.bagnovilla.model.entity.TimeSlot;
import it.unica.bagnovilla.model.factory.TimeSlotFactory;
import it.unica.bagnovilla.utils.HttpUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author fpw
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/homeServlet"})
public class HomeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String command = request.getParameter("offset");
        
        int nElements = 4;
        int offsetSize = 4;
        try {
            if (command != null) {               
                
                int offset = Integer.parseInt(request.getParameter("offset"));
                offsetSize = offsetSize * offset;
                List<TimeSlot> timeSlots = TimeSlotFactory.getInstance().getNextNTimeSlotsWithOffset(nElements,offsetSize);

                request.setAttribute("nTimeSlots", timeSlots.size());

                request.setAttribute("timeSlots", timeSlots);
                HttpUtils.httpSet(request, response, "timeSlotJSON.jsp");
            } else {
                List<TimeSlot> timeSlots = TimeSlotFactory.getInstance().getNextNTimeSlotsWithOffset(nElements,0);

                request.setAttribute("nTimeSlots", timeSlots.size());
                request.setAttribute("timeSlots", timeSlots);
                request.getRequestDispatcher("home.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
