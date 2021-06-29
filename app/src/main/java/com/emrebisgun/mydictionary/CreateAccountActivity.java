package com.emrebisgun.mydictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class CreateAccountActivity extends AppCompatActivity {

    EditText editTextNewUser;
    EditText editTextNewPsswrd;
    EditText editTextNewEmail;
    Button btnCreateAccount;
    Button btnClear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        editTextNewUser=findViewById(R.id.editTextNewUser);
        editTextNewPsswrd=findViewById(R.id.editTextNewPsswrd);
        editTextNewEmail=findViewById(R.id.editTextNewEmail);
        btnCreateAccount=findViewById(R.id.btnCreateAccount);
        btnClear=findViewById(R.id.btnClear);
    }


    //Veri tabanında ilgili tabloya verileri eklemeyi yapacak metod.
    public void register(View view){
        ParseUser user=new ParseUser();
        user.setUsername(editTextNewUser.getText().toString());
        user.setPassword(editTextNewPsswrd.getText().toString());
        user.setEmail(editTextNewEmail.getText().toString());
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e!=null){
                    Toast.makeText(CreateAccountActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CreateAccountActivity.this, "Hesap Oluşturuldu.", Toast.LENGTH_SHORT).show();
                    editTextNewUser.setText("");
                    editTextNewPsswrd.setText("");
                    editTextNewEmail.setText("");
                }
            }
        });
    }
}