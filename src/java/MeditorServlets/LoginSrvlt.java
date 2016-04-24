/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorServlets;

import static MeditorJavaClasses.GlobalConstants.TYPE_ADMIN;
import static MeditorJavaClasses.GlobalConstants.TYPE_VISITOR;
import MeditorJavaClasses.LoginService;
import MeditorPersistence.NewHibernateUtil;
import MeditorPersistence.User;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        
        HttpSession session = request.getSession(true);
        Date createTime = new Date(session.getCreationTime());
        Date lastAccessTime = new Date(session.getLastAccessedTime());
        
        String userId = request.getParameter("userId");   
        String password = request.getParameter("password");
        LoginService loginService = new LoginService((String)request.getParameter("userId"), (String)request.getParameter("password"));
        User user = loginService.getUserByEmailOrUsername();
        boolean result = loginService.authenticateUser(user);
        
        request.setAttribute("user", user);
        
        if(result == true){
            if (session.isNew()) {
                session.setAttribute("userId", user.getId());
            }

            

            switch (user.getUserType()) {
                case TYPE_ADMIN:
                    this.getServletConfig().getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
                    break;
                case TYPE_VISITOR:
                    this.getServletConfig().getServletContext().getRequestDispatcher("/visitor.jsp").forward(request, response);
                    break;
                default:
                    break;
            }

        } else {
            request.setAttribute("invalidUser", "invalid");
            this.getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
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
