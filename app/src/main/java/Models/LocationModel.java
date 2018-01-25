package Models;

/**
 * Created by FABIO.ROSSI on 10/01/2018.
 */

public class LocationModel {

    private String Name;
    private String Id;
    private String Nation;

    //TODO aggiungere nazione
    public LocationModel(String id, String nome){
        this.Name= nome;
        this.Id=id;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNation() {
        return Nation;
    }

    public void setNation(String nation) {
        Nation = nation;
    }
}
