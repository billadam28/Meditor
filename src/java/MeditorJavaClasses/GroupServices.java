/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorPersistence.Group;
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
 * @author thodo
 */
public class GroupServices {
    String nameOfGroup;
    String descOfGroup;
    private List<Visitor> visitorList;
    private final String getVisitorList;
    
    public GroupServices() {
        visitorList = new ArrayList<>();
        getVisitorList = "select v.*, u.firstname, u.sirname from visitor v, user u where v.id not in "
                + "(select gm.member_id from group_member gm) and v.user_id = u.id";
        
    }


    public boolean availableGroup (String nameOfGroup) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Group group = new Group();
        group.setName(nameOfGroup);
        Query query = session.createQuery("select name from Group where name='"+nameOfGroup+"'");
        return (query.uniqueResult() != null);
    }
    
    public void createGroup(String nameOfGroup, String descOfGroup) {
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;

            try {
                tx = session.beginTransaction();
                    Group group = new Group();
                    //Group parentGroup = new Group();
                    group.setName(nameOfGroup);
                    group.setDescription(descOfGroup);
                    session.save(group);
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
    
    public void showLists() {
                 
    }
}
