/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorServlets;

import MeditorJavaClasses.LoginService;
import MeditorPersistence.NewHibernateUtil;
import MeditorPersistence.User;
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
public class LoginSrvlt extends HttpServlet {
    private static NewHibernateUtil hibernateUtil;
    
    /**
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException 
    {
        hibernateUtil = new NewHibernateUtil();
    }
    
    /**
     *
     */
    @Override
    public void destroy()
    {
        NewHibernateUtil.getSessionFactory().close();
    }

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
        
        String userId = request.getParameter("userId");   
        String password = request.getParameter("password");
        LoginService loginService = new LoginService((String)request.getParameter("userId"), (String)request.getParameter("password"));
        boolean result = loginService.authenticateUser();
        User user = loginService.getUserByEmailOrUsername((String)request.getParameter("userId"));
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
        if(result == true){
            //request.getSession().setAttribute("user", user);      
            //response.sendRedirect("home.jsp");
            String surname = user.getSurname();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestHibernate</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>User logged in. User Surname: " + surname + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        else
        {
         //response.sendRedirect("error.jsp");
            out.println("Wrong user, try again");
        }

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
