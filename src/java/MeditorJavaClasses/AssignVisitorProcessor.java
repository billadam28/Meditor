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
        getDoctorListQuery = "select d, s.specialtyName, c.cityName, ga.geoName, i.institutionName,"
                + " (select u.surname from User u, Visitor v, Doctor d where d.assignedVstId=v.id and v.userId=u.id ) as vstName"
                + " from Doctor d, Specialty s, City c, GeographicalArea ga, Institution i"
                + " where d.specialtyId=s.id and d.cityId=c.id and d.geoAreaId=ga.id and d.institutionId=i.id";
        assignVisitorQuery = "insert into Doctor set AssignedVstId = :vst_id where ";
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
            //Query query = session.createQuery(assignVisitorQuery);
            for (int i=0; i<docList.length; i++) {
                Doctor doc = new Doctor();
                doc.setId(Integer.parseInt(docList[i]));
                doc.setAssignedVstId(visitorId);
                //mergedOne = session.merge(one);
                //session.saveOrUpdate(mergedOne);
                session.save(doc);
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
