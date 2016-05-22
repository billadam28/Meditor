/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorPersistence.Doctor;
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
 * @author glalas
 */
public class SearchEditDoc {
    private List<Doctor> results;

    
    
    
    public SearchEditDoc() {
        
        
        
    }
    
    public void SearchbyName(String name){

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        results = new ArrayList<>();
        
            
            try{
            tx = session.beginTransaction();
            Query query= session.createQuery("from Doctor d where d.name like :name");
            query.setParameter("name","%"+name+"%");
            List<Doctor> res = (List<Doctor>) query.list();
            for (Doctor dct : res) {
               Hibernate.initialize(dct.getId());
               Hibernate.initialize(dct.getName());
               Hibernate.initialize(dct.getAddress());
               Hibernate.initialize(dct.getCreatedFrom());
               Hibernate.initialize(dct.getAssignedVisitor());
               Hibernate.initialize(dct.getPhone());
               Hibernate.initialize(dct.getPosition());
               Hibernate.initialize(dct.getSpecialty());
               Hibernate.initialize(dct.getInstitution());
               Hibernate.initialize(dct.getInstitution().getCity());
               Hibernate.initialize(dct.getInstitution().getCity().getGeoArea());
                results.add(dct);
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

    public void searchBySpecialty(Integer specid){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        results = new ArrayList<>();
        
            
            try{
            tx = session.beginTransaction();
            Query query= session.createQuery("from Doctor d where d.specialty.id = :spec");
            query.setParameter("spec", specid);
            List<Doctor> res = (List<Doctor>) query.list();
            for (Doctor dct : res) {
               Hibernate.initialize(dct.getId());
               Hibernate.initialize(dct.getName());
               Hibernate.initialize(dct.getAddress());
               Hibernate.initialize(dct.getCreatedFrom());
               Hibernate.initialize(dct.getAssignedVisitor());
               Hibernate.initialize(dct.getPhone());
               Hibernate.initialize(dct.getPosition());
               Hibernate.initialize(dct.getSpecialty());
               Hibernate.initialize(dct.getInstitution());
               Hibernate.initialize(dct.getInstitution().getCity());
               Hibernate.initialize(dct.getInstitution().getCity().getGeoArea());
                results.add(dct);
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
  
  public void searchByAssignedVisitor(Integer vstId){
      Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        results = new ArrayList<>();
        
            
            try{
            tx = session.beginTransaction();
            Query query= session.createQuery("from Doctor d where d.createdFrom.id = :visitorID");
            query.setParameter("visitorID", vstId);
            List<Doctor> res = (List<Doctor>) query.list();
            for (Doctor dct : res) {
               Hibernate.initialize(dct.getId());
               Hibernate.initialize(dct.getName());
               Hibernate.initialize(dct.getAddress());
               Hibernate.initialize(dct.getCreatedFrom());
               Hibernate.initialize(dct.getAssignedVisitor());
               Hibernate.initialize(dct.getPhone());
               Hibernate.initialize(dct.getPosition());
               Hibernate.initialize(dct.getSpecialty());
               Hibernate.initialize(dct.getInstitution());
               Hibernate.initialize(dct.getInstitution().getCity());
               Hibernate.initialize(dct.getInstitution().getCity().getGeoArea());
                results.add(dct);
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
  
  public void searchByInstitution(Integer instID){
      Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        results = new ArrayList<>();
        
            
            try{
            tx = session.beginTransaction();
            Query query= session.createQuery("from Doctor d where d.institution.id = :institutionID");
            query.setParameter("institutionID", instID);
            List<Doctor> res = (List<Doctor>) query.list();
            for (Doctor dct : res) {
               Hibernate.initialize(dct.getId());
               Hibernate.initialize(dct.getName());
               Hibernate.initialize(dct.getAddress());
               Hibernate.initialize(dct.getCreatedFrom());
               Hibernate.initialize(dct.getAssignedVisitor());
               Hibernate.initialize(dct.getPhone());
               Hibernate.initialize(dct.getPosition());
               Hibernate.initialize(dct.getSpecialty());
               Hibernate.initialize(dct.getInstitution());
               Hibernate.initialize(dct.getInstitution().getCity());
               Hibernate.initialize(dct.getInstitution().getCity().getGeoArea());
                results.add(dct);
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
    
  public List<Doctor> getSearchResults(){
     return this.results;
 }
    
}
