package MeditorPersistence;

import java.util.Set;


public class Group  implements java.io.Serializable {


     private Integer id;
     private String name;
     private String description;
     private Group parentGroup;
     private Visitor groupLeader;
     //private Set<Visitor> members;

    public Group() {
    }

    public Group(Group parentGroup, String name, String description, Visitor groupLeader) {
       this.parentGroup = parentGroup;
       this.name = name;
       this.description = description;
       this.groupLeader = groupLeader;
    }
   
    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Group getParentGroup() {
        return this.parentGroup;
    }
    public void setParentGroup(Group parentGroup) {
        this.parentGroup = parentGroup;
    }
    
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Visitor getGroupLeader() {
        return this.groupLeader;
    }
    public void setGroupLeader(Visitor leader) {
        this.groupLeader = leader;
    }
    
    /*public Set getMembers() {
        return this.members;
    }
    
    public void setMembers(Set members) {
        this.members = members;
    } */

}


