package MeditorPersistence;


public class Cycle  implements java.io.Serializable {


     private Integer id;
     private String cycle;

    public Cycle() {
    }

    public Cycle(String cycle) {
       this.cycle = cycle;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCycle() {
        return this.cycle;
    }
    
    public void setCycle(String cycle) {
        this.cycle = cycle;
    }




}


