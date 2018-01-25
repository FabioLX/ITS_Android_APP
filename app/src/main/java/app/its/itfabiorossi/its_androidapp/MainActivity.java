package app.its.itfabiorossi.its_androidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Models.Utente;

public class MainActivity extends AppCompatActivity {

    //region variable
    private TextView mTextView;
    private Button mButton;
    private EditText mEdit01;
    public  static final String EXTRA="key";
    public  static final String UTENTE="utente";
    private Button mLoginButton;
    private EditText mLoginBox;
    private EditText mPasswordBox;



    //endregion


    //region Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdit01=(EditText) findViewById(R.id.et01);
        mButton=(Button)findViewById(R.id.button_action);
        mLoginButton= (Button) findViewById(R.id.button_login);
        mLoginBox= (EditText) findViewById(R.id.login);
        mPasswordBox= (EditText) findViewById(R.id.password);

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
