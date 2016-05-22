/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorPersistence.Doctor;
import MeditorPersistence.NewHibernateUtil;
import MeditorPersistence.Specialty;
import MeditorPersistence.Visit;
import MeditorPersistence.Visitor;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
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
public class ReportVstHandler {
    private List<Visitor> results;
    private List<Visit> visitresult;
    private List<Visitor> vstor;

    
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
    public String getStatics(Visitor visitor, Integer cycId){
        float count = 0;
        float total=0;
        float visits=0;

            for (Iterator iterator = visitor.getVisits().iterator(); iterator.hasNext();){
                Visit visit = (Visit) iterator.next();
                if(visit.getCycle().getId().equals(cycId)){
                    if(visit.getStatus().equals("pending") || (visit.getStatus().equals("completed")) ||(visit.getStatus().equals("unsuccessful"))){
                        visits++;
                    }
                    if(!visit.getStatus().equals("pending")){
                    count++; 
                    }
                }
                
          }
        total = count / (visits);
        NumberFormat defaultFormat = NumberFormat.getPercentInstance();
        defaultFormat.setMinimumFractionDigits(1);
        defaultFormat.format(total);
        return defaultFormat.format(total);
    }
     
    public void findByVstId(Integer vstId){
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        visitresult = new ArrayList<>();
        String hqlquery = "Select  v from Visit v join v.visitors vv where vv.id= :visitorId " ;
            try{
            tx = session.beginTransaction();
            
            Query query= session.createQuery(hqlquery);
            query.setParameter("visitorId", vstId);
            List<Visit> res = (List<Visit>) query.list();
            for (Visit vst : res) {
                Hibernate.initialize(vst.getDoctor());
                Hibernate.initialize(vst.getCycle());
                Hibernate.initialize(vst.getExtraVisit());
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
    
   public Integer totalVisits(Visitor visitor){
       int visits = 0;
            for (Iterator iterator = visitor.getVisits().iterator(); iterator.hasNext();){
                Visit visit = (Visit) iterator.next();
                if(visit.getStatus().equals("pending") || (visit.getStatus().equals("completed")) ||(visit.getStatus().equals("unsuccessful"))){
                    visits++;
                }
          }
        return visits;
    }
   
    
       public Integer totalDoctors(Visitor visitor){
       Session session = NewHibernateUtil.getSessionFactory().openSession();
       int doctors = 0;
       List dct = session.createQuery("From Doctor").list();
            for (Iterator iterator = 
                           dct.iterator(); iterator.hasNext();){
            Doctor doctor = (Doctor) iterator.next(); 
            if(doctor.getAssignedVisitor().getId().equals(visitor.getId())){
                    doctors++;
                }
          }
        return doctors;
    }
   
     public List<Visitor> getVisitorResults(){
     return this.results;
 }
    public List<Visit> displayVisits(){
        return this.visitresult;
    }
    
    public List<Visitor> displayVisitorResult(){
        return this.vstor;
    }
    
}
