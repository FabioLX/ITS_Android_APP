package Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import Models.CountryList;
import app.its.itfabiorossi.its_androidapp.R;

/**
 * Created by FABIO.ROSSI on 20/12/2017.
 */

public class ManazaFragment extends ListFragment {


    private OnFragmentEventListener mListener;
    private String[] mCountries;
    private CountryList mListCountries=new CountryList();

    //costruttore
    public ManazaFragment(){
        this.mCountries=new String[mListCountries.getCountries().size()]; // riserviamo in memoria lo spazio della size
        mListCountries.getCountries().toArray(mCountries);// carico la lista


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //TODO implementare la view!
        //arrayAdapter = in memoria vengono caricate solo quelle visibili
        ArrayAdapter<String> arrayAdapter =new ArrayAdapter<String>((Context) mListener, android.R.layout.simple_list_item_1); //l'adapter resta in vita aggangiato alla vita dell'activity
        //lo stampo su un layout di sistema
        //collego il listfragment alla lista dei countries
        setListAdapter(arrayAdapter);


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manaza, container, false);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //riferimento ns activity
        this.mListener=(OnFragmentEventListener) context;// il contesto e' quello dell'activity che lo ospita!!!  //SALVO IL CONTESSTO NEL LISTENER
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    //creazione interfaccia
    public interface OnFragmentEventListener{

        void OnSelectCountry(String country);
    }

}