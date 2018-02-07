package app.its.itfabiorossi.its_androidapp.models;

import app.its.itfabiorossi.its_androidapp.models.domain.City;
import app.its.itfabiorossi.its_androidapp.models.domain.List;

/**
 * Created by lucagiorgetti on 03/10/17.
 */

public class ForecastModel {

    //region Variables

    private City city;

    private List data;

    //endregion

    //region Constructors

    public ForecastModel(City city, List data) {
        this.city = city;
        this.data = data;
    }

    //endregion

    //region Getters & Setters

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    //endregion
}
