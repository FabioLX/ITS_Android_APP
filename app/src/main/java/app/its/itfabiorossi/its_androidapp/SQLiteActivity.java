package app.its.itfabiorossi.its_androidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.its.itfabiorossi.its_androidapp.Database.TrainingCourseDbManager;

public class SQLiteActivity extends AppCompatActivity {

    private Button mSaveButton;
    private Button mReadButton;
    private EditText mStartEdit;
    private EditText mEndEdit;
    private EditText mDescrEdit;
    private TrainingCourseDbManager dbManager;


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

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //TODO controllo stringa vuota

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

    }
}
