package MeditorPersistence;
// Generated Apr 29, 2016 4:36:30 PM by Hibernate Tools 4.3.1


public class City  implements java.io.Serializable {


     private Integer id;
     private String cityName;
     private GeographicalArea geoArea;

    public City() {
    }

    public City(String cityName, GeographicalArea geoArea) {
       this.cityName = cityName;
       this.geoArea = geoArea;
    }
   
    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getCityName() {
        return this.cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
    public GeographicalArea getGeoArea() {
        return this.geoArea;
    }
    public void setGeoArea(GeographicalArea geoArea) {
        this.geoArea = geoArea;
    }

}


