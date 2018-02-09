package app.its.itfabiorossi.its_androidapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import app.its.itfabiorossi.its_androidapp.Database.TrainingCourseDbManager;
import app.its.itfabiorossi.its_androidapp.R;
import app.its.itfabiorossi.its_androidapp.models.TrainingCourseModel;

/**
 * Created by FABIO.ROSSI on 09/02/2018.
 */

public class TrainingCourseArrayAdapter extends ArrayAdapter<TrainingCourseModel> {

    //creazione listener
    public interface TrainingListener{
        void  eliminaRecord(int id);

    }

    private Context context;
    private ArrayList<TrainingCourseModel> values;
    private TrainingListener listener;

    public TrainingCourseArrayAdapter(Context context, ArrayList<TrainingCourseModel> values) {
        super(context, R.layout.row_training_course, values);
        this.context = context;
        this.values = values;
    }

    public void setlistener(TrainingListener l){
        listener=l;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_training_course, parent, false);

        //Recupero le view definita nel layout della riga
        TextView txtDescr = (TextView) rowView.findViewById(R.id.description_label);
        TextView txtStart = (TextView) rowView.findViewById(R.id.start_label);
        TextView txtEnd = (TextView) rowView.findViewById(R.id.end_label);
        ImageButton deleteButton=(ImageButton) rowView.findViewById(R.id.delete);

        //Recupero l'elemento i_esimo da visualizzare
        final TrainingCourseModel model = values.get(position);

        txtDescr.setText(model.getDescription());
        txtStart.setText(model.getStart());
        txtEnd.setText(model.getEnd());

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*
                //not BEST PRACTICE cancellazione da fare fuori adapter! VA FATTO SULL?ACTIVITY
                // adapter notifica l'activity che e' stata chiamata la delete, l'activity cancella, activity notify il change!

                int idrecord= model.getId();
                TrainingCourseDbManager tcm = new TrainingCourseDbManager(getContext());
                tcm.deleteRecord(idrecord);
                */

              //creo listener per notificare l'activity

              listener.eliminaRecord(model.getId());

            }
        });

        return rowView;
    }
}