/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorServlets;

import MeditorJavaClasses.DoctorDAO;
import MeditorJavaClasses.addDocQ;
import MeditorPersistence.Doctor;
import MeditorPersistence.Institution;
import MeditorPersistence.NewHibernateUtil;
import MeditorPersistence.Specialty;
import MeditorPersistence.Visitor;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author glalas
 */
public class SubmitDocServlet extends HttpServlet {

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
            Session hibersession = NewHibernateUtil.getSessionFactory().openSession();

            
            try {
               
                Integer myuserid = (Integer) session.getAttribute("userId");
                Visitor vstID = (Visitor)hibersession.get(Visitor.class, myuserid);
                String name = request.getParameter("name");
                String address =request.getParameter("address");
                String phone = request.getParameter("phone");    
                String specialty = request.getParameter("specialty");
                int specialid = Integer.parseInt(specialty);
                String institution =request.getParameter("institution");
                int institutionid = Integer.parseInt(institution);
                String position = request.getParameter("position");
                
                
                Doctor doctor =new Doctor();
                Specialty spec = (Specialty) hibersession.get(Specialty.class, specialid);
                Institution inst = (Institution) hibersession.get(Institution.class, institutionid);
                doctor.setName(name);
                doctor.setAddress(address);
                doctor.setPhone(phone);
                doctor.setSpecialty(spec);
                doctor.setInstitution(inst);
                doctor.setPosition(position);
                doctor.setCreatedFrom(vstID);
                //Fetch Lists for  Reload in Success-Error
                addDocQ ad = new addDocQ();
                ad.makeLists();
                ad.getSpecialtyList();
                ad.getGeoAreaList();
                ad.getCityList();
                ad.getInstituteList();
                request.setAttribute("City", ad);
                request.setAttribute("Institution", ad);
                request.setAttribute("GeographicalArea", ad);
                request.setAttribute("Specialty",ad);
                if (ad.checkForDoubles(name, address, phone) == true){

                    request.setAttribute("revealCreateErrorMsg","true");
                    this.getServletConfig().getServletContext().getRequestDispatcher("/addDoc.jsp").forward(request, response);
                } else{
                DoctorDAO doctordao= new DoctorDAO();
                doctordao.addDoctor(doctor);
                request.setAttribute("revealCreateSuccessMsg", "true");
                this.getServletConfig().getServletContext().getRequestDispatcher("/addDoc.jsp").forward(request, response);
                }
               
            }
            catch (HibernateException e) {
    
            e.printStackTrace();
        } finally {
            hibersession.close();
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
