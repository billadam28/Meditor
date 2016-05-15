/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorInterfaces.LoadListService;
import MeditorPersistence.Institution;
import MeditorPersistence.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author adamopoulo
 */
public class InstitutionList implements LoadListService {
    private List<Institution> institutionList;
    private final String institutionListQuery;
    private final String institutionListPerCityQuery;
    
    public InstitutionList() {
        institutionList = new ArrayList<>();
        institutionListQuery = "select i from Institution i";
        institutionListPerCityQuery = "select i from Institution i where i.city.id = :city_id"; 
    }
    
    @Override
    public void populateDefaultList() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(institutionListQuery);
            List<Institution> res = (List<Institution>) query.list();
            for (Institution inst : res) {
                institutionList.add(inst);
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
    
    public void populateListPerCity(Integer cityId) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(institutionListPerCityQuery)
                    .setParameter("city_id", cityId);
            List<Institution> res = (List<Institution>) query.list();
            for (Institution inst : res) {
                institutionList.add(inst);
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
    
    public List<Institution> getInstitutionList () {
        return this.institutionList;
    }
    
}
