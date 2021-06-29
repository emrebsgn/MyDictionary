package com.emrebisgun.mydictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class StoriesActivity extends AppCompatActivity {

    TextView storyEnglish;
    TextView storyTurkish;
    TextView titleStoryName;
    ImageView btnRightStory;
    ImageView btnLeftStory;

    String objectEnglishStory; //Veri tabanımdaki ingilizce hikayeyi burada tanımladığım değişkende tutacağım.
    String objectTurkishStory; // "" "" ""
    String objectStoryName; //Veri tabanımdaki Stories tablomdaki hikayelerin başlığını tutacağım değişken.
    int sira=1; //Hikayeyi değiştirmek adına tıklanmalar olacak.Bu tıklanma veri tabanımdaki "Stories" adlı tablomun ID sine gör
               // veriler getirecek.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stories);


        storyEnglish=findViewById(R.id.storyEnglish);
        storyTurkish=findViewById(R.id.storyTurkish);
        titleStoryName=findViewById(R.id.titleStoryName);
        btnRightStory=findViewById(R.id.btnRightDialog);
        btnLeftStory=findViewById(R.id.btnLeftDialog);



    }

    //Story getir methodum. Çağırıldığında ID ye göre ilgili hikayenin İngilizce,Türkçe hikayesini ve başlığını getirecek.
    public void brindStory(){
        ParseQuery<ParseObject> query=ParseQuery.getQuery("Stories"); //Parse sorgusu oluşturmak için bir obje tanımladım.
        if(sira>0){ //eğer sıra numarası 0 dan buyukse veri tabanından verileri çekecek.
            query.whereEqualTo("storyID",sira); //sira adındaki değişkenimin numarası veri tabanımdaki storyID ile eş olan satırdaki verileri getirecek.
            query.findInBackground(new FindCallback<ParseObject>() { //Arka planda bulmamızı sağlayacak olan metodum.
                @Override
                public void done(List<ParseObject> objects, ParseException e) { //burada objects'ler donecek.
                    if(e!=null){      //eğer bu işlemde hata varsa Toast mesajda görüntülenecek.
                        Toast.makeText(StoriesActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        //eğer geriye değerler dönüyorsa yani dönen objects'lerin uzunluğu 0'dan büyükse
                        if(objects.size()>0){
                            for(ParseObject object:objects){ //objelerimi ParseObject türünde object adı verdiğim değişkende tut.
                                objectEnglishStory=object.getString("englishStory"); //Global olarak tanımladığım değişkenime object yardımıyla key'i englistStory olan hücremin içindeki veriyi getirecek.
                                objectTurkishStory=object.getString("turkishStory"); // "" "" "" "" "" ""
                                objectStoryName=object.getString("storyName");      // "" "" "" "" "" ""

                                storyEnglish.setText(objectEnglishStory); //Bir ust satırda Veri tabanından çekmiş olduğum objectEnglishStory de tuttuğum veriyi storyEnglish ismindeki textView'ımda görüntüleyecek.
                                storyTurkish.setText(objectTurkishStory); // "" "" "" "" "" ""
                                titleStoryName.setText(objectStoryName); // "" "" "" "" "" ""
                            }
                        }
                    }
                }
            });
        }

    }

    public void clickRightStory(View view){
        Toast.makeText(this, "Sonraki!", Toast.LENGTH_SHORT).show();
        try{
            if(sira<7){
                sira=sira+1;
            }
            brindStory();
        }catch (Exception e){
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void clickLeftStory(View view){
        Toast.makeText(this, "Önceki!", Toast.LENGTH_SHORT).show();
        try{
            if(sira>1){
                sira=sira-1;
            }

            brindStory();
        }catch (Exception e){
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void clickDictionary(View view){
        Intent intent=new Intent(StoriesActivity.this,DictionaryActivity.class);
        startActivity(intent);
    }
    public void clickTranslate(View view){
        Intent intent=new Intent(StoriesActivity.this,TranslateActivity.class);
        startActivity(intent);
    }
    public void clickMain(View view){
        Intent intent=new Intent(StoriesActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void clickEducation(View view){
        Intent intent=new Intent(StoriesActivity.this,EducationActivity.class);
        startActivity(intent);
    }

}