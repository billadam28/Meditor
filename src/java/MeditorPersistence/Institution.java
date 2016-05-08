package MeditorPersistence;
// Generated Apr 29, 2016 4:36:30 PM by Hibernate Tools 4.3.1


public class Institution  implements java.io.Serializable {


     private Integer id;
     private String institutionName;
     private City city;

    public Institution() {
    }
   
    public Integer getId() {
        return this.id;
    } 
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getInstitutionName() {
        return this.institutionName;
    }
    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }
    
    public City getCity() {
        return this.city;
    }
    public void setCity(City city) {
        this.city = city;
    }

}


