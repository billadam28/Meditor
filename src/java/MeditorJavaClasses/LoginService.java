
package MeditorJavaClasses;

import MeditorPersistence.NewHibernateUtil;
import MeditorPersistence.User;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;



public class LoginService {
    private final String userInputId;
    private final String password;
    
    public LoginService (String userInputId, String password) 
    {
        this.userInputId = userInputId;
        this.password = password;
    }
    
     public static String toSha(String pass){
             String hexedpass = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(pass.getBytes());
            BigInteger hash = new BigInteger(1, md.digest());
            hexedpass = hash.toString(16);                 
         } 
        catch (NoSuchAlgorithmException e) { 
            e.printStackTrace();
        }
      
        return hexedpass;
    }  
    
    public boolean authenticateUser(User user) {
        //User user = getUserByEmailOrUsername();         
        return (user!=null && (user.getEmail().equals(userInputId) || user.getUsername().equals(userInputId)) 
                && user.getPasswd().equals(password));
    }
 
    public User getUserByEmailOrUsername() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        User user = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from User where email='"+userInputId+"' or username='"+userInputId+"'");
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
