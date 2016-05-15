/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorInterfaces.LoadListService;
import MeditorPersistence.Cycle;
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
public class CycleList implements LoadListService{
    private List<Cycle> cycleList;
    private final String cycleListQuery;
    
    public CycleList() {
        cycleList = new ArrayList<>();
        cycleListQuery = "select c from Cycle c";
    }
    
    public List<Cycle> getCycleList () {
        return this.cycleList;
    }
    
    @Override
    public void populateDefaultList() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(cycleListQuery);
            List<Cycle> res = (List<Cycle>) query.list();
            for (Cycle cl : res) {
                cycleList.add(cl);
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
    
}
