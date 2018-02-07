package app.its.itfabiorossi.its_androidapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import Models.Utente;

public class MainActivity extends AppCompatActivity {

    //region variable
    private TextView mTextView;
    private Button mButton;
    private EditText mEdit01;
    public  static final String EXTRA="key";
    public  static final String UTENTE="utente";
    public  static final String SHARED_KEY="shared";
    public  static final String STRING_KEY="stringa";
    private Button mLoginButton;
    private EditText mLoginBox;
    private EditText mPasswordBox;
    private EditText mSharedPrefs;
    private Button mSaveShared;
    private Button mReadShared;
    private Button mReadInternal;
    private Button mSaveInternal;
    private Button mReadSql;
    private Button mSaveSql;



    //endregion


    //region Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String fileName = "MyFile";
        final String contenuto = "prova1238888";


        //FileInputStream fis = getApplicationContext().openFileInput("hello.txt");
        //InputStreamReader isr = new InputStreamReader(fis);

        mEdit01=(EditText) findViewById(R.id.et01);
        mButton=(Button)findViewById(R.id.button_action);
        mLoginButton= (Button) findViewById(R.id.button_login);
        mLoginBox= (EditText) findViewById(R.id.login);
        mPasswordBox= (EditText) findViewById(R.id.password);

        //region SHARED PREFS
//        Context ctx= MainActivity.this;
        final SharedPreferences pref = getApplicationContext().getSharedPreferences(SHARED_KEY, getApplicationContext().MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();

        mSharedPrefs= (EditText) findViewById(R.id.et_shared);
        mSaveShared=(Button) findViewById(R.id.button_save_shared);
        mReadShared=(Button)findViewById(R.id.button_read_shared);
        mReadInternal=(Button) findViewById(R.id.button_read_internal);
        mSaveInternal=(Button) findViewById(R.id.button_save_internal);

        mSaveShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putString(STRING_KEY,mSharedPrefs.getText().toString());
                editor.commit();
            }
        });
        mReadShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextView.setText(pref.getString(STRING_KEY, "nussun dato presente"));//default value
            }
        });

        //save INTERNAL

        mSaveInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FileOutputStream outputStream=null;
                try {
                    outputStream = openFileOutput(fileName, Context.MODE_APPEND);
                    outputStream.write(("\n"+contenuto).getBytes());
                    outputStream.close();
                    Toast.makeText(getApplicationContext(), "miii, hai salvato",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "miii,NON hai salvato",
                            Toast.LENGTH_LONG).show();
                }


            }
        });

        mReadInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //endregion
        mReadSql=(Button) findViewById(R.id.button_read_sql);
        mSaveSql=(Button) findViewById(R.id.button_save_sql);

        //region SQL




        //endregion

        //login button azioni
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);


            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onClickAction();
            }
        });


        mTextView = findViewById(R.id.id_prova);
        mTextView.setText("coooo");
        mTextView.setText(R.string.pippo);
        mTextView.setText(R.string.hello);
        Log.d("LX", "create");

    }

    @Override
    protected void onStart() {
        Log.d("LX", "start");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d("LX", "stop");
        super.onStop();
    }

    @Override
    protected void onResume() {
        Log.d("LX", "resume");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.d("LX", "destroy");
        super.onDestroy();
    }

    //endregion

    //region onclick intent
    private void onClickAction(){

        Utente utente= new Utente("01","utente1", "utente1", 99);

        Intent intent = new Intent(MainActivity.this, ProvaActivity.class);
        intent.putExtra(EXTRA, mEdit01.getText().toString());
        intent.putExtra(UTENTE, utente);
        startActivity(intent);
        startActivityForResult(intent,1);

    }
    //endregion


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

if(requestCode==1) {
    if (resultCode == RESULT_OK) {

        Toast.makeText(getApplicationContext(), "result OK",
                Toast.LENGTH_LONG).show();
    }
}

    }
}
