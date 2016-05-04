/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorPersistence.Doctor;
import MeditorPersistence.NewHibernateUtil;
import MeditorPersistence.Visitor;
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
public class AssignVisitorProcessor {
    private List<Doctor> doctorList;
    private List<Visitor> visitorList;
    private final String getVisitorListQuery;
    private final String getDoctorListQuery;
    private final String assignVisitorQuery;
    
    public AssignVisitorProcessor() {
        doctorList = new ArrayList<>();
        visitorList= new ArrayList<>();
        getVisitorListQuery = "select v, u.firstname, u.surname, g.name, (select u.surname from User as u, Visitor as v where v.superiorId = v.id and v.userId=u.id) as superiorName"
                + " from Visitor v , User u, Group g, GroupMember gm where v.userId = u.id and v.id = gm.id.memberId and gm.id.groupId = g.id";
        /*getDoctorListQuery = "select d, s.specialtyName, c.cityName, ga.geoName, i.institutionName,"
                + " (select u.surname from User u, Visitor v, Doctor d where d.assignedVstId=v.id and v.userId=u.id ) as vstName"
                + " from Doctor d, Specialty s, City c, GeographicalArea ga, Institution i"
                + " where d.specialtyId=s.id and d.cityId=c.id and d.geoAreaId=ga.id and d.institutionId=i.id"; */
        assignVisitorQuery = "update Doctor d set d.assignedVstId = :vst_id where d.id = :doc_id";
       getDoctorListQuery = "select d, s.specialtyName, c.cityName, ga.geoName, i.institutionName, u.surname"
                    + " from Doctor d"
                    + " left outer join Visitor v on d.assignedVstId = v.id"
                    + " left outer join User u on v.userId = u.id"
                    + " inner join City c on d.cityId=c.id"
                    + " inner join Specialty s on d.specialtyId=s.id"
                    + " inner join GeographicalArea ga on d.geoAreaId=ga.id"
                    + " inner join Institution i on d.institutionId=i.id"; 
       
    }

    public void populateDefaultLists() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(getVisitorListQuery);
            List<Object> rs = (List<Object>)query.list();
            for (Object obj : rs) {
                Object[] o = (Object[]) obj;
                Visitor vst = (Visitor) o[0];
                vst.setFirstname((String) o[1]);
                vst.setSurname((String) o[2]);
                vst.setGroupName((String) o[3]);
                vst.setSuperiorName((String) o[4]);
                visitorList.add(vst);
            }
            
            query = session.createQuery(getDoctorListQuery);
            rs = (List<Object>)query.list();
            for (Object obj : rs) {
                Object[] o = (Object[]) obj;
                Doctor doctor = (Doctor) o[0];
                doctor.setSpecialtyName((String) o[1]);
                doctor.setCityName((String) o[2]);
                doctor.setGeoAreaName((String) o[3]);
                doctor.setInstitutionName((String) o[4]);
                doctor.setAssignedVstName((String) o[5]);
                doctorList.add(doctor);
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
            Query query = session.createQuery(assignVisitorQuery);
            int updatedEntities;
            for (String docList1 : docList) {
                query.setInteger("doc_id", Integer.parseInt(docList1));
                query.setInteger("vst_id", visitorId);
                updatedEntities = query.executeUpdate();
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
}
