package MeditorPersistence;
// Generated Apr 29, 2016 4:36:30 PM by Hibernate Tools 4.3.1



/**
 * Specialty generated by hbm2java
 */
public class Specialty  implements java.io.Serializable {


     private Integer id;
     private String specialtyName;

    public Specialty() {
    }

    public Specialty(String specialtyName) {
       this.specialtyName = specialtyName;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getSpecialtyName() {
        return this.specialtyName;
    }
    
    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }




}

