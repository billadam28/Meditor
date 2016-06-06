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
    private final String getVisitorsNoLeaderQuery;
    
    
    /**
     * This method initializes the lists and declares the queries.
     */
    
    public GroupServices() {
        visitorsList = new ArrayList<>();
        groupsList= new ArrayList<>();
        visitorsNoLeaderList = new ArrayList<>();
                
        getVisitorsQuery = "select v from Visitor v where group = null";
        getGroupsQuery = "from Group g order by g.name asc";
        getVisitorsNoLeaderQuery = "from Visitor v where group != null";
    }

    /**
     * This method checks if the given name exists or not. 
     * @param nameOfGroup
     * @return True or False.
     */
    
    public boolean availableGroup (String nameOfGroup) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Group group = new Group();
        group.setName(nameOfGroup);
        Query query = session.createQuery("select name from Group where name='"+nameOfGroup+"'");
        return (query.uniqueResult() != null);
    }
    
    /**
     * This method retrieves the name of this parent group.
     * @param parentGroup
     * @return String
     */
    
    public String assignedGroup (int parentGroup) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select name from Group where id='"+parentGroup+"'");
        //System.out.println(parentGroup);
        String name = query.uniqueResult().toString();
        //System.out.println("name"+ name);
        return name;
        
    }
    
    /**
     * This method creates a new Group by inserting a new row in the 
     * corresponding table.
     * @param nameOfGroup
     * @param descOfGroup
     * @param parentGroup 
     */
    
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
    
    /**
     * This method assigns one or more visitors to a group by updating a row
     * in the corresponding table.
     * @param assignedVisitors
     * @param groupId 
     */
    
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
    
    /**
     * This method sets a visitor as a group leader by updating a row in the
     * corresponding table.
     * @param groupId
     * @param visitorId 
     */
    
    public void setVisitorAsLeader(int groupId,int visitorId) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Visitor visitor = (Visitor) session.get(Visitor.class, visitorId);
            Group group = (Group)session.get(Group.class, groupId);
            group.setGroupLeader(visitor);
            session.update(group);
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
     * This method retrieves the groups and stores them in a list, retrieves
     * the visitors that they are not members of a group and stores them in a list 
     * and retrieves the visitors that are members of a group but not leaders and
     * stores them in a list.
     */
    
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
                Hibernate.initialize(visitor.getGroup());
                visitorsList.add(visitor);
                //System.out.println(visitor.getId());
            }
            
            List<Visitor> visitorNoLeader = session.createQuery(getVisitorsNoLeaderQuery).list();
            for (Visitor vstr : visitorNoLeader) {
                Hibernate.initialize(vstr.getSuperior());
                Hibernate.initialize(vstr.getGroup().getGroupLeader());
                if(vstr.getGroup().getGroupLeader() == null) {
                    visitorsNoLeaderList.add(vstr);
                }
                
                
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
     * This method returns the list of visitors who are not members of a group.
     * @return List
     */
    
    public List<Visitor> visitorsList () {
        return this.visitorsList;
    }
    
    /**
     * This method returns the list of available groups.
     * @return List
     */
    
    public List<Group> groupsList () {
        return this.groupsList;
    }
    
    /**
     * This method returns the list of visitors who are in a group which has no
     * leader.
     * @return List 
     */
    
    public List<Visitor> visitorsNoLeaderList () {
        return this.visitorsNoLeaderList;
    }
}
