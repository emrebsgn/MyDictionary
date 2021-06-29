package com.emrebisgun.mydictionary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;
import java.util.Random;

public class WordsActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView englishWord; //en ustteki
    TextView titleEnglishWord; //en alttaki
    TextView preTitleEnglishWord; //önceki başlık
    TextView nextTitleEnglishWord; //sonraki başlık
    TextView txtTotal; //toplam doğruyu gosterecek.
    TextView txtQuestionNumber; //soru numarasını gosterecek.
    TextView txtTotalReset;

    Button button4;
    Button button5;
    Button button6;
    Button button7;
    String objectEnglishWord;
    String objectTitleEnglishWord;
    String objectTrueWord;
    String objectRndMeaning1;
    String objectRndMeaning2;
    String objectRndMeaning3;
    String objectPre;
    String objectNext;
    String trueMeaning; //doğru kelimeyi tutacak.
    int sharedWordPoint;

    Random rnd=new Random();
    private int count=1; //soru sırasını tutacak.
    private int sayi; //random bir değer uretilecek ve sayi değişkenine atanak.
    private int point=0; //yapılan doğru veya yanlışa göre puan değerini tutacak.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        englishWord=findViewById(R.id.englishWord);
        titleEnglishWord=findViewById(R.id.titleDialogue);
        preTitleEnglishWord=findViewById(R.id.preTitleEnglishWord);
        nextTitleEnglishWord=findViewById(R.id.nextTitleEnglishWord);
        txtTotal=findViewById(R.id.txtTotal);
        txtQuestionNumber=findViewById(R.id.txtQuestionNumber);
        txtTotalReset=findViewById(R.id.txtTotalReset);

        sharedPreferences=this.getSharedPreferences("com.emrebisgun.mydictionary", Context.MODE_PRIVATE);

        txtTotal.setText(point+"/15");
        txtQuestionNumber.setText("Soru:"+count);


        button4=findViewById(R.id.button4);
        button5=findViewById(R.id.button5);
        button6=findViewById(R.id.button6);
        button7=findViewById(R.id.button7);
        bringWords(); //kelimeleri getir.
    }

    public void clickTxtTotalReset(View view){
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Emin misiniz?");
        alert.setMessage("Puanınızı sıfırlamak ve en başa dönmek üzeresiniz. Bundan emin misiniz?");
        alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                count=1;
                point=0;
                txtTotal.setText(point+"/15");
                bringWords();
                Toast.makeText(getApplicationContext(), "Başa Dönüldü", Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();
    }

    //Kelimeleri veri tabanından çekeceğim metod.
    public void bringWords(){
        preBringWords();
        nextBringWords();
        ParseQuery<ParseObject> query=ParseQuery.getQuery("WordMeanings"); //Sorgumu yapacağım tabloyu belirttim.
        if(count>0){ //Global olarak tanımladığım count değişkenimin değeri 0'dan büyükse if bloğu içine girecek.
            query.whereEqualTo("wordMeaningID",count); //count değişkenimin değeri yukarıda belirttiğim tablonun ID'sine eşdeğer olan değer varmı onu kontrol edecek.
            query.findInBackground(new FindCallback<ParseObject>() { //bu belirttiklerimi arka planda arayacak.
                @Override //Arama işlemi sonrasında parseobjesi donuyor mu yoksa hata mı donuyor bunları ele alacağız.
                public void done(List<ParseObject> objects, ParseException e) {
                    if(e!=null){ //eğer hata donuyorsa if bloğuna girecek ve hatayı Toast mesajda gosterecek.
                        Toast.makeText(WordsActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        if(objects.size()>0){ //eğer parseObjeleri donuyorsa objects sayısı 0'dan buyuk olacaktır dolayısıyle ıf bloguna girecektir.
                            for(ParseObject object:objects){ //bu donen objects'leri for dongusu ile tek tek gezelim.
                                objectEnglishWord=object.getString("englishWord"); //globak olarak tanımladığım değişkenime object ile gezdiğim ve anahtar kelimesi "englisword" olan değeri atadım.
                                objectTitleEnglishWord=object.getString("englishWord"); // ** ** ** ** **
                                objectTrueWord=object.getString("correctMeaning"); // ** ** ** ** **
                                objectRndMeaning1=object.getString("turkishMeaning1"); // ** ** ** ** **
                                objectRndMeaning2=object.getString("turkishMeaning2"); // ** ** ** ** **
                                objectRndMeaning3=object.getString("turkishMeaning3"); // ** ** ** ** **


                                englishWord.setText(objectEnglishWord);   //englisword adlı textView'da objectEnglishWord'de tuttuğum değeri gösterdim.
                                titleEnglishWord.setText(objectEnglishWord); // ** ** ** ** **
                                String[] meanings=new String[4]; /*bir string dizisi oluşturdum.Burada dizi oluşturmamın amacı veri tabanımdan aldığım ve butonlara aktaracağım
                                 turkce kelimeleri random olarak atabilmek için önce burada tutup ardından rastgele butonlara aktaracağım.
                                  */
                                for(int i=0;i<4;i++){ // 4 değerle işlem yapılacak ve bu 4 değer strin dizisine aktarılacaktır bu nedenle for sınırı 4 dür.
                                    sayi=rnd.nextInt(4); //random bir sayi üretildi bu sayı ,sayı değişkenine aktarıldı.(0 ile 4 arasında sayı uretecek 4 dahil değil.)
                                    if(meanings[sayi]==null){ //uretilen random sayı meanings dizisinin indeksini işaret ediyor ve işaret edilen indeks null ise if bloguna giriyor.
                                        meanings[sayi]=objectTrueWord; //dizinin işaret edilen indeks numarasına objectTrueWord yani veri tabanından çekilen kelimelerden doğru olan kelimeyi dizide rastgele tutulan yerin değerine aktarıyor.
                                        trueMeaning=meanings[sayi];
                                        if(sayi==0){ //eğer rastgele uretilen sayı 0 ise dizide de 0.indekse objectTrueWord'değeri aktarılmuş olduğu için if bloguna giriyor.
                                            //ve sırasıyla ------->
                                            meanings[sayi+1]=objectRndMeaning1; //1.indekse diğer diğer kelimeyi aktaracak.
                                            meanings[sayi+2]=objectRndMeaning2; //2.indekse diğer diğer kelimeyi aktaracak.
                                            meanings[sayi+3]=objectRndMeaning3; //3.indekse diğer diğer kelimeyi aktaracak.
                                        }
                                        if(sayi==1){ //eğer rastgele uretilen sayı 1 ise dizide de 1.indekse objectTrueWord'değeri aktarılmuş olduğu için if bloguna giriyor.
                                            //ve sırasıyla ------->
                                            meanings[sayi-1]=objectRndMeaning1; //0.indekse diğer diğer kelimeyi aktaracak.
                                            meanings[sayi+1]=objectRndMeaning2; //2.indekse diğer diğer kelimeyi aktaracak.
                                            meanings[sayi+2]=objectRndMeaning3; //3.indekse diğer diğer kelimeyi aktaracak.
                                        }
                                        if(sayi==2){//eğer rastgele uretilen sayı 2 ise dizide de 2.indekse objectTrueWord'değeri aktarılmuş olduğu için if bloguna giriyor.
                                            //ve sırasıyla ------->
                                            meanings[sayi-1]=objectRndMeaning1; //1.indekse diğer diğer kelimeyi aktaracak.
                                            meanings[sayi-2]=objectRndMeaning2; //0.indekse diğer diğer kelimeyi aktaracak.
                                            meanings[sayi+1]=objectRndMeaning3; //3.indekse diğer diğer kelimeyi aktaracak.
                                        }
                                        if(sayi==3){//eğer rastgele uretilen sayı 3 ise dizide de 3.indekse objectTrueWord'değeri aktarılmuş olduğu için if bloguna giriyor.
                                            //ve sırasıyla ------->
                                            meanings[sayi-1]=objectRndMeaning1; //2.indekse diğer diğer kelimeyi aktaracak.
                                            meanings[sayi-2]=objectRndMeaning2; //1.indekse diğer diğer kelimeyi aktaracak.
                                            meanings[sayi-3]=objectRndMeaning3; //0.indekse diğer diğer kelimeyi aktaracak.
                                        }
                                    }
                                }
                                /*buraya kadar yapılan işlemler:  veri tabanından 4 kelime çekildi bu 4 kelime String türünde boyutu 4 olan diziye rastgele olarak aktarıldı.
                                Değerler rastgele aktarıldığı için dizinin indekslerinde hangi değerler hangi sırayla yerleştirildi bilinmiyor. Bu yüzden dizi indeksi sırasıyla
                                butonlara aktarmamıza sakınca yoktur.
                                */
                                button4.setText(meanings[0]); //Button4'un textine dizinin 0.indeksi aktarıldı.
                                button5.setText(meanings[1]); //Button5'un textine dizinin 1.indeksi aktarıldı.
                                button6.setText(meanings[2]); //Button6'un textine dizinin 2.indeksi aktarıldı.
                                button7.setText(meanings[3]); //Button7'un textine dizinin 3.indeksi aktarıldı.
                                txtQuestionNumber.setText("Soru:"+count); //Soru sayısını göster.
                            }

                        }
                    }
                }
            });
        }
    }

    //veri tabanından önceki kelimeyi getirerek alt başlıkta sol tarafta gosterecek olan metod.
    public void preBringWords(){
        ParseQuery<ParseObject> query=ParseQuery.getQuery("WordMeanings"); //Sorgumu yapacağım tabloyu belirttim.
        if(count>1){ //Eğer count değişkenimin değeri 1 den buyukse (1 dahil degil)
            query.whereEqualTo("wordMeaningID",count-1); //tablodaki wordMeaningID'ye eşdeğer olan count-1 değerindeki veri aranacak.
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if(e!=null){
                        Toast.makeText(WordsActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        if(objects.size()>0){
                            for(ParseObject object:objects){
                                objectPre=object.getString("englishWord");

                            }
                            preTitleEnglishWord.setText(objectPre);
                        }
                    }
                }
            });
        }
        if(count==1){
            preTitleEnglishWord.setText("-");
        }
    }

    public void nextBringWords(){
        ParseQuery<ParseObject> query=ParseQuery.getQuery("WordMeanings");
        if(count>0){
            query.whereEqualTo("wordMeaningID",count+1);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if(e!=null){
                        Toast.makeText(WordsActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        if(objects.size()>0){
                            for(ParseObject object:objects){
                                objectNext=object.getString("englishWord");

                            }
                            nextTitleEnglishWord.setText(objectNext);
                        }
                    }
                }
            });
        }
        if(count==15){
            nextTitleEnglishWord.setText("-");
        }
    }



    //buton4'e tıkladığında yapılacak işlemler.
    public void clickMeaningBtn4(View view){
        if(button4.getText().equals(trueMeaning)){ //eğer butonun textindeki değer dogru kelime ise if bloguna girecek.
            Toast.makeText(getApplicationContext(), "Doğru Cevap +1", Toast.LENGTH_SHORT).show();
            point++; //pont değeri yanı puan 1 artacak.
            txtTotal.setText(point+"/15"); //txtTotal'in text değerinde guncel puan yazacak.
            clickRightWordMeaning(view);  //bir sonraki kelime sorusuna geçeçek.
        }else{ //eğer butonun textindeki değer yanlış ise else bloguna girecek.
            Toast.makeText(getApplicationContext(), "Yanlış Cevap -1", Toast.LENGTH_SHORT).show();
            point--; //puan 1 azaktılacak.
            txtTotal.setText(point+"/15"); //txtTotal'in text değerinde guncel puan yazacak.
            clickRightWordMeaning(view); //bir sonraki kelime sorusuna geçeçek.
        }
    }

    //buton5'e tıkladığında yapılacak işlemler.
    public void clickMeaningBtn5(View view){
        if(button5.getText().equals(trueMeaning)){//eğer butonun textindeki değer dogru kelime ise if bloguna girecek.
            Toast.makeText(getApplicationContext(), "Doğru Cevap +1", Toast.LENGTH_SHORT).show();
            point++;//pont değeri yanı puan 1 artacak.
            txtTotal.setText(point+"/15");//txtTotal'in text değerinde guncel puan yazacak.
            clickRightWordMeaning(view);//bir sonraki kelime sorusuna geçeçek.
        }else{//eğer butonun textindeki değer yanlış ise else bloguna girecek.
            Toast.makeText(getApplicationContext(), "Yanlış Cevap -1", Toast.LENGTH_SHORT).show();
            point--;//puan 1 azaktılacak.
            txtTotal.setText(point+"/15");//txtTotal'in text değerinde guncel puan yazacak.
            clickRightWordMeaning(view);//bir sonraki kelime sorusuna geçeçek.
        }
    }

    //** ** ** **
    public void clickMeaningBtn6(View view){
        if(button6.getText().equals(trueMeaning)){
            Toast.makeText(getApplicationContext(), "Doğru Cevap +1", Toast.LENGTH_SHORT).show();
            point++;
            txtTotal.setText(point+"/15");
            clickRightWordMeaning(view);
        }else{
            Toast.makeText(getApplicationContext(), "Yanlış Cevap -1", Toast.LENGTH_SHORT).show();
            point--;
            txtTotal.setText(point+"/15");
            clickRightWordMeaning(view);
        }
    }

    //** ** ** **
    public void clickMeaningBtn7(View view){
        if(button7.getText().equals(trueMeaning)){
            Toast.makeText(getApplicationContext(), "Doğru Cevap +1", Toast.LENGTH_SHORT).show();
            point++;
            txtTotal.setText(point+"/15");
            clickRightWordMeaning(view);
        }else{
            Toast.makeText(getApplicationContext(), "Yanlış Cevap -1", Toast.LENGTH_SHORT).show();
            point--;
            txtTotal.setText(point+"/15");
            clickRightWordMeaning(view);
        }
    }



    //sağ ok yönündeki image butona tıklandığında yapılacak işlemler.
    public void clickRightWordMeaning(View view){

        //eğer count değişkenin değeri 16 dan küçükse if bloguna girecek.
        if(count<16){
            count++; //count değeri 1 arttırılacak.
            //Toast.makeText(getApplicationContext(), "Sonraki ", Toast.LENGTH_SHORT).show();
            bringWords(); //kelimeleri getir.
        }
        if(count==16){ //eğer count değişkeninin değeri 16 ya eşit ise if bloguna girecek.
            sharedWordPoint=point;
            sharedPreferences.edit().putInt("storedWordPoint",sharedWordPoint).apply();
            count=1; //count değerinin yeni değerini 1 olarak beliredim.
            bringWords(); //tekrar kelimelerigetirdim (burada count 1 ve verileri count değerine göre çektiği için en başa dönmüş olacak.)
        }


    }
    public void clickProfil(View view){
        Intent intent=new Intent(getApplicationContext(),ProfilActivity.class);
        startActivity(intent);
    }


    //sol ok yönündeki image butona tıklandığında yapılacak işlemler.
    public void clickLeftWordMeaning(View view){
        if(count>=1){ //eğer count değişkeninin değeri 1 den büyük veya eşitse
            count--; //count değerini 1 azalt
            Toast.makeText(getApplicationContext(), "Önceki", Toast.LENGTH_SHORT).show();
            bringWords(); //keliemeleri getir.
        }
        if(count==0){ //eğer count değeri 0'a eşitse if bloguna gir.
            count=15; //count değişkeninin yeni değeri 15 olacak (yani sona atlamış olacak.)
            bringWords(); //kelimeyi getir.
        }
    }

    //Anasayfa butonuna tıklandığında anasayfaya yönlendirecek.
    public void clickMain(View view){
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
    //Translate butonuna basıldığında translateye yonlendirecek.
    public void clickTranslate(View view){
        Intent intent=new Intent(getApplicationContext(),TranslateActivity.class);
        startActivity(intent);
    }

    //Sozluk butonuna basıldığında Sozluk'e yonlendirecek.
    public void clickDictonary(View view){
        Intent intent=new Intent(getApplicationContext(),DictionaryActivity.class);
        startActivity(intent);
    }

    //Eğitim buyonuna basıldığında Eğitim'e yonlendirecek.
    public void clickEducation(View view){
        Intent intent=new Intent(getApplicationContext(),EducationActivity.class);
        startActivity(intent);
    }




}