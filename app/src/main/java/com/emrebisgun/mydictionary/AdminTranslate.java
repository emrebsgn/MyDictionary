package com.emrebisgun.mydictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

public class AdminTranslate extends AppCompatActivity {

    EditText editTextTurk;
    EditText editTextEng;
    TextView resultText;
    Button btnWordAdd;
    Button btnTranslate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_translate);

        editTextTurk=findViewById(R.id.editTextTurk);
        editTextEng=findViewById(R.id.editTextEng);
        btnWordAdd=findViewById(R.id.btnWordAdd);
        btnTranslate=findViewById(R.id.btnTranslate);
        resultText=findViewById(R.id.resultText);
    }

    //Ekle butonuna basıldığında Words adlı kelimeler tabloma sırasıyla girilen kelimeleri ekleyecek.
    public void clickWordAdd(View view){
        ParseObject object=new ParseObject("Words"); //Parse veri tabanında tabloya veri eklemek için parseObject kullanılır.
        object.put("turkish",editTextTurk.getText().toString()); //oluşturduğum object ismindeki nesnem ile turkish kolonuna editTextTurkte yazılan kelimeyi ekledim..
        object.put("english",editTextEng.getText().toString());
        object.saveInBackground(new SaveCallback() { //Ve bu işlemleri arka planda kaydettım.
            @Override
            public void done(ParseException e) {
                if(e!=null){
                    //Kaydetme işlemi sırasında hata varsa hatayı göster.
                    Toast.makeText(AdminTranslate.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    //Hata yoksa Toast ile kelime kaydedildi bilgisi ver ve text kutularının içini boşalt.
                    Toast.makeText(AdminTranslate.this, "Kelime Kaydedildi", Toast.LENGTH_SHORT).show();
                    editTextTurk.setText("");
                    editTextEng.setText("");
                }
            }
        });
    }

    public void clickTranslate(View view){
        ParseQuery<ParseObject> query=ParseQuery.getQuery("Words");
        if(editTextEng.getText().toString().matches("")){
            query.whereEqualTo("turkish",editTextTurk.getText().toString());
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if(e!=null){
                        Toast.makeText(AdminTranslate.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        if(objects.size()>0){
                            for(ParseObject object:objects){
                                String objectEnglish=object.getString("english");
                                resultText.setText(objectEnglish);
                            }
                        }
                    }
                }
            });
        }

        if(editTextTurk.getText().toString().matches("")){
            query.whereEqualTo("english",editTextEng.getText().toString());
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if(e!=null){
                        Toast.makeText(AdminTranslate.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        if(objects.size()>0){
                            for(ParseObject object:objects){
                                String objectTurkish=object.getString("turkish");
                                resultText.setText(objectTurkish);
                            }
                        }
                    }
                }
            });
        }

    }
}