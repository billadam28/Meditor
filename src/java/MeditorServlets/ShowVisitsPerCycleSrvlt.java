/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorServlets;

import MeditorJavaClasses.CycleServices;
import MeditorJavaClasses.VisitServices;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thodo
 */
public class ShowVisitsPerCycleSrvlt extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(false);
        String period = "0";
        
        if ((session == null) || (session.getAttribute("userId") == null)) {
            this.getServletConfig().getServletContext().getRequestDispatcher("/index.jsp?noSession=1").forward(request, response);
        } else {
            
            VisitServices visitServices = new VisitServices();
            CycleServices cycleServices = new CycleServices();
            String uId = session.getAttribute("userId").toString();
            int vId = visitServices.getVisitorId(Integer.parseInt(uId));
            
            if (request.getParameterNames().hasMoreElements()) {
                
                period = request.getParameter("period");
                cycleServices.getVisitorVisits(vId, Integer.parseInt(period));
                //System.out.println(vId+" "+Integer.parseInt(period));
            }
            cycleServices.showCyclesList();
            request.setAttribute("vId", vId);
            if (period.equals("0")) {
                request.setAttribute("period", "All periods");
            } else if (period.equals("1")) {
                request.setAttribute("period", "1 Jan. - 31 Mar.");
            } else if (period.equals("2")) {
                request.setAttribute("period", "1 Apr. - 30 June");
            }else if (period.equals("3")) {
                request.setAttribute("period", "1 July - 30 Sept.");
            } else {
                request.setAttribute("period", "1 Oct. - 31 Dec");
            }
            
            request.setAttribute("visitServices", visitServices);
            request.setAttribute("cycleServices", cycleServices);
            this.getServletConfig().getServletContext().getRequestDispatcher("/show_visits_per_cycle.jsp").forward(request, response);
            
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
