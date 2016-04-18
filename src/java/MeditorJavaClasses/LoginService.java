/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MeditorJavaClasses;

import MeditorPersistence.NewHibernateUtil;
import MeditorPersistence.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author adamopoulo
 */
public class LoginService {
    private final String userEmail;
    private final String password;
    
    public LoginService (String userEmail, String password) 
    {
        this.userEmail = userEmail;
        this.password = password;
    }
    
    public boolean authenticateUser() {
        User user = getUserByEmailOrUsername(userEmail);         
        return (user!=null && user.getEmail().equals(userEmail) && user.getPasswd().equals(password));
    }
 
    public User getUserByEmailOrUsername(String userId) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        User user = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from User where email='"+userId+"'");
            user = (User)query.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }
    
}
