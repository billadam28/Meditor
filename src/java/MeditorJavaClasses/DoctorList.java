/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorInterfaces.LoadListService;
import MeditorPersistence.Doctor;
import MeditorPersistence.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author adamopoulo
 */
public class DoctorList implements LoadListService {
    private List<Doctor> doctorList;
    private final String doctorListQuery;
    
    public DoctorList() {
        doctorList = new ArrayList<>();
        doctorListQuery = "select d from Doctor d";
    }
    

    @Override
    public void populateDefaultList() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(doctorListQuery);
            List<Doctor> res = (List<Doctor>) query.list();
            for (Doctor dct : res) {
                // we get lazy initialization exception, so we need to 
                // initialize the child objects in order to access them in 
                // the jsp page after we close the session.
                Hibernate.initialize(dct.getAssignedVisitor());
                Hibernate.initialize(dct.getSpecialty());
                Hibernate.initialize(dct.getInstitution());
                Hibernate.initialize(dct.getInstitution().getCity());
                Hibernate.initialize(dct.getInstitution().getCity().getGeoArea());
                doctorList.add(dct);
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
    
    public List<Doctor> getDoctorList () {
        return this.doctorList;
    }

}
