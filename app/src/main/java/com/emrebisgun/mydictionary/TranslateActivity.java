package com.emrebisgun.mydictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class TranslateActivity extends AppCompatActivity {

    EditText editTextTurkish;
    EditText editTextEnglish;
    Button btnTranslate;
    Button btnTransClear;
    //TextView resultText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        editTextTurkish=findViewById(R.id.editTextTurk);
        editTextEnglish=findViewById(R.id.editTextEng);
        btnTranslate=findViewById(R.id.btnTranslate);
        btnTransClear=findViewById(R.id.btnTransClear);
        //resultText=findViewById(R.id.resultText);




    }
    public void clickTranslate(View view){
        ParseQuery<ParseObject> query=ParseQuery.getQuery("Words");
        if(editTextEnglish.getText().toString().matches("")){
            query.whereEqualTo("turkish",editTextTurkish.getText().toString());
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if(e!=null){
                        Toast.makeText(TranslateActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        if(objects.size()>0){
                            for(ParseObject object:objects){
                                String objectEnglish=object.getString("english");
                                //resultText.setText(objectEnglish);
                                editTextEnglish.setText(objectEnglish);
                                Toast.makeText(getApplicationContext(), "Çevrildi", Toast.LENGTH_SHORT).show();
                                editTextTurkish.setText("");
                            }
                        }
                    }
                }
            });
        }

        if(editTextTurkish.getText().toString().matches("")){
            query.whereEqualTo("english",editTextEnglish.getText().toString());
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if(e!=null){
                        Toast.makeText(TranslateActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        if(objects.size()>0){
                            for(ParseObject object:objects){
                                String objectTurkish=object.getString("turkish");
                                //resultText.setText(objectTurkish);
                                editTextTurkish.setText(objectTurkish);
                                Toast.makeText(getApplicationContext(), "Çevrildi", Toast.LENGTH_SHORT).show();
                                editTextEnglish.setText("");
                            }
                        }
                    }
                }
            });
        }
    }

    public void clickTransClear(View view){
        editTextTurkish.setText("");
        editTextEnglish.setText("");
    }
}