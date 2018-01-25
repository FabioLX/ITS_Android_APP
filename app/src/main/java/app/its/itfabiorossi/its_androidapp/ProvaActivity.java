package app.its.itfabiorossi.its_androidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Fragments.ManazaFragment;
import Fragments.ProvaFragment;
import Models.Utente;

public class ProvaActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextProva;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
             setContentView(R.layout.activity_prova);
        //region fragment

        if(savedInstanceState== null){  // se nullo istanzio il fragment
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_prova, new ManazaFragment()).commit();


        }
        //endregion

        Intent i= getIntent();
        String value=i.getStringExtra("key");
        Utente utente=(Utente)i.getParcelableExtra(MainActivity.UTENTE);
        Log.d(MainActivity.EXTRA,value);

        mTextProva=(TextView) findViewById(R.id.prova_text_01);
        mTextProva.setText(utente.getLastname());

        mButton=findViewById(R.id.buttonprova);
//        mButton.setOnClickListener(this);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(view.getId()==R.id.buttonprova){
                    //mando indietro il risultato
//                    setResult(RESULT_OK);
//                    finish();

                    Intent returnIntent = new Intent();
                    setResult(MainActivity.RESULT_OK, returnIntent);
                    finish();
                }
            }
        });




    }








    //NON USATO ATTUALMENTE
    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.buttonprova){
            //mando indietro il risultato
            setResult(RESULT_OK);
            finish();
        }

//        switch (view.getId()){
//            case R.id.buttonprova:
//
//                break;
//        }

    }
}
