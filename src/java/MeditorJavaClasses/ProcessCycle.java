/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorPersistence.Doctor;
import MeditorPersistence.NewHibernateUtil;
import MeditorPersistence.User;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author thodo
 */
public class ProcessCycle {
    private final String doctorId;

    public ProcessCycle(String doctorId) {
        this.doctorId = doctorId;
    }
    
    public Doctor getAssignedDoctorWithNoPeriod() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        //Doctor doctor = null;
        try {
            tx = session.beginTransaction();
            List doctors = session.createQuery("from doctor where assigned_vst_id != ''").list();
            for (Iterator iterator = doctors.iterator(); iterator.hasNext();) {
                Doctor doctor = (Doctor) iterator.next(); 
                System.out.println("First Name: " + doctor.getName()); 
                System.out.println("  Assigned visitor: " + doctor.getAssignedVstId()); 
                System.out.println("  Address: " + doctor.getAddress()); 
            
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

        return doctor;
    }
}
