package com.emrebisgun.mydictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DenemeActicityyyyy extends AppCompatActivity {

    EditText txtDeneme;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deneme_acticityyyyy);

        txtDeneme=findViewById(R.id.txtDeneme);

        sharedPreferences=this.getSharedPreferences("com.emrebisgun.mydictionary", Context.MODE_PRIVATE);


    }

    public void clickBtnSaveAndGo(View view){

        if(!txtDeneme.getText().toString().matches("")){
            String name=txtDeneme.getText().toString();
            sharedPreferences.edit().putString("sendName",name).apply();
            Intent intent=new Intent(getApplicationContext(),ProfilActivity.class);
            startActivity(intent);
        }




    }

    public void clickGo(View view){
        Intent intent=new Intent(getApplicationContext(),ProfilActivity.class);
        startActivity(intent);
    }
}