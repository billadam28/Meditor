/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorPersistence.City;
import MeditorPersistence.Doctor;
import MeditorPersistence.GeographicalArea;
import MeditorPersistence.Institution;
import MeditorPersistence.NewHibernateUtil;
import MeditorPersistence.Specialty;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * An assisting java class which is responsible for making all the lists needed for 
 * feature 4 and also checks if the Doctor the user tries to submit already exists
 * @author glalas
 */
public class AddDoctorLists {
    private String getSpecialtyListQuery;
    private List<Specialty> specialtylist;
    private String getGeographicalAreaListQuery;
    private List<GeographicalArea> geoAreaList;
    private String getCityQuery;
    private List<City> cityList;
    private String getInstitutionQuery;
    private List<Institution> instituteList;
    
    /**
     * The Constructor of the class
     */
    public AddDoctorLists(){
 
    }
    
    /**
     * This method makes the queries for the corresponding lists that are needed
     * in feature 4.  
     */
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
    
    /**
     * The method for returning the list for Specialty
     * @return a list of Specialty objects
     */
    public List<Specialty> getSpecialtyList () {
        return this.specialtylist;
    }
    
    /**
     * The method for returning the list for Geographical Area
     * @return a list of Geographical Area Objects
     */
    public List<GeographicalArea> getGeoAreaList () {
        return this.geoAreaList;
    }
    
    /**
     * The method for returning the list for City
     * @return a list of City objects
     */
    public List<City> getCityList () {
        return this.cityList;
    }
    
    /**
     * The method for returning the list of Institutes
     * @return a list of Institution objects
     */
    public List<Institution> getInstituteList(){
        return this.instituteList;
    }
    
    /**
     * This method is responsible for checking if an entry already exists
     * in the Doctor table. Entries with the same name, address and phone combination
     * are considered double.
     * @param name String
     * @param address String
     * @param phone String
     * @return Boolean
     */
    public boolean checkForDoubles(String name, String address, String phone){
            Session session = NewHibernateUtil.getSessionFactory().openSession();

            Query query= session.createQuery("from Doctor d where d.name = :name and d.address = :address and d.phone =:phone ");
            query.setParameter("name", name);
            query.setParameter("address", address);
            query.setParameter("phone", phone);
            query.setMaxResults(1);
            Doctor dct = (Doctor) query.uniqueResult();
            if (dct == null){
                return false;
            }
            else return true;


    }
}
