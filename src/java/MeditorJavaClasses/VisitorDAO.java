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
 * The Data Access Object class for Visitor table
 * @author glalas
 */
public class VisitorDAO {
    
    /**
     * This method is responsible for adding a new Visitor to the DB
     * @param visitor 
     */
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
    
    /**
     * This method updates an existing entry in the Visitor table
     * @param visitor 
     */
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
    
    /**
     * This method returns all the entries from the Visitor table
     * @return a list of Visitor objects
     */
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


    
    /**
     * This method deletes an entry from the Visitor table
     * @param userid Integer
     */
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