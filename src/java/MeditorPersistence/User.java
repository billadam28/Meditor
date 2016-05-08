package MeditorPersistence;


public class User  implements java.io.Serializable {


     private Integer id;
     private String firstname;
     private String surname;
     private String email;
     private String username;
     private String passwd;
     private UserType userType;

    public User() {
    }

    public User(String firstname, String surname, String email, String username, String passwd, UserType userType) {
       this.firstname = firstname;
       this.surname = surname;
       this.email = email;
       this.username = username;
       this.passwd = passwd;
       this.userType = userType;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFirstname() {
        return this.firstname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getSurname() {
        return this.surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPasswd() {
        return this.passwd;
    }
    
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    public UserType getUserType() {
        return this.userType;
    }
    
    public void setUserType(UserType userType) {
        this.userType = userType;
    }




}


