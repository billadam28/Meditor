/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorPersistence.Doctor;
import java.util.List;
import java.util.ArrayList;
import MeditorPersistence.NewHibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author thodo
 */
public class VisitServices {
    private List<Doctor> doctorsList;
    private final String getDoctorsQuery;
    
    public VisitServices() {
        doctorsList = new ArrayList<>();      
        getDoctorsQuery = "select d from Doctor d where assignedVisitor!=null";
    }
    
    public void showDoctorsList(int userId) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select username from Visitor where user_id='"+userId+"'");
        //System.out.println(parentGroup);
        String visitor = query.uniqueResult().toString();
        //Integer vstId = Integer.parseInt(vid);
        System.out.println(visitor);
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            List<Doctor> doctors  = session.createQuery(getDoctorsQuery).list();
            for (Doctor doctor : doctors) {
                Hibernate.initialize(doctor.getAssignedVisitor());
                Hibernate.initialize(doctor.getSpecialty());
                Hibernate.initialize(doctor.getInstitution());
                Hibernate.initialize(doctor.getInstitution().getCity());
                Hibernate.initialize(doctor.getInstitution().getCity().getGeoArea());
                if(doctor.getAssignedVisitor().getUsername().equals(visitor)){
                    doctorsList.add(doctor);
                }
                
                //System.out.println(group.getName() + group.getId());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
                 
    }
    
    public List<Doctor> doctorsList () {
        return this.doctorsList;
    }
    
}
