package app.its.itfabiorossi.its_androidapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import app.its.itfabiorossi.its_androidapp.models.domain.List;

import java.util.ArrayList;


import app.its.itfabiorossi.its_androidapp.R;
import app.its.itfabiorossi.its_androidapp.models.ForecastModel;
import app.its.itfabiorossi.its_androidapp.models.domain.Forecasts;

/**
 * Created by FABIO.ROSSI on 31/01/2018.
 */

//CUSTOM RECYCLEVIEW adapter

//PASSO 1
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastsViewHolder> {

    //PASSO 8 lista dei model FORECAST (model LIST!!!)
    private java.util.List<List> forecasts= new ArrayList<>(); //NB: List e' il modello List nel domain!!!

    public ForecastAdapter(){}

    // PASSO 11 carichiamo il forecast
    public void setForecasts(java.util.List<List> forecasts){
        this.forecasts=forecasts;
    }

    //PASSO 4 override dei metodi
    //region override extends
    @Override
    public ForecastsViewHolder onCreateViewHolder(ViewGroup padre, int viewType) {


        //PASSO 7 inflate della view custom
        //inflate della view
        View item= LayoutInflater.from(padre.getContext()).inflate(R.layout.cell_forecast, padre, false);

       return new ForecastsViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ForecastsViewHolder holder, int position) {

        //PASSO 9
        List model= this.forecasts.get(position);
        holder.mText1.setText(model.getDtTxt());
        holder.mText2.setText(model.getDt());

    }

    @Override
    public int getItemCount() {

        //PASSO 10 contiamo le celle
        return this.forecasts.size();
    }
    //endregion

//PASSO 2
//mantiene referenza della view e reciclera' i dati del model
    public class ForecastsViewHolder extends RecyclerView.ViewHolder{


    //PASSO 5 disegno view
        private TextView mText1;
        private TextView mText2;


    //PASSO 3
        public ForecastsViewHolder (View view){
            super(view);


        //PASSO 6
            this.mText1= (TextView) view.findViewById(R.id.cell_1);
            this.mText2= (TextView) view.findViewById(R.id.cell_2);

        }

    }
}
