package app.its.itfabiorossi.its_androidapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Models.LocationModel;
import app.its.itfabiorossi.its_androidapp.R;

/**
 * Created by FABIO.ROSSI on 10/01/2018.
 */

public class LocalAdapter extends ArrayAdapter<LocationModel> {

    private Context context;
    private ArrayList <LocationModel> listModel;

    public LocalAdapter (Context context, ArrayList<LocationModel> localModel){
        super(context, R.layout.row, localModel);

        this.context= context;
        this.listModel=localModel;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView= inflater.inflate(R.layout.row, parent, false);
        TextView lblName= rowView.findViewById(R.id.rowM);
//        TextView lblName= rowView.findViewById(R.id.titolo);
//        TextView lblNazione= rowView.findViewById(R.id.nazione);
        LocationModel localModel=this.listModel.get(position);
        lblName.setText(localModel.getName());
//        lblNazione.setText(localModel.getNation());
        return  rowView;


    }
}
