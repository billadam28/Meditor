/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorServlets;

import MeditorJavaClasses.SearchEditDoc;
import MeditorJavaClasses.VisitorDAO;
import MeditorJavaClasses.AddDoctorLists;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author glalas
 */
public class SearchEditSrvlt extends HttpServlet {

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
        if ((session == null) || (session.getAttribute("userId") == null)) {
            this.getServletConfig().getServletContext().getRequestDispatcher("/index.jsp?noSession=1").forward(request, response);
        } else {
            //Fetch The lists for Edit Results
            AddDoctorLists ad = new AddDoctorLists();
            ad.makeLists();
            ad.getSpecialtyList();
            ad.getGeoAreaList();
            ad.getCityList();
            ad.getInstituteList();
            VisitorDAO vstDao= new VisitorDAO();
            vstDao.getAllVisitors();
            request.setAttribute("Visitor", vstDao);
            request.setAttribute("City", ad);
            request.setAttribute("Institution", ad);
            request.setAttribute("GeographicalArea", ad);
            request.setAttribute("Specialty",ad);
            //Search Feature Implementation
            if((request.getParameter("name") !=null) && (request.getParameter("specialty").equals("Select A Specialty")) && (request.getParameter("visitor").equals("Select A Visitor")) && (request.getParameter("institution").equals("Select Institution")) ){
            SearchEditDoc search = new SearchEditDoc();
            String docname = request.getParameter("name");
            search.SearchbyName(docname);
            search.getSearchResults();
            request.setAttribute("Doctor", search);
            } else if ((request.getParameter("name").equals("")) && (!request.getParameter("specialty").equals("Select A Specialty")) && (request.getParameter("visitor").equals("Select A Visitor")) && (request.getParameter("institution").equals("Select Institution")) ){
                SearchEditDoc search = new SearchEditDoc();
                String specialty = request.getParameter("specialty");
                int specialid = Integer.parseInt(specialty);
                search.searchBySpecialty(specialid);
                search.getSearchResults();
                request.setAttribute("Doctor", search);
            } else if ((request.getParameter("name").equals("")) && (request.getParameter("specialty").equals("Select A Specialty")) && (!request.getParameter("visitor").equals("Select A Visitor")) && (request.getParameter("institution").equals("Select Institution")) ){
              SearchEditDoc search = new SearchEditDoc();
              String vstID = request.getParameter("visitor");
              int vstId = Integer.parseInt(vstID);
              search.searchByAssignedVisitor(vstId);
              search.getSearchResults();
              request.setAttribute("Doctor", search);
            } else if ((request.getParameter("name").equals("")) && (request.getParameter("specialty").equals("Select A Specialty")) && (request.getParameter("visitor").equals("Select A Visitor")) && (!request.getParameter("institution").equals("Select Institution")) ){
              SearchEditDoc search = new SearchEditDoc();
              String instID = request.getParameter("institution");
              int instId = Integer.parseInt(instID);
              search.searchByInstitution(instId);
              search.getSearchResults();
              request.setAttribute("Doctor", search);
            } else {
                this.getServletConfig().getServletContext().getRequestDispatcher("/searcherror.jsp").forward(request, response);
            }

            this.getServletConfig().getServletContext().getRequestDispatcher("/displaysearch.jsp").forward(request, response);
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
