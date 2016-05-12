/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorPersistence.Doctor;
import MeditorPersistence.NewHibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author adamopoulo
 */
public class DeleteDoctorProcessor {
    private DoctorList doctorListObj;
    private SpecialtyList specialtyListObj;
    private CityList cityListObj;
    private GeoAreaList geoAreaListObj;
    private InstitutionList institutionListObj;
   
   
    
    public DeleteDoctorProcessor() {
        doctorListObj = new DoctorList();
        specialtyListObj = new SpecialtyList();
        cityListObj = new CityList();
        geoAreaListObj = new GeoAreaList();
        institutionListObj = new InstitutionList(); 
    }
    
    public void loadLists () {
        doctorListObj.populateDefaultList();
        specialtyListObj.populateDefaultList();
        cityListObj.populateDefaultList();
        geoAreaListObj.populateDefaultList();
        institutionListObj.populateDefaultList();
    }
    
    public void deleteDoctor(String[] docList) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Integer docId;
            for (String retrieveddocId : docList) {
                docId = Integer.parseInt(retrieveddocId);
                Doctor dct = (Doctor) session.get(Doctor.class, docId);
                session.delete(dct); 
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
    
    
}
