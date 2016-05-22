/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorPersistence.NewHibernateUtil;
import MeditorPersistence.Visit;
import MeditorPersistence.Visitor;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author glalas
 */
public class ReportVstHandler {
    private List<Visitor> results;
    private List<Visit> visitresult;

    
    public ReportVstHandler(){
        
    }
    
    public void getAllVisitors() {
    Transaction tx = null;
    results = new ArrayList<>();
    Session session = NewHibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("From Visitor");
            List<Visitor> visitors =  query.list();
            for (Visitor vst : visitors){
                vst.getGroup();
                vst.getSuperior();
                results.add(vst);
                
            }
            tx.commit();
        } catch (HibernateException e) {
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
    }
     public void getStatics(Visitor visitor){
         Transaction tx = null;
         results = new ArrayList<>();
         visitresult = new ArrayList<>();
          Session session = NewHibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            Query query= session.createQuery("Select v from Visitor v ");
            List<Visitor> visitors =  query.list();
            for (Visitor vst : visitors){
                vst.getGroup();
                vst.getSuperior();
                vst.getVisits();
                vst.getVisitorLevel();
                results.add(vst);
                
            }
            tx.commit();
        } catch (HibernateException e) {
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
         
     }
     
    public void findByVstId(Integer vstId){
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        visitresult = new ArrayList<>();
        String hqlquery = "Select  v from Visit v join v.visitors vv where  v.id =vv.id and vv.id= :visitorId " ;
            try{
            tx = session.beginTransaction();
            
            Query query= session.createQuery(hqlquery);
            query.setParameter("visitorId", vstId);
            List<Visit> res = (List<Visit>) query.list();
            for (Visit vst : res) {
                vst.getDoctor();
                vst.getCycle();
                vst.getExtraVisit();
               visitresult.add(vst);
                
            }
            tx.commit();

            }catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            } finally {
                session.close();
            }

    } 
     
     public List<Visitor> getVisitorResults(){
     return this.results;
 }
    public List<Visit> displayVisits(){
        return this.visitresult;
    }
}
