package com.emrebisgun.mydictionary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SentenceActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    int sharedSentenceScore;
    TextView emptySentence; //Boşluk doldurmalı sorumu gösterecek olan textView.
    TextView questionNumber; //Soru numarasını gösterecek olan textView.
    TextView sentenceScore; //Skoru gosterecek olan textView.
    Button button1;
    Button button2;
    Button button3;
    int soruSirasi = 1, sayi, score = 0;

    String objectSentence; //Veri tabanından çekilen boşluk doldurmalı soruyu tutacak olan değişkenim.
    String objectRequiredWord; //Boşluk doldurmalı soru için veri tabanından çekilen doğru kelimeyi tutacak olan değişkenim.
    String objectRndWord1; //Veri tabanından çekilen sorunun yanlış cevabını tutacak olan değişkenim.
    String objectRndWord2; //Veri tabanından çekilen sorunun yanlış cevabını tutacak olan değişkenim.
    private String correctAnswer; //Burada doğru kelimeyi tekrar bir string değişkenine atadım çünkü butonlara tıklandığında bu değişkende tutulan değer kontrol edilecek.
    Random rnd = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence);

        emptySentence = findViewById(R.id.emptySentence);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        sharedPreferences=this.getSharedPreferences("com.emrebisgun.mydictionary", Context.MODE_PRIVATE);

        questionNumber = findViewById(R.id.questionNumber);
        sentenceScore = findViewById(R.id.sentenceScore);
        sentenceScore.setText("Puan: " + score);

        bringSentence(); //Cümleyi çağırdım.

    }

    //Veri tabanına bağlanıp ID ye göre boşluk doldurmalı soruyu ve çıkları çekecek olan metodum.
    public void bringSentence() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("SentenceWords"); //Parse sorgusu oluşturmak için bir obje tanımladım.
        if (soruSirasi > 0) { //Global olarak tanımladığım bu değişken'e başlangıçta 1 değeri verdim ve if bloguna girecek.
            query.whereEqualTo("sentenceID", soruSirasi); //veri tabanımdaki SentenceWords tablomdaki ID ile soruSirası değişkenimin tuttuğu sayı ile aynı olan satırı belirttim.
            query.findInBackground(new FindCallback<ParseObject>() { //belirttiğim kriterdeki satırı arka planda arayacak.
                @Override
                public void done(List<ParseObject> objects, ParseException e) {//Bulunan değerleri ParseObjectin List şeklinde tutacak.

                    if (e != null) { //Eğer arama sırasında hata olursa hatayu Toast mesaj ile belirtecek.
                        Toast.makeText(SentenceActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    } else { //Eğer hata olmaz ve belirtilen kriterde veriler varsa bu if bloguna girecek.
                        if (objects.size() > 0) {

                            for (ParseObject object : objects) { //List object'deki değerleri tek tek object değişkeni ile çekeceğiz.

                                objectRequiredWord = object.getString("requiredWord"); //Veri tabanındaki sutun ismini belirterek oradaki veriyi değişkenime atadım.
                                objectRndWord1 = object.getString("rndWord1");
                                objectRndWord2 = object.getString("rndWord2");
                                objectSentence = object.getString("sentence");

                                emptySentence.setText(objectSentence); //objectSentence de tuttuğum boşluk doldurma sorusunu emptySentence textView'ımda gösterdım.

                                //Şıkların rastgele gelmesi adına küçük bir işlem yaptım.

                                String[] veriler = new String[3]; //Sorularda 3 adet şık olacağı için dizimin boyutu 3 adet.
                                for (int i = 0; i < 3; i++) { //döngüm 3 kere tekrar edecek.
                                    sayi = rnd.nextInt(3); //sayi değişkenime 0 dan 3 e kadar (3 dahil değil) 0,1,2 değerlerinden biri rast gele gelecek.
                                    if (veriler[sayi] == null) { //rast gele atanan sayı değeri veriler adlı dizimde indise karşılık gelecek..Örneğin . ...Burada atanan değere karşılık gelene indis null mı onu kontrol ettim.veriler[1]
                                        veriler[sayi] = objectRequiredWord; //rastgele atanan değerin indisinde objectRequired yani boşluk doldurma sorusunda gerekli olan kelime bu indisi tutacak.
                                        correctAnswer = veriler[sayi]; //bu dizideki doğru kelimeyi başka bir değiikenime atıyorum çünkü şıklara tıklandığında lazım olacak.
                                        if (sayi == 0) { //rastegele atanan değer indisi 0.indisteyse 1. ve 2. indise diğer cevapları atayacak.
                                            veriler[sayi + 1] = objectRndWord1;
                                            veriler[sayi + 2] = objectRndWord2;
                                        }
                                        if (sayi == 1) { //rastegele atanan değer 1.indiste yer tutuyorsa 0. ve 2. indise diğer cevapları atayacak.
                                            veriler[sayi - 1] = objectRndWord1;
                                            veriler[sayi + 1] = objectRndWord2;
                                        }
                                        if (sayi == 2) { //rastgele atanan değer 2.indisi tutuyorsa 0. ve 1.indise diğer cevaplar atanacak.
                                            veriler[sayi - 1] = objectRndWord1;
                                            veriler[sayi - 2] = objectRndWord2;
                                        }
                                    }
                                }
                                //sorunun şıkları rastgele atandıktan sonra dizideki değerleri sırayla butonların textlerine yazdırdım.
                                button1.setText(veriler[0]);
                                button2.setText(veriler[1]);
                                button3.setText(veriler[2]);

                                questionNumber.setText("Soru : " + soruSirasi);
                            }
                        }
                    }
                }
            });
        }

    }





    public void clickSentenceBtn1(View view){
        if(button1.getText().equals(correctAnswer)){
            Toast.makeText(this, "Doğru Cevap +10", Toast.LENGTH_SHORT).show();
            score+=10;
            sentenceScore.setText("Puan: "+score);

        }else{
            Toast.makeText(this, "Yanlış Cevap -10", Toast.LENGTH_SHORT).show();
            score-=10;
            sentenceScore.setText("Puan: "+score);
        }
    }

    public void clickSentenceBtn2(View view){
        if(button2.getText().equals(correctAnswer)){
            Toast.makeText(this, "Doğru Cevap +10", Toast.LENGTH_SHORT).show();
            score+=10;
            sentenceScore.setText("Puan: "+score);
        }else{
            Toast.makeText(this, "Yanlış Cevap -10", Toast.LENGTH_SHORT).show();
            score-=10;
            sentenceScore.setText("Puan: "+score);
        }
    }
    public void clickSentenceBtn3(View view){
        if(button3.getText().equals(correctAnswer)){
            Toast.makeText(this, "Doğru Cevap +10", Toast.LENGTH_SHORT).show();
            score+=10;
            sentenceScore.setText("Puan: "+score);
        }else{
            Toast.makeText(this, "Yanlış Cevap -10", Toast.LENGTH_SHORT).show();
            score-=10;
            sentenceScore.setText("Puan: "+score);
        }
    }

    //Bir sonraki soruya geçmemizi sağlayan click butonum.
    public void clickSentenceRight(View view){
        if(soruSirasi<9) { //Soru sirasi 8 den küçükse arttırma işlemi devam edilebilecek.
            soruSirasi++;
            Toast.makeText(getApplicationContext(), "Sonraki ", Toast.LENGTH_SHORT).show();
            bringSentence(); //Güncel soruSirasi ile sorumuzu tekrar veri tabanından çekmiş olacağız.


            if (soruSirasi == 9) {
                sharedSentenceScore = score;
                sharedPreferences.edit().putInt("storedSentenceScore", sharedSentenceScore).apply();
                soruSirasi = 1;
                bringSentence();
               AlertDialog.Builder builder=new AlertDialog.Builder(getApplicationContext());
               builder.setTitle("Soru Bitti");
               builder.setMessage("Sorular bitti puanınız: "+score);
               builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       soruSirasi=1;
                       bringSentence();
                   }
               });

            }
        }

    }

    //Bir önceki soruya geçmemizi sağlayan click butonum.
    public void clickSentenceLeft(View view){

            if(soruSirasi>=1){ //Sorusirasi değikenimin tuttuğu sayı 0 dan büyükse eksiltme yani bir önceki soruya geçme işlemi yapılabilenecek.
                soruSirasi--;
                Toast.makeText(getApplicationContext(), "Önceki", Toast.LENGTH_SHORT).show();
                bringSentence();
            }
            if(soruSirasi==0){
                soruSirasi=8;
                bringSentence();
            }
    }

    public void clickMain(View view){
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    public void clickTranslate(View view){
        Intent intent=new Intent(getApplicationContext(),TranslateActivity.class);
        startActivity(intent);
    }
    public void clickEducation(View view){
        Intent intent=new Intent(getApplicationContext(),EducationActivity.class);
        startActivity(intent);
    }
    public void clickDictionary(View view){
        Intent intent=new Intent(getApplicationContext(),DictionaryActivity.class);
        startActivity(intent);
    }
    public void clickProfil(View view){
        Intent intent=new Intent(getApplicationContext(),ProfilActivity.class);
        startActivity(intent);
    }


}