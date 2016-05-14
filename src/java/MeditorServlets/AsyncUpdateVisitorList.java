/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorServlets;

import MeditorJavaClasses.VisitorList;
import MeditorPersistence.Visitor;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adamopoulo
 */
public class AsyncUpdateVisitorList extends HttpServlet {

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
        StringBuilder sb = new StringBuilder();
        VisitorList vstList = new VisitorList();
        vstList.populateListWithParameters( Integer.parseInt(request.getParameter("group")));
        
        if (!vstList.getVisitorList().isEmpty()) { // list is not empty, create xml response
            for (Visitor vst : vstList.getVisitorList()) {
                sb.append("<visitor>");
                sb.append("<id>").append(vst.getId()).append("</id>");
                sb.append("<name>").append(vst.getFirstname()).append(" ").append(vst.getSurname()).append("</name>");
                sb.append("<level>").append(vst.getVisitorLevel()).append("</level>");
                if (vst.getSuperior() != null) {
                    sb.append("<superior>").append(vst.getSuperior().getSurname()).append("</superior>");
                }
                else {
                    sb.append("<superior>").append("---").append("</superior>");
                }  
                sb.append("<group>").append(vst.getGroup().getName()).append("</group>");
                sb.append("</visitor>");
            }
            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write("<visitors>" + sb.toString() + "</visitors>");
            
        }
        else { // list is empty
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);   
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
