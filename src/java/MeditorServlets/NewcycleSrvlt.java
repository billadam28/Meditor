/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorServlets;

import static MeditorJavaClasses.GlobalConstants.TYPE_ADMIN;
import static MeditorJavaClasses.GlobalConstants.TYPE_VISITOR;
import MeditorJavaClasses.LoginService;
import MeditorJavaClasses.ProcessCycle;
import MeditorPersistence.Doctor;
import MeditorPersistence.NewHibernateUtil;
import MeditorPersistence.User;
import java.io.IOException;
import static java.time.Clock.system;
import java.util.Date;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thodo
 */
public class NewcycleSrvlt extends HttpServlet {

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
        
        //object period
        
        HttpSession session = request.getSession(false);
        
        if ((session == null) || (session.getAttribute("userId") == null)) {
            this.getServletConfig().getServletContext().getRequestDispatcher("/index.jsp?noSession=1").forward(request, response);
            System.out.println("null");
        } else {
            //fere giatrous pou den exoun assigned period k na exoun assigned visitor
            //fere ta periods
            String doctorId = request.getParameter("doctorId");
            ProcessCycle processCycle = new ProcessCycle((String)request.getParameter("doctorId")); 
            Doctor doctor = processCycle.getAssignedDoctorWithNoPeriod();
            session.setAttribute("doctorId", doctor.getId());
            session.setAttribute("name", doctor.getName());
            
            this.getServletConfig().getServletContext().getRequestDispatcher("/newcycle.jsp").forward(request, response);
            System.out.println("not null");
            //System.out.println(session.getAttribute("username"));
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
