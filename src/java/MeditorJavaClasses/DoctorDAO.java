/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorPersistence.Doctor;
import MeditorPersistence.NewHibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author glalas
 */
public class DoctorDAO {
    
    
    public void addDoctor(Doctor doctor){
        Transaction tx = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try{
            tx = session.beginTransaction();
            session.save(doctor);
            tx.commit();
        }catch (HibernateException e){
            if (tx!=null){
                tx.rollback();
            }
            e.printStackTrace();}
            finally{
            session.close();
        }
    }
    
    
    public void updateDoctor(Doctor doctor){
        Transaction tx = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try{
            tx = session.beginTransaction();
            session.update(doctor);
            tx.commit();
        }catch (HibernateException e){
            if (tx!=null){
                tx.rollback();
            }
            e.printStackTrace();}
            finally{
            session.close();
        }
    }
    
    
}
