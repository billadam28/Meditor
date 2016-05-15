/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorPersistence.Cycle;
import MeditorPersistence.Doctor;
import MeditorPersistence.NewHibernateUtil;
import MeditorPersistence.Visit;
import MeditorPersistence.Visitor;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author adamopoulo
 */
public class CreateVisitsProcessor {
    private DoctorList doctorListObj;
    private SpecialtyList specialtyListObj;
    private CityList cityListObj;
    private GeoAreaList geoAreaListObj;
    private InstitutionList institutionListObj;
    
    public CreateVisitsProcessor() {
    doctorListObj = new DoctorList();
    specialtyListObj = new SpecialtyList();
    cityListObj = new CityList();
    geoAreaListObj = new GeoAreaList();
    institutionListObj = new InstitutionList();
    }
    
    public void loadLists (Integer cycleId) {
        doctorListObj.populateListToBeVisited(cycleId);
        specialtyListObj.populateDefaultList();
        cityListObj.populateDefaultList();
        geoAreaListObj.populateDefaultList();
        institutionListObj.populateDefaultList();
    }
    
        public void createVisits(String[] docList, Integer numberOfVisits , Integer cycleId) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Integer docId;
            Cycle cl = (Cycle) session.get(Cycle.class, cycleId);
            for (String retrieveddocId : docList) {
                docId = Integer.parseInt(retrieveddocId);
                Doctor dct = (Doctor)session.get(Doctor.class, docId);
                Visitor vst = (Visitor)session.get(Visitor.class, dct.getAssignedVisitor().getId());
                Set<Visitor> vstSet = new HashSet<Visitor>();
                vstSet.add(vst);
                for (int i=0; i<numberOfVisits; i++) {
                    Visit visit = new Visit();
                    visit.setVisitOffset(i+1);
                    visit.setStatus("pending");
                    visit.setExtraVisit(false);
                    visit.setCycle(cl);
                    visit.setDoctor(dct);
                    visit.setVisitors(vstSet);
                    session.save(visit);
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
