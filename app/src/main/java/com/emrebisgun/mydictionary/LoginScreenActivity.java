package com.emrebisgun.mydictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginScreenActivity extends AppCompatActivity {

    EditText editTextUser;
    EditText editTextPsswrd;
    Button btnLogin;
    Button btnNewAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        ParseUser parseUser=ParseUser.getCurrentUser();
        if(parseUser!=null){
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }

        editTextUser=findViewById(R.id.editTextUser);
        editTextPsswrd=findViewById(R.id.editTextPsswrd);
        btnLogin=findViewById(R.id.btnLogin);
        btnNewAccount=findViewById(R.id.btnNewAccount);
    }
    public void clickLogin(View view){
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        ParseUser.logInInBackground(editTextUser.getText().toString(), editTextPsswrd.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e!=null){
                    Toast.makeText(LoginScreenActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginScreenActivity.this, "Hoşgeldin: "+editTextUser.getText().toString(), Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        });

    }
    public void clickRedirectNewAccount(View view){
        Intent intent=new Intent(getApplicationContext(),CreateAccountActivity.class);
        startActivity(intent);
    }
}