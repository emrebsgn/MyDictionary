package com.emrebisgun.mydictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;

public class ProfilActivity extends AppCompatActivity {



    SharedPreferences sharedPreferences;

    TextView profilCurrentName;
    Button btnProfilWord;
    Button btnProfilSentence;
    int meetWordData;
    int meetSentenceData;
    String currentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);


        btnProfilWord=findViewById(R.id.btnProfilWord);
        btnProfilSentence=findViewById(R.id.btnProfilSentence);
        profilCurrentName=findViewById(R.id.profilCurrentName);

        currentName= ParseUser.getCurrentUser().getUsername().toString();
        profilCurrentName.setText(currentName);

        sharedPreferences=getSharedPreferences("com.emrebisgun.mydictionary",Context.MODE_PRIVATE);
        meetWordData=sharedPreferences.getInt("storedWordPoint",0);
        meetSentenceData=sharedPreferences.getInt("storedSentenceScore",0);
        if(meetSentenceData==0){

        }else{
            btnProfilSentence.setText("Cümle Testi "+meetSentenceData+"/80");
        }

        if(meetWordData==0){

        }else{
            btnProfilWord.setText("Kelime Testi "+meetWordData+"/15");
        }




    }

}