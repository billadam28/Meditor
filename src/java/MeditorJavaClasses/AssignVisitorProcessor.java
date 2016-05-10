/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorPersistence.Doctor;
import MeditorPersistence.NewHibernateUtil;
import MeditorPersistence.Visitor;
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
    private DoctorList doctorListObj;
    private VisitorList visitorListObj;
    private SpecialtyList specialtyListObj;
    private CityList cityListObj;
    private GeoAreaList geoAreaListObj;
    private InstitutionList institutionListObj;
    private GroupList groupListObj;
   
   
    
    public AssignVisitorProcessor() {
        doctorListObj = new DoctorList();
        visitorListObj = new VisitorList();
        specialtyListObj = new SpecialtyList();
        cityListObj = new CityList();
        geoAreaListObj = new GeoAreaList();
        institutionListObj = new InstitutionList(); 
        groupListObj = new GroupList();
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
    
    public void loadLists () {
        doctorListObj.populateDefaultList();
        visitorListObj.populateDefaultList();
        specialtyListObj.populateDefaultList();
        cityListObj.populateDefaultList();
        geoAreaListObj.populateDefaultList();
        institutionListObj.populateDefaultList();
        groupListObj.populateDefaultList();
    }
    
    public VisitorList getVisitorListObj () {
        return this.visitorListObj;
    }
    
    public DoctorList getDoctorListObj () {
        return this.doctorListObj;
    }
    
    public GeoAreaList getGeoAreaListObj () {
        return this.geoAreaListObj;
    }
    
    public CityList getCityListObj () {
        return this.cityListObj;
    }
    
    public SpecialtyList getSpecialtyListObj () {
        return this.specialtyListObj;
    }
    
    public InstitutionList getInstitutionListObj () {
        return this.institutionListObj;
    }
    
    public GroupList getGroupListObj () {
        return this.groupListObj;
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
