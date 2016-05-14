/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorInterfaces.LoadListService;
import MeditorPersistence.NewHibernateUtil;
import MeditorPersistence.Specialty;
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
public class SpecialtyList implements LoadListService{
    private List<Specialty> specialtyList;
    private final String specialtyListQuery;
    
    public SpecialtyList() {
        specialtyList = new ArrayList<>();
        specialtyListQuery = "select s from Specialty s";
    }
    
    @Override
    public void populateDefaultList() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(specialtyListQuery);
            List<Specialty> res = (List<Specialty>) query.list();
            for (Specialty spc : res) {
                specialtyList.add(spc);
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
    
    public List<Specialty> getSpecialtyList () {
        return this.specialtyList;
    }
    
}
