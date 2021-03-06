/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorPersistence.Doctor;
import java.util.List;
import java.util.ArrayList;
import MeditorPersistence.NewHibernateUtil;
import MeditorPersistence.Visit;
import MeditorPersistence.Visitor;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author thodo
 */
public class VisitServices {
    private List<Doctor> doctorsList;
    private final String getDoctorsQuery;
    private List<Visit> visitsList;
    private final String getVisitsQuery;
    private Visit getVisit;
    private List<Visitor> traineesList;
    private final String getTraineesQuery;
    //private final String getVisitQuery;
    
    /**
     * This method initializes the lists and declares the queries.
     */
    
    public VisitServices() {
        traineesList= new ArrayList<>();
        doctorsList = new ArrayList<>(); 
        getDoctorsQuery = "select d from Doctor d where assignedVisitor!=null";
        
        visitsList = new ArrayList<>();
        getVisitsQuery = "select v from Visit v where status = 'pending'";
        getTraineesQuery = "select v from Visitor v where visitor_level = 'trainee'";
    }
    
    /**
     * This method retrieves all trainees and stores them in a list.
     */
    
    public void showTraineesList() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            List<Visitor> noOfTrainees  = session.createQuery(getTraineesQuery).list();
            for (Visitor trainee : noOfTrainees) {
                traineesList.add(trainee);    
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
     * This method updates information about a selected visit and adds and
     * accompanied trainee, if available.
     * @param date
     * @param status
     * @param extra
     * @param comments
     * @param visitId
     * @param traineeId 
     */
    
    public void updateVisit(Date date, String status, boolean extra, String comments, int visitId, int traineeId) {
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;

            try {
                tx = session.beginTransaction();
                    Visit visit = (Visit) session.get(Visit.class, visitId);
                    visit.setDate(date);
                    visit.setStatus(status);
                    visit.setExtraVisit(extra);
                    visit.setComments(comments);

                    if (traineeId != 0) {
                        Visitor trainee = (Visitor) session.get(Visitor.class, traineeId);
                        visit.getVisitors().add(trainee);
                        
                    }
                    session.update(visit);
                    
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
     * This method retrieves all the information about the visit that is going
     * to be edited.
     * @param visitId 
     */
    
    public void editVisitForm (int visitId) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("select v from Visit v where id = '"+visitId+"'");
                Visit visit = (Visit) query.uniqueResult();
                Hibernate.initialize(visit.getDoctor().getId());
                Hibernate.initialize(visit.getVisitors());
                Hibernate.initialize(visit.getDate());
                Hibernate.initialize(visit.getCycle().getCycle());
                Hibernate.initialize(visit.getDoctor().getAssignedVisitor().getId());
                if((visit.getId()) == visitId){
                    getVisit = visit;
                }
                
                //System.out.println(group.getName() + group.getId());
            
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
     * This method returns visitor's Id.
     * @param userId
     * @return Integer
     */
    
    public int getVisitorId (int userId) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select id from Visitor where user_id='"+userId+"'");
        String visitorId = query.uniqueResult().toString();
        Integer vstId = Integer.parseInt(visitorId);
        //System.out.println(vstId);
        return vstId;
    }
    
    /**
     * This method retrieves all doctors who have been assigned to the particular
     * visitor and stores them in a list.
     * @param userId 
     */
    
    public void showDoctorsList(int userId) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select username from Visitor where user_id='"+userId+"'");
        String visitor = query.uniqueResult().toString();
        //Integer vstId = Integer.parseInt(vid);
        //System.out.println(visitor);
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            List<Doctor> doctors  = session.createQuery(getDoctorsQuery).list();
            for (Doctor doctor : doctors) {
                Hibernate.initialize(doctor.getAssignedVisitor());
                Hibernate.initialize(doctor.getSpecialty());
                Hibernate.initialize(doctor.getInstitution());
                Hibernate.initialize(doctor.getInstitution().getCity());
                Hibernate.initialize(doctor.getInstitution().getCity().getGeoArea());
                if(doctor.getAssignedVisitor().getUsername().equals(visitor)){
                    doctorsList.add(doctor);
                }
                
                //System.out.println(group.getName() + group.getId());
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
     * This method retrieves the pending visits of the selected doctor and stores
     * them in a list.
     * @param docId 
     */
    
    public void showVisitsList (int docId) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            List<Visit> visits  = session.createQuery(getVisitsQuery).list();
            for (Visit visit : visits) {
                Hibernate.initialize(visit.getDoctor().getId());
                //Hibernate.initialize(visit.getDoctor().getAssignedVisitor().getId());
                Hibernate.initialize(visit.getVisitors());
                Hibernate.initialize(visit.getDate());
                Hibernate.initialize(visit.getCycle().getCycle());
                
                    if((visit.getDoctor().getId())==docId){
                        visitsList.add(visit);
                    } else {
                        visitsList.isEmpty();
                    }
            
                //System.out.println(group.getName() + group.getId());
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
     * This method returns a list of all doctors who have been assigned to the 
     * particular visitor.
     * @return List
     */
    
    public List<Doctor> doctorsList () {
        return this.doctorsList;
    }
    
    /**
     * This method returns a list of the pending visits of the selected doctor.
     * @return List
     */
    
    public List<Visit> visitsList () {
        return this.visitsList;
    }
    
    /**
     * This method returns a list of all trainees.
     * @return List 
     */
    
    public List<Visitor> traineesList () {
        return this.traineesList;
    }
    
    /**
     * This method returns the visit that is going to be edited.
     * @return Visit
     */
    
    public Visit getVisit () {
        return this.getVisit;
    }
}
