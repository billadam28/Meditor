package MeditorPersistence;

import java.util.Set;


public class Group  implements java.io.Serializable {


     private Integer id;
     private String name;
<<<<<<< HEAD
     private Integer leaderId;
     private String description;
=======
     private Group parentGroup;
     private Visitor groupLeader;
     //private Set<Visitor> members;
>>>>>>> refs/remotes/origin/master

    public Group() {
    }

<<<<<<< HEAD
    public Group(Integer parentGroupId, String name, Integer leaderId, String description) {
       this.parentGroupId = parentGroupId;
       this.name = name;
       this.leaderId = leaderId;
       this.description = description;
=======
    public Group(Group parentGroup, String name, Visitor groupLeader) {
       this.parentGroup = parentGroup;
       this.name = name;
       this.groupLeader = groupLeader;
>>>>>>> refs/remotes/origin/master
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
    
    public Visitor getGroupLeader() {
        return this.groupLeader;
    }
    public void setGroupLeader(Visitor leader) {
        this.groupLeader = leader;
    }
    
    /*public Set getMembers() {
        return this.members;
    }
    
<<<<<<< HEAD
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }



=======
    public void setMembers(Set members) {
        this.members = members;
    } */
>>>>>>> refs/remotes/origin/master

}


