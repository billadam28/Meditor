/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorInterfaces.LoadListService;
import MeditorPersistence.GeographicalArea;
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
public class GeoAreaList implements LoadListService {
    private List<GeographicalArea> geoAreaList;
    private final String geoAreaListQuery;
    
    public GeoAreaList() {
        geoAreaList = new ArrayList<>();
        geoAreaListQuery = "select ga from GeographicalArea ga";
    }
    
    @Override
    public void populateDefaultList() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(geoAreaListQuery);
            List<GeographicalArea> res = (List<GeographicalArea>) query.list();
            for (GeographicalArea ga : res) {
                geoAreaList.add(ga);
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
    
    public List<GeographicalArea> getGeoAreaList () {
        return this.geoAreaList;
    }
    
}
