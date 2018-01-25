package Models;

import java.util.ArrayList;

/**
 * Created by FABIO.ROSSI on 20/12/2017.
 */

public class CountryList {


    private ArrayList<String> mCountries;

    public CountryList(){

        this.mCountries=new ArrayList<String>();
        mCountries.add("Arizona");
        mCountries.add("California");
        mCountries.add("Lanzarote");
    }

    public ArrayList<String> getCountries() {
        return mCountries;
    }


}
