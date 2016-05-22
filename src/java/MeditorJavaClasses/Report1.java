/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorInterfaces.ReportGeneratorService;
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
public class Report1 implements ReportGeneratorService {
    private String reportQuery = "select count(*), ga.geoName, v.status from Visit v, Doctor d, Institution i, City c, " +
                                 "GeographicalArea ga join v.visitors vv " +
                                 "where vv.id = :vst_id " +
                                 "and v.doctor.id = d.id " +
                                 "and d.institution.id = i.id " +
                                 "and i.city.id = c.id " +
                                 "and c.geoArea.id = ga.id " +
                                 "group by ga.geoName, v.status";
    private Integer vstId;
    private Visitor visitor;
    private List<String[]> reportList;
    
    public Report1() {
        reportList = new ArrayList<>();  
    }
    
    @Override
    public void generateReport() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            visitor = (Visitor)session.get(Visitor.class, vstId);
            Query query = session.createQuery(reportQuery)
                    .setParameter("vst_id", vstId);
            List<Object[]> res = (List<Object[]>) query.list();
            for (Object[] obj : res) {
                String[] result = new String[3];
                result[0] = obj[0].toString();
                result[1] = obj[1].toString();
                result[2] = obj[2].toString();
                reportList.add(result);
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
    
    public Visitor getVisitor() {
        return this.visitor;
    }
    
    public void setVstId(Integer id) {
        this.vstId = id;
    }
    
    public List<String[]> getReportList() {
        return this.reportList;
    }
    
}
