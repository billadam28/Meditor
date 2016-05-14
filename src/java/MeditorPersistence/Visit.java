package MeditorPersistence;


import java.util.Date;
import java.util.Set;

public class Visit  implements java.io.Serializable {


     private Integer id;
     private Integer visitOffset;
     private String status;
     private String comments;
     private Boolean extraVisit;
     private Date date;
     private Cycle cycle;
     private Doctor doctor;
     private Set<Visitor> visitors;
     
    public Visit() {
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Doctor getDoctor() {
        return this.doctor;
    }
    
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    

    public Integer getVisitOffset() {
        return this.visitOffset;
    }
    
    public void setVisitOffset(Integer visitOffset) {
        this.visitOffset = visitOffset;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getComments() {
        return this.comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    public Cycle getCycle() {
        return this.cycle;
    }
    
    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }
    
    public Set getVisitors() {
        return this.visitors;
    }
    
    public void setVisitors(Set visitors) {
        this.visitors = visitors;
    }
    
    public Boolean getExtraVisit() {
        return this.extraVisit;
    }
    
    public void setExtraVisit(Boolean extraVisit) {
        this.extraVisit = extraVisit;
    }

}


