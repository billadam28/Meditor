/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorServlets;

import MeditorJavaClasses.DoctorList;
import MeditorPersistence.Doctor;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adamopoulo
 */
public class AsyncUpdateDoctorList extends HttpServlet {

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
        DoctorList docList = new DoctorList();
        docList.populateListWithParameters( Integer.parseInt(request.getParameter("institution")), 
                Integer.parseInt(request.getParameter("specialty")));
        
        if (!docList.getDoctorList().isEmpty()) { // list is not empty, create xml response
            for (Doctor dct : docList.getDoctorList()) {
                sb.append("<doctor>");
                sb.append("<id>").append(dct.getId()).append("</id>");
                sb.append("<name>").append(dct.getName()).append("</name>");
                if (dct.getAssignedVisitor() != null) {
                    sb.append("<assignedVisitor>").append(dct.getAssignedVisitor().getSurname()).append("</assignedVisitor>");
                }
                else {
                    sb.append("<assignedVisitor>").append("---").append("</assignedVisitor>");
                }  
                sb.append("<specialty>").append(dct.getSpecialty().getSpecialtyName()).append("</specialty>");
                sb.append("<position>").append(dct.getPosition()).append("</position>");
                sb.append("<institution>").append(dct.getInstitution().getInstitutionName()).append("</institution>");
                sb.append("<geoArea>").append(dct.getInstitution().getCity().getGeoArea().getGeoName()).append("</geoArea>");
                sb.append("<city>").append(dct.getInstitution().getCity().getCityName()).append("</city>");
                sb.append("</doctor>");
            }
            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write("<doctors>" + sb.toString() + "</doctors>");
            
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
