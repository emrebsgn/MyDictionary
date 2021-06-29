package com.emrebisgun.mydictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EducationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
    }
    public void clickDictionary(View view){
        Intent intent=new Intent(EducationActivity.this,DictionaryActivity.class);
        startActivity(intent);
    }
    public void clickTranslate(View view){
        Intent intent=new Intent(EducationActivity.this,TranslateActivity.class);
        startActivity(intent);
    }
    public void clickMain(View view){
        Intent intent=new Intent(EducationActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void clickStories(View view){
        Intent intent=new Intent(EducationActivity.this,StoriesActivity.class);
        startActivity(intent);
    }
    public void clickWordsMeaning(View view){
        Intent intent=new Intent(getApplicationContext(),WordsActivity.class);
        startActivity(intent);
    }
    public void clickSentences(View view){
        Intent intent=new Intent(EducationActivity.this,SentenceActivity.class);
        startActivity(intent);
    }
    public void clickDialog(View view){
        Intent intent=new Intent(EducationActivity.this,DialogueActivity.class);
        startActivity(intent);
    }

}