/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorPersistence.NewHibernateUtil;
import java.util.List;
import java.util.ArrayList;
import org.hibernate.Transaction;
import MeditorPersistence.Cycle;
import MeditorPersistence.Visit;
import MeditorPersistence.Visitor;
import java.util.Set;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author thodo
 */
public class CycleServices {
    private List<Cycle> cyclesList;
    private final String getCyclesQuery;
    private List<Visit> getVisitorVisits;
    private final String getVisitorVisitsQuery;
    
    public CycleServices() {
    
        getVisitorVisits = new ArrayList<>();
        cyclesList = new ArrayList<>();
        getCyclesQuery = "from Cycle c order by c.id asc";
        getVisitorVisitsQuery = "select v from Visit v join v.visitors vv where "
                                  + "vv.id = :vst_id "
                                  + "and (v.status=\'completed\' or v.status=\'unsuccessful\')";
    }
    
    
    public void showCyclesList() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            List<Cycle> noOfCycles  = session.createQuery(getCyclesQuery).list();
            for (Cycle cycle : noOfCycles) {
                //group.getName();
                cyclesList.add(cycle);    
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
   
    public void getVisitorVisits (int vId, int cycleId) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            List<Visit> res =  (List<Visit>)session.createQuery(getVisitorVisitsQuery)
                    .setParameter("vst_id", vId).list();
            for (Visit visit: res) {
                Hibernate.initialize(visit.getDoctor());
                Hibernate.initialize(visit.getVisitors());
                Hibernate.initialize(visit.getCycle());
                
                if (cycleId!= 0) {
                    if((visit.getCycle().getId()== cycleId)){
                        getVisitorVisits.add(visit);
                        //System.out.println(visit.getId());
                    }
                } else {
                    getVisitorVisits.add(visit);
                }    
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
    
    public List<Cycle> cyclesList () {
        return this.cyclesList;
    }
    
    public List<Visit> getVisitorVisits () {
        return this.getVisitorVisits;
    }
}

