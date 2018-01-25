package app.its.itfabiorossi.its_androidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private Button mLoginButton;
    private EditText mLoginBox;
    private EditText mPasswordBox;
    private TextView mError;
    private String mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mError=(TextView) findViewById(R.id.error);
        mLoginButton= (Button) findViewById(R.id.button_login);
        mLoginBox= (EditText) findViewById(R.id.login);
        mPasswordBox= (EditText) findViewById(R.id.password);


        //login button azioni
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPasswordBox.getText().toString().equals("1234")) {
                    mError.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(LoginActivity.this, ListActivity.class);
                    startActivity(intent);

                }
                else {

                    mError.setVisibility(View.VISIBLE);
                }

            }
        });



    }
}
