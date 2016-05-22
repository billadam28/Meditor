/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;


import MeditorPersistence.NewHibernateUtil;
import MeditorPersistence.Visitor;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author glalas
 */
public class VisitorDAO {
    
    
    public void addVisitor(Visitor visitor){
        Transaction tx = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try{
            tx = session.beginTransaction();
            session.save(visitor);
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
    
    
    public void updateVisitor(Visitor visitor){
        Transaction tx = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try{
            tx = session.beginTransaction();
            session.update(visitor);
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
    
    
    public List<Visitor> getAllVisitors() {
    Transaction tx = null;
    List results = null;
    Session session = NewHibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Visitor");
            results =  query.list();
            
            tx.commit();
        } catch (HibernateException e) {
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }


    
    
public void deleteVisitor(int userid) {
        Transaction tx = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            Visitor visitor = (Visitor) session.load(Visitor.class, new Integer(userid));
            session.delete(visitor);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}