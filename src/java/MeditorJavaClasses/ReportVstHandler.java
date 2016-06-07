/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorPersistence.Doctor;
import MeditorPersistence.NewHibernateUtil;
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
 * Java class which responsible of producing all the information needed for the statistics.
 * @author glalas
 */
public class ReportVstHandler {
    private List<Visitor> results;
    private List<Visit> visitresult;
    private List<Visitor> vstor;

    /**
     * Constructor
     */
    public ReportVstHandler(){
        
    }
    /**
     * This method is responsible for producing the percentage of
     * coverage of Visits a Visitor has in a current Cycle. 
     * @param visitor
     * @param cycId Integer
     * @return String. The percentage covered by this Visitor in this Cycle
     */
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
                    if(visit.getStatus().equals("completed")){
                    count++; 
                    }
                }  
          }
        if (visits !=0){
        total = count / (visits);}
        else total=0;
        NumberFormat defaultFormat = NumberFormat.getPercentInstance();
        defaultFormat.setMinimumFractionDigits(1);
        defaultFormat.format(total);
        return defaultFormat.format(total);
    }
        /**
         * This method produces the percentage of coverage for the the Group that 
         * the Visitor belongs to.
         * @param visitor
         * @param cycid Integer
         * @return String. The percentage of coverage of the group
         */
        public String groupVisitorsStats (Visitor visitor, Integer cycid){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        int groupid = visitor.getGroup().getId();
        float count=0;
        float vsttotal=0;
        float visits=0;
        float div=0;
        float grptotal=0;
        float grpadd=0;
        Transaction tx = null;
        String hqlquery = "Select v from Visitor v where v.group.id= :groupid " ;
            try{
            tx = session.beginTransaction();
            Query query= session.createQuery(hqlquery);
            query.setParameter("groupid", groupid);
            List<Visitor> res = (List<Visitor>) query.list();
            for (Visitor vst : res) {
               for (Iterator iterator = vst.getVisits().iterator(); iterator.hasNext();){
                Visit visit = (Visit) iterator.next();
                if(visit.getCycle().getId().equals(cycid)){
                    if(visit.getStatus().equals("pending") || (visit.getStatus().equals("completed")) ||(visit.getStatus().equals("unsuccessful"))){
                        visits++;
                    }
                    if(visit.getStatus().equals("completed")){
                    count++; 
                    }
                }
               }
               vsttotal = count/visits;
               grpadd=vsttotal++;
               div++;
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
        grptotal = grpadd / div;
        NumberFormat defaultFormat = NumberFormat.getPercentInstance();
        defaultFormat.setMinimumFractionDigits(1);
        defaultFormat.format(grptotal);
        return defaultFormat.format(grptotal);
    }
    
    /**
     * This method finds all the Visits a Visitor is assigned to by providing the
     * Visitor ID
     * @param vstId Integer. The Visitor ID
     */
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
   /**
    * This method returns the total number of Visits a Visitor has (Pending, Completed & Unsuccessful)
    * @param visitor
    * @return Integer. The total number of visits
    */
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
   
   /**
    * This method returns the total number of Doctors a Visitor is assigned to
    * @param visitor
    * @return Integer. The total number of doctors
    */
   public Integer totalDoctors(Visitor visitor){
   Session session = NewHibernateUtil.getSessionFactory().openSession();
   int doctors = 0;
   List dct = session.createQuery("From Doctor").list();
        for (Iterator iterator = 
                       dct.iterator(); iterator.hasNext();){
        Doctor doctor = (Doctor) iterator.next();
        if(Integer.valueOf(doctor.getAssignedVisitor().getId()).equals(visitor.getId())){
                doctors++;
            }

      }
    return doctors;
    }
   
    /**
     * This method returns the total number of Visits a Visitor has in a specific Cycle
     * @param visitor
     * @param cycId Integer. The ID of the Cycle
     * @return Integer.
     */
    public Integer totalVisitsPerCycle(Visitor visitor, Integer cycId ){
        int visits=0;
        for (Iterator iterator = visitor.getVisits().iterator(); iterator.hasNext();){
                Visit visit = (Visit) iterator.next();
                if(visit.getCycle().getId().equals(cycId)){
                    if(visit.getStatus().equals("pending") || (visit.getStatus().equals("completed")) ||(visit.getStatus().equals("unsuccessful"))){
                        visits++;
                    }
        
            }
        }
        return visits;
    }   
    
    /**
     * This method returns the pending visits a visitor has in a cycle
     * @param visitor
     * @param vstId Integer . Visitor ID
     * @param cycId Integer. Cycle ID
     * @return Integer. The total of pending visits in the cycle
     */
    public Integer pendingVisits(Visitor visitor, Integer vstId, Integer cycId){
        int visits=0;
        for (Iterator iterator = visitor.getVisits().iterator(); iterator.hasNext();){
                Visit visit = (Visit) iterator.next();
                if(visit.getCycle().getId().equals(cycId)){
                    if(visit.getStatus().equals("pending")){
                        visits++;
                    }
                }
            }
        return visits; 
    }
    
    /**
      * This method returns the completed visits a visitor has in a cycle
      * @param visitor
      * @param vstId Integer . Visitor ID
      * @param cycId Integer. Cycle ID
      * @return Integer. The total of completed visits in the cycle
      */
    public Integer completedVisits(Visitor visitor, Integer vstId, Integer cycId){
    int visits=0;
    for (Iterator iterator = visitor.getVisits().iterator(); iterator.hasNext();){
            Visit visit = (Visit) iterator.next();
            if(visit.getCycle().getId().equals(cycId)){
                if(visit.getStatus().equals("completed")){
                    visits++;
                }
            }
        }
    return visits; 
    }
    
   /**
     * This method returns the unsuccessful visits a visitor has in a cycle
     * @param visitor
     * @param vstId Integer . Visitor ID
     * @param cycId Integer. Cycle ID
     * @return Integer. The total of unsuccessful visits in the cycle
     */ 
    public Integer unsuccessfulVisits(Visitor visitor, Integer vstId, Integer cycId){
    int visits=0;
    for (Iterator iterator = visitor.getVisits().iterator(); iterator.hasNext();){
            Visit visit = (Visit) iterator.next();
            if(visit.getCycle().getId().equals(cycId)){
                if(visit.getStatus().equals("unsuccessful")){
                    visits++;
                }
            }
        }
    return visits; 
    }

    public List<Visit> displayVisits(){
        return this.visitresult;
    }
    
    public List<Visitor> displayVisitorResult(){
        return this.vstor;
    }
    
}
