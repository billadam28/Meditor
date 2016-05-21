/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorPersistence.City;
import MeditorPersistence.GeographicalArea;
import MeditorPersistence.Institution;
import MeditorPersistence.NewHibernateUtil;
import MeditorPersistence.Specialty;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author glalas
 */
public class addDocQ {
    private String getSpecialtyListQuery;
    private List<Specialty> specialtylist;
    private String getGeographicalAreaListQuery;
    private List<GeographicalArea> geoAreaList;
    private String getCityQuery;
    private List<City> cityList;
    private String getInstitutionQuery;
    private List<Institution> instituteList;

            
    
    public addDocQ(){
        
           
    }
    
    public void makeLists(){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        getSpecialtyListQuery ="FROM Specialty";
        specialtylist = new ArrayList<>();
        getGeographicalAreaListQuery="FROM GeographicalArea";
        geoAreaList = new ArrayList<>();
        getCityQuery = "FROM City";
        cityList =new ArrayList<>();
        getInstitutionQuery = "FROM Institution";
        instituteList = new ArrayList<>();
        try {
            tx = session.beginTransaction();
            //Specialty LIst
            List spec = session.createQuery(getSpecialtyListQuery).list();
            for (Iterator iterator = 
                           spec.iterator(); iterator.hasNext();){
            Specialty specialty = (Specialty) iterator.next(); 
            specialty.getSpecialtyName();
            specialtylist.add(specialty);
            }
            //Geographical-Area List
            List geo = session.createQuery(getGeographicalAreaListQuery).list();
            for (Iterator iterator = 
                           geo.iterator(); iterator.hasNext();){
            GeographicalArea geoarea = (GeographicalArea) iterator.next(); 
            geoarea.getGeoName();
            geoAreaList.add(geoarea);
            }
            //City List
             List cityq = session.createQuery(getCityQuery).list();
            for (Iterator iterator = 
                           cityq.iterator(); iterator.hasNext();){
            City city = (City) iterator.next(); 
            city.getCityName();
            cityList.add(city);
            }
            //Institution List
            List inst = session.createQuery(getInstitutionQuery).list();
            for (Iterator iterator = 
                           inst.iterator(); iterator.hasNext();){
            Institution institute = (Institution) iterator.next(); 
            institute.getInstitutionName();
            instituteList.add(institute);
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
    
    public List<Specialty> getSpecialtyList () {
        return this.specialtylist;
    }
    
    public List<GeographicalArea> getGeoAreaList () {
        return this.geoAreaList;
    }
    
    public List<City> getCityList () {
        return this.cityList;
    }
    
    public List<Institution> getInstituteList(){
        return this.instituteList;
    }
    

}
