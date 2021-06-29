package com.emrebisgun.mydictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DictionaryDetailsActivity extends AppCompatActivity {

    TextView dictionaryDetailsText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_details);

        dictionaryDetailsText=findViewById(R.id.dictionaryDetailsText); //dictionaryDetailsText yani textView'in görünümü belirttim.

        Intent intent=getIntent(); //intent oluşturup bu intent ile karşılama işlemi yapacağım.
        String detail=intent.getStringExtra("detail"); // details değişkenine önceki aktiviteden gönderilen değeri atadım.
        dictionaryDetailsText.setText(detail); //atadığım değeri textView da görüntüledim.
    }
    public void homeClick(View view){
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    public void dictionaryClick(View view){
        Intent intent=new Intent(getApplicationContext(),DictionaryActivity.class);
        startActivity(intent);
    }
}