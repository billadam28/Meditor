/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorInterfaces.LoadListService;
import MeditorPersistence.Group;
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
public class GroupList implements LoadListService {
    private List<Group> groupList;
    private final String groupListQuery;
    
    public GroupList() {
        groupList = new ArrayList<>();
        groupListQuery = "select g from Group g";
    }
    
    @Override
    public void populateDefaultList() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(groupListQuery);
            List<Group> res = (List<Group>) query.list();
            for (Group gr : res) {
                groupList.add(gr);
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
    
    public List<Group> getGroupList () {
        return this.groupList;
    }
    
}
