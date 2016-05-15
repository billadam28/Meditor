/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorServlets;

import MeditorJavaClasses.CityList;
import MeditorJavaClasses.InstitutionList;
import MeditorPersistence.City;
import MeditorPersistence.Institution;
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
public class DynamicListLoadSrvlt extends HttpServlet {

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
        
        if (request.getParameter("geoAreaId") != null) {
            CityList cityList = new CityList();
            cityList.populateListWithId(Integer.parseInt(request.getParameter("geoAreaId")));

            if (!cityList.getCityList().isEmpty()) { // list is not empty, create xml response
                for (City city : cityList.getCityList()) {
                    sb.append("<city>");
                    sb.append("<id>").append(city.getId()).append("</id>");
                    sb.append("<name>").append(city.getCityName()).append("</name>");
                    sb.append("</city>");
                }
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<cities>" + sb.toString() + "</cities>");
            }
            else { // list is empty
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);   
            }
        
        }
        else if (request.getParameter("cityId") != null) {
            InstitutionList instList = new InstitutionList();
            instList.populateListPerCity(Integer.parseInt(request.getParameter("cityId")));

            if (!instList.getInstitutionList().isEmpty()) { // list is not empty, create xml response
                for (Institution institution : instList.getInstitutionList()) {
                    sb.append("<institution>");
                    sb.append("<id>").append(institution.getId()).append("</id>");
                    sb.append("<name>").append(institution.getInstitutionName()).append("</name>");
                    sb.append("</institution>");
                }
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<cities>" + sb.toString() + "</cities>");
            }
            else { // list is empty
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);   
            }
        }
        else {
            
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
