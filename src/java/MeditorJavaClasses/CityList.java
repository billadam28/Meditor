/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorInterfaces.LoadListService;
import MeditorPersistence.City;
import MeditorPersistence.NewHibernateUtil;
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
public class CityList implements LoadListService {
    private List<City> cityList;
    private final String cityListQuery;
    private final String cityListWithIdQuery;
    
    public CityList() {
        cityList = new ArrayList<>();
        cityListQuery = "select c from City c";
        cityListWithIdQuery = "Select c from City c where c.geoArea.id = :geoArea_id";
    }
    
    @Override
    public void populateDefaultList() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(cityListQuery);
            List<City> res = (List<City>) query.list();
            for (City ct : res) {
                cityList.add(ct);
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
    
    public void populateListWithId(Integer geoAreaId) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(cityListWithIdQuery)
                    .setParameter("geoArea_id", geoAreaId);
            List<City> res = (List<City>) query.list();
            for (City ct : res) {
                cityList.add(ct);
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
    
    public List<City> getCityList () {
        return this.cityList;
    }
    
}
