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
    String parentGroup;
    private List<Visitor> visitorsList;
    private List<Group> groupsList;
    private final String getVisitorsQuery;
    private final String getGroupsQuery;
    private final String assignVisitorGroupQuery;
    private final String setVisitorLeaderQuery;
    
    
    public GroupServices() {
        visitorsList = new ArrayList<>();
        groupsList= new ArrayList<>();
        getVisitorsQuery = "select v, u.firstname, u.surname from visitor v, user u where v.id not in "
                + "(select gm.member_id from group_member gm) and v.user_id = u.id";
        getGroupsQuery = "";
        assignVisitorGroupQuery = "insert into Group g set g.assignedVstId = :vst_id where g.id = :group_id\"";
        setVisitorLeaderQuery = "update Group g set g.leaderVstId = :vst_id where g.id = :group_id";
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
                    //parentGroup.getName(parentGroup);
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
    
    public void assignVisitorToGroup(String[] groupList, int visitorId ) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(assignVisitorGroupQuery);
            for (int i=0; i<groupList.length; i++) {
                Group group = new Group();
                group.setId(Integer.parseInt(groupList[i]));
                //group.setAssignedVstId(visitorId);
                //mergedOne = session.merge(one);
                //session.saveOrUpdate(mergedOne);
                session.save(group);
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
    
    public void setVisitorAsLeader(String[] groupList, int visitorId ) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(assignVisitorGroupQuery);
            for (int i=0; i<groupList.length; i++) {
                Group group = new Group();
                group.setId(Integer.parseInt(groupList[i]));
                group.setLeaderId(visitorId);
                //mergedOne = session.merge(one);
                //session.saveOrUpdate(mergedOne);
                session.saveOrUpdate(group);
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
    
    public void showVisitorGroupLists() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery(getVisitorsQuery);
            List<Object> rs = (List<Object>)query.list();
            for (Object obj : rs) {
                Object[] o = (Object[]) obj;
                Visitor visitor = (Visitor) o[0];
                
                visitorsList.add(visitor);
            }
            
            query = session.createQuery(getGroupsQuery);
            rs = (List<Object>)query.list();
            for (Object obj : rs) {
                Object[] o = (Object[]) obj;
                Group group = (Group) o[0];
                group.setName((String) o[1]);
                groupsList.add(group);
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
    
    public List<Visitor> visitorsList () {
        return this.visitorsList;
    }
    
    public List<Group> groupsList () {
        return this.groupsList;
    }
}
