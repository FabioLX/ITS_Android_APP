package app.its.itfabiorossi.its_androidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import app.its.itfabiorossi.its_androidapp.Database.TrainingCourseDbManager;
import app.its.itfabiorossi.its_androidapp.adapters.TrainingCourseArrayAdapter;
import app.its.itfabiorossi.its_androidapp.models.TrainingCourseModel;

public class SQLiteActivity extends AppCompatActivity implements TrainingCourseArrayAdapter.TrainingListener {

    private Button mSaveButton;
    private Button mReadButton;
    private EditText mStartEdit;
    private EditText mEndEdit;
    private EditText mDescrEdit;
    private TrainingCourseDbManager dbManager;
    private ListView mListViewCourses;
    private TrainingCourseArrayAdapter mAdapter;
    private ArrayList <TrainingCourseModel> mListCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        dbManager = new TrainingCourseDbManager(this); //creo istanza

        mSaveButton = (Button) findViewById(R.id.button_save_sql);
        mReadButton = (Button) findViewById(R.id.button_read_sql);
        mStartEdit = (EditText) findViewById(R.id.editstart);
        mEndEdit = (EditText) findViewById(R.id.editend);
        mDescrEdit = (EditText) findViewById(R.id.editdescr);
        mListViewCourses=(ListView) findViewById(R.id.list_sql);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //controllo stringa vuota
                String desc = mDescrEdit.getText().toString();
                String start = mStartEdit.getText().toString();
                String end = mEndEdit.getText().toString();
                if (TextUtils.isEmpty(desc) || TextUtils.isEmpty(start) || TextUtils.isEmpty(end)) {

                    Toast.makeText(getApplicationContext(), "WARNING: c'e' un campo vuoto!",
                            Toast.LENGTH_LONG).show();
                } else {

                    //inserimento a DB
                    dbManager.insertRecord(mDescrEdit.getText().toString(), mStartEdit.getText().toString(), mEndEdit.getText().toString());
                }
            }
        });

        mReadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
               mListCourses = dbManager.readRecord();
                //list adapter
               mAdapter= new TrainingCourseArrayAdapter(SQLiteActivity.this, mListCourses); //(getApplicationContext(), mListCourses); stessa cosa
                //stampa lista
                mListViewCourses.setAdapter(mAdapter);*/
                leggiDati();

            }

        });




    }

    //notifica ricevuta dall'adapter per la cancellazione dell'elemento id
    @Override
    public void eliminaRecord(int id) {

        dbManager.deleteRecord(id);
        leggiDati();
    }



    public void leggiDati(){

        //nascondi tastiera
        View view = this.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        //leggi da DB

        mListCourses = dbManager.readRecord();


        //list adapter
        mAdapter= new TrainingCourseArrayAdapter(SQLiteActivity.this, mListCourses); //(getApplicationContext(), mListCourses); stessa cosa

        //setta il listener
        mAdapter.setlistener(SQLiteActivity.this);
        //stampa lista
        mListViewCourses.setAdapter(mAdapter);

    }

}
