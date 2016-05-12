/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorServlets;

import MeditorJavaClasses.VisitServices;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.*;
import java.util.*; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thodo
 */
public class UpdateVisitSrvlt extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
       
        HttpSession session = request.getSession(false);
        
        if ((session == null) || (session.getAttribute("userId") == null)) {
            this.getServletConfig().getServletContext().getRequestDispatcher("/index.jsp?noSession=1").forward(request, response);
        } else {
            
            VisitServices visitServices = new VisitServices();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                
                Date date = format.parse(request.getParameter("date"));
            
            
            
            String status = request.getParameter("status");
            String comments = request.getParameter("comments");
            boolean extra;
            if (request.getParameterValues("extra1")!=null) {
                extra = true;
            } else {
                extra = false;
            }
            System.out.println(date+status+extra+comments);
            //visitServices.updateVisit(date,status,extra,comments);
            request.setAttribute("revealSuccessMsg", "true");
            request.setAttribute("revealForm3", "true");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            request.setAttribute("visitServices", visitServices);
            this.getServletConfig().getServletContext().getRequestDispatcher("/enter_visit_info.jsp").forward(request, response);
            
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
