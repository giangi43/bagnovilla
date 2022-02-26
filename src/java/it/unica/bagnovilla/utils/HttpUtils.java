/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.bagnovilla.utils;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fpw
 */
public class HttpUtils {
    public static void httpSet(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        request.getRequestDispatcher(path).forward(request, response);
    }
}
