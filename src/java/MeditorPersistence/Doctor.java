package MeditorPersistence;


public class Doctor  implements java.io.Serializable {


     private Integer id;
     private String name;
     private String address;
     private String phone;
     private String position;
     private Visitor assignedVisitor;
     private Visitor createdFrom;
     private Specialty specialty;
     private Institution institution;

    public Doctor() {
    }
   
    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Visitor getAssignedVisitor() {
        return this.assignedVisitor;
    }
    public void setAssignedVisitor(Visitor assignedVisitor) {
        this.assignedVisitor = assignedVisitor;
    }
    
    public Visitor getCreatedFrom() {
        return this.createdFrom;
    }
    public void setCreatedFrom(Visitor createdFrom) {
        this.createdFrom = createdFrom;
    }
    
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public Specialty getSpecialty() {
        return this.specialty;
    }
    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
    
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public Institution getInstitution() {
        return this.institution;
    }
    public void setInstitution(Institution institution) {
        this.institution = institution;
    }
    
    public String getPosition() {
        return this.position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    
}


