package MeditorPersistence;
// Generated Apr 29, 2016 4:36:30 PM by Hibernate Tools 4.3.1

import java.util.Set;



public class Visitor extends User  implements java.io.Serializable {


     private String visitorLevel;
     private Visitor superior;
     private Set<Visit> visits;
     private Group group;

    public Visitor() {
    }

    public String getVisitorLevel() {
        return this.visitorLevel;
    }
    
    public void setVisitorLevel(String visitorLevel) {
        this.visitorLevel = visitorLevel;
    }
    
    public Visitor getSuperior() {
        return this.superior;
    }
    
    public void setSuperior(Visitor superior) {
        this.superior = superior;
    }
    
    public Set getVisits() {
        return this.visits;
    }
    
    public void setVisits(Set visits) {
        this.visits = visits;
    }
    
    public Group getGroup() {
        return this.group;
    }
    
    public void setGroup(Group group) {
        this.group = group;
    }

}


