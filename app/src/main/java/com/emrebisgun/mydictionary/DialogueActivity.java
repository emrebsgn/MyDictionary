package com.emrebisgun.mydictionary;

import androidx.appcompat.app.AppCompatActivity;

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

public class DialogueActivity extends AppCompatActivity {

    TextView titleDialogue;
    Button btnDialogue1;
    Button btnDialogue2;
    Button btnDialogue3;
    Button btnDialogue4;
    Button btnDialogue5;
    Button btnDialogue6;
    private int number=1;
    String objectDialogEng1;
    String objectDialogEng2;
    String objectDialogEng3;
    String objectDialogEng4;
    String objectDialogEng5;
    String objectDialogEng6;
    String objectDialogTurk1;
    String objectDialogTurk2;
    String objectDialogTurk3;
    String objectDialogTurk4;
    String objectDialogTurk5;
    String objectDialogTurk6;
    String objectDialogName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogue);

        btnDialogue1=findViewById(R.id.btnDialogue1);
        btnDialogue2=findViewById(R.id.btnDialogue2);
        btnDialogue3=findViewById(R.id.btnDialogue3);
        btnDialogue4=findViewById(R.id.btnDialogue4);
        btnDialogue5=findViewById(R.id.btnDialogue5);
        btnDialogue6=findViewById(R.id.btnDialogue6);
        titleDialogue=findViewById(R.id.titleDialogue);



        bringDialogue();

    }
    public void clickBtnDialogue1(View view){
        Toast.makeText(getApplicationContext(), objectDialogTurk1, Toast.LENGTH_LONG).show();
    }
    public void clickBtnDialogue2(View view){
        Toast.makeText(getApplicationContext(), objectDialogTurk2, Toast.LENGTH_LONG).show();
    }
    public void clickBtnDialogue3(View view){
        Toast.makeText(getApplicationContext(), objectDialogTurk3, Toast.LENGTH_LONG).show();
    }
    public void clickBtnDialogue4(View view){
        Toast.makeText(getApplicationContext(), objectDialogTurk4, Toast.LENGTH_LONG).show();
    }
    public void clickBtnDialogue5(View view){
        Toast.makeText(getApplicationContext(), objectDialogTurk5, Toast.LENGTH_LONG).show();
    }
    public void clickBtnDialogue6(View view){
        Toast.makeText(getApplicationContext(), objectDialogTurk6, Toast.LENGTH_LONG).show();
    }


    //dialogları ıd ye gore çektiğim metod. (Veri tabanımdaki "Dialog" adındaki tablomun ID'si,buradaki number değişkenimin değeriyle eşdeğer olacak.)
    public void bringDialogue(){
        ParseQuery<ParseObject> query=ParseQuery.getQuery("Dialog"); //Veri tabanı sorgumu oluşturdum.
        if(number>0){ //Count'a başlangıç değeri 1 verdim burada if blogu içine girecek.
            query.whereEqualTo("dialogID",number); //Count sayısı veri tabanımdaki tablomun ID'sine karşılık gelecek ve ona göre arama yapacak.
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if(e!=null){ //Eğer hata döndürüyorsa
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show(); //Hatayı Toast Message'de gösterecek.

                    }else{
                        if(objects.size()>0){ //Eğer değerler varsa if bloğuna girecek ve sutunları tek tek alacak.
                            for(ParseObject object:objects){
                                objectDialogEng1=object.getString("dialogEng1");
                                objectDialogTurk1=object.getString("dialogTurk1");
                                objectDialogEng2=object.getString("dialogEng2");
                                objectDialogTurk2=object.getString("dialogTurk2");
                                objectDialogEng3=object.getString("dialogEng3");
                                objectDialogTurk3=object.getString("dialogTurk3");
                                objectDialogEng4=object.getString("dialogEng4");
                                objectDialogTurk4=object.getString("dialogTurk4");
                                objectDialogEng5=object.getString("dialogEng5");
                                objectDialogTurk5=object.getString("dialogTurk5");
                                objectDialogEng6=object.getString("dialogEng6");
                                objectDialogTurk6=object.getString("dialogTurk6");
                                objectDialogName=object.getString("dialogName");

                                btnDialogue1.setText(objectDialogEng1);
                                btnDialogue2.setText(objectDialogEng2);
                                btnDialogue3.setText(objectDialogEng3);
                                btnDialogue4.setText(objectDialogEng4);
                                btnDialogue5.setText(objectDialogEng5);
                                btnDialogue6.setText(objectDialogEng6);
                                titleDialogue.setText(objectDialogName);

                            }
                        }
                    }
                }
            });
        }

    }

    //Sağ ok yonünü gösteren image butonu.
    public void clickBtnRightDialogue(View view){
        if(number<=6){ //eğer number değişkenimin değeri 6 ya eşit veya küçükse if bloguna girecek.
            number++;  //number değeri 1 arttırılacak , number değeri değiştiği için aşağıda tekrar dialogları çektiğim metodu çağıracağım.
            bringDialogue();
        }
        if(number>6){
            number=1;
            bringDialogue();
        }
    }
    public void clickBtnLeftDialogue(View view){
        if(number>0 && number<=6){
            number--;
            bringDialogue();
        }
        if(number==0){
            number=6;
            bringDialogue();
        }

    }


}