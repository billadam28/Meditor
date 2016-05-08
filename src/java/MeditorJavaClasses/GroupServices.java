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
    private List<Visitor> visitorsNoLeaderList;
    private List<Group> groupsNoLeaderList;
    private final String getVisitorsQuery;
    private final String getGroupsQuery;
    private final String getGroupsNoLeaderQuery;
    private final String getVisitorsNoLeaderQuery;
    private final String assignVisitorGroupQuery;
    private final String setVisitorLeaderQuery;
    
    
    public GroupServices() {
        visitorsList = new ArrayList<>();
        groupsList= new ArrayList<>();
        visitorsNoLeaderList = new ArrayList<>();
        groupsNoLeaderList = new ArrayList<>();
                
        getVisitorsQuery = "select v, u from Visitor v, User u where u.user_type=2";
                /*"select v, u.firstname, u.surname from visitor v, user u where v.id not in "
                + "(select gm.member_id from group_member gm) and v.user_id = u.id"; */
        getGroupsQuery = "from Group g order by g.name asc";
        getGroupsNoLeaderQuery = "";
        getVisitorsNoLeaderQuery = "";
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
    
    public void createGroup(String nameOfGroup, String descOfGroup, String parentGroup) {
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;

            try {
                tx = session.beginTransaction();
                
                    Group group = new Group();
                    //Group parentGroup = new Group();
                    if (parentGroup.equals("0")==false) {
                        group.setParentGroupId(Integer.parseInt(parentGroup));
                        group.setName(nameOfGroup);
                        group.setDescription(descOfGroup);
                        session.save(group);
                    } else {
                        group.setName(nameOfGroup);
                        group.setDescription(descOfGroup);
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
            List<Group> groups  = session.createQuery(getGroupsQuery).list();
            for (Group group : groups) {
                //group.getName();
                groupsList.add(group);
                //System.out.println(group.getName() + group.getId());
            }
            
         
            /*List<Visitor> visitors = session.createQuery(getVisitorsQuery).list();
            for (Visitor visitor : visitors) {
                visitorsList.add(visitor);
                //System.out.println(visitor.getId());
            }
            
            query = session.createQuery(getVisitorsNoLeaderQuery);
            rs = (List<Object>)query.list();
            for (Object obj : rs) {
                Object[] o = (Object[]) obj;
                Visitor visitor = (Visitor) o[0];
                
                visitorsNoLeaderList.add(visitor);
            }
            
            query = session.createQuery(getGroupsQuery);
            rs = (List<Object>)query.list();
            for (Object obj : rs) {
                Object[] o = (Object[]) obj;
                Group group = (Group) o[0];
                group.setName((String) o[1]);
                groupsList.add(group);
            }
            
            query = session.createQuery(getGroupsNoLeaderQuery);
            rs = (List<Object>)query.list();
            for (Object obj : rs) {
                Object[] o = (Object[]) obj;
                Group group = (Group) o[0];
                group.setName((String) o[1]);
                groupsNoLeaderList.add(group);
            } */
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
    
    public List<Visitor> visitorsNoLeaderList () {
        return this.visitorsNoLeaderList;
    }
    
    public List<Group> groupsNoLeaderList () {
        return this.groupsNoLeaderList;
    }
}
