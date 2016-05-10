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
import org.hibernate.Hibernate;
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
    private List<Visitor> visitorsList;
    private List<Group> groupsList;
    private List<Visitor> visitorsNoLeaderList;
    private List<Group> groupsNoLeaderList;
    private final String getVisitorsQuery;
    private final String getGroupsQuery;
    private final String getGroupsNoLeaderQuery;
    private final String getVisitorsNoLeaderQuery;
    
    
    public GroupServices() {
        visitorsList = new ArrayList<>();
        groupsList= new ArrayList<>();
        visitorsNoLeaderList = new ArrayList<>();
        groupsNoLeaderList = new ArrayList<>();
                
        getVisitorsQuery = "select v from Visitor v where group = null";
                /*"select v, u.firstname, u.surname from visitor v, user u where v.id not in "
                + "(select gm.member_id from group_member gm) and v.user_id = u.id"; */
        getGroupsQuery = "from Group g order by g.name asc";
        getGroupsNoLeaderQuery = "";
        getVisitorsNoLeaderQuery = "";
    }


    public boolean availableGroup (String nameOfGroup) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Group group = new Group();
        group.setName(nameOfGroup);
        Query query = session.createQuery("select name from Group where name='"+nameOfGroup+"'");
        return (query.uniqueResult() != null);
    }
    
    public String assignedGroup (int parentGroup) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select name from Group where id='"+parentGroup+"'");
        //System.out.println(parentGroup);
        String name = query.uniqueResult().toString();
        //System.out.println("name"+ name);
        return name;
        
    }
    
    public void createGroup(String nameOfGroup, String descOfGroup, int parentGroup) {
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;

            try {
                tx = session.beginTransaction();
                    Group group = new Group();
                    Group pgroup = (Group) session.get(Group.class, parentGroup);
                    
                    if (parentGroup!=0) {
                        group.setName(nameOfGroup);
                        group.setDescription(descOfGroup);
                        group.setParentGroup(pgroup);
                        session.save(group);
                    } else {
                        group.setName(nameOfGroup);
                        group.setDescription(descOfGroup);
                        //System.out.println(descOfGroup);
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
    
    public void assignVisitorToGroup(String[] assignedVisitors, int groupId ) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Integer visitorId;
            Group group = (Group) session.get(Group.class, groupId);
            for (String visitor : assignedVisitors) {
                visitorId = Integer.parseInt(visitor);
                Visitor visitors = (Visitor)session.get(Visitor.class, visitorId);
                visitors.setGroup(group);
                session.update(visitors); 
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
            //Query query = session.createQuery(assignVisitorGroupQuery);
            for (int i=0; i<groupList.length; i++) {
                Group group = new Group();
                group.setId(Integer.parseInt(groupList[i]));
                //group.setLeaderId(visitorId);
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
            
         
            List<Visitor> visitors = session.createQuery(getVisitorsQuery).list();
            for (Visitor visitor : visitors) {
                Hibernate.initialize(visitor.getSuperior());
                visitorsList.add(visitor);
                //System.out.println(visitor.getId());
            }
            /*
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
