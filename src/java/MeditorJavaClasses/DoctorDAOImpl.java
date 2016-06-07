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
 * DoctoDAOImpl class that implements the DoctorDAO interface
 * @author glalas
 */
public class DoctorDAOImpl implements DoctorDAO {
    
    
    @Override
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
    
    
    @Override
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

    /*
     * This method is not implemented yet since this feature was not asked in feature 4.
     */
    @Override
    public void deleteDoctor(Doctor doctor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /*
     * This method is not implemented yet since this feature was not asked in feature 4.
     */
    @Override
    public void readDoctor(Integer docId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
