/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorPersistence.Doctor;
import MeditorPersistence.Group;
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
public class AssignVisitorProcessor {
    private List<Doctor> doctorList;
    private List<Visitor> visitorList;
    private final String getVisitorListQuery;
    private final String getDoctorListQuery;
    
    public AssignVisitorProcessor() {
        doctorList = new ArrayList<>();
        visitorList= new ArrayList<>();
        getVisitorListQuery = "select v from Visitor v";
        getDoctorListQuery = "select d from Doctor d";  
    }

    public void populateDefaultLists() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(getVisitorListQuery);
            List<Visitor> res = (List<Visitor>) query.list();
            for (Visitor vst : res) {
                // we get lazy initialization exception, so we need to 
                // initialize the child objects in order to access them in 
                // the jsp page after we close the session.
                Hibernate.initialize(vst.getGroup());
                Hibernate.initialize(vst.getSuperior());
                visitorList.add(vst);
            }
            
            query = session.createQuery(getDoctorListQuery);
            List<Doctor> res2 = (List<Doctor>) query.list();
            for (Doctor dct : res2) {
                Hibernate.initialize(dct.getAssignedVisitor());
                Hibernate.initialize(dct.getSpecialty());
                Hibernate.initialize(dct.getInstitution());
                Hibernate.initialize(dct.getInstitution().getCity());
                Hibernate.initialize(dct.getInstitution().getCity().getGeoArea());
                doctorList.add(dct);
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
    
    /**
     *
     * @param docList
     * @param visitorId
     */
    public void assignVisitor(String[] docList, int visitorId ) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Integer docId;
            Visitor vst = (Visitor) session.get(Visitor.class, visitorId);
            for (String retrieveddocId : docList) {
                docId = Integer.parseInt(retrieveddocId);
                Doctor dct = (Doctor)session.get(Doctor.class, docId);
                dct.setAssignedVisitor(vst);
                session.update(dct); 
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
    
    public List<Doctor> getDoctorList () {
        return this.doctorList;
    }
    
    public void testmethod() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("select v from Visitor v");
            List<Visitor> rs = (List<Visitor>)query.list();
            for (Visitor vst : rs) {
                System.out.println("---------");
                System.out.println(vst.getId().toString());
                System.out.println(vst.getFirstname());
                if (vst.getSuperior() != null) {
                    System.out.println(vst.getSuperior().getFirstname());
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
}
