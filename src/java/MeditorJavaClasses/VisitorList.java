/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorInterfaces.LoadListService;
import MeditorPersistence.NewHibernateUtil;
import MeditorPersistence.Visitor;
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
public class VisitorList implements LoadListService {
    private List<Visitor> visitorList;
    private final String visitorListQuery;
    private final String visitorListWithParameters;
    
    public VisitorList() {
        visitorList= new ArrayList<>();
        visitorListQuery = "select v from Visitor v where v.visitorLevel = 'senior'";
        visitorListWithParameters = "Select v from Visitor v where v.group.id = :group_id";
    }
    
    @Override
    public void populateDefaultList() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(visitorListQuery);
            List<Visitor> res = (List<Visitor>) query.list();
            for (Visitor vst : res) {
                // we get lazy initialization exception, so we need to 
                // initialize the child objects in order to access them in 
                // the jsp page after we close the session.
                Hibernate.initialize(vst.getGroup());
                Hibernate.initialize(vst.getSuperior());
                visitorList.add(vst);
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
    
    public void populateListWithParameters(Integer groupId) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(visitorListWithParameters)
                    .setParameter("group_id", groupId);
            List<Visitor> res = (List<Visitor>) query.list();
            for (Visitor vst : res) {
                // we get lazy initialization exception, so we need to 
                // initialize the child objects in order to access them in 
                // the jsp page after we close the session.
                Hibernate.initialize(vst.getGroup());
                Hibernate.initialize(vst.getSuperior());
                visitorList.add(vst);
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
    
    public List<Visitor> getVisitorList () {
        return this.visitorList;
    }
}
