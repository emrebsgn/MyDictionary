package com.emrebisgun.mydictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.sql.DatabaseMetaData;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences=this.getSharedPreferences("com.emrebisgun.mydictionary", Context.MODE_PRIVATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //Hangi menuyu kullanacağımı belirmemi sağlayan metod.
        MenuInflater menuInflater=getMenuInflater(); //Menu yayınlayıcı oluşturdum.
        menuInflater.inflate(R.menu.menu_main,menu); //Yayınlayacağım menunun konumu belirttim.
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //yayınladığım menu elemanlarına tıklandığında yapılacak işlemler.
        if(item.getItemId()==R.id.menu_main){ //Belirtilen konumdaki menunun ID'sine tıklandığında yapılacak işlemler.
            ParseUser.logOutInBackground(new LogOutCallback() {
                @Override
                public void done(ParseException e) {
                    if(e!=null){ //Eğer hata varse if bloguna gir.
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show(); //Hatayı göster.
                    }else{
                        sharedPreferences.edit().remove("storedWordPoint").apply();
                        sharedPreferences.edit().remove("storedSentenceScore").apply();
                        Intent intent=new Intent(getApplicationContext(),LoginScreenActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Çıkış yapıldı!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    public void clickDictionary(View view){
        Intent intent=new Intent(MainActivity.this,DictionaryActivity.class);
        startActivity(intent);
    }
    public void clickNotePad(View view){
        Intent intent=new Intent(MainActivity.this,NotepadActivity.class);
        startActivity(intent);
    }
    public void clickEducation(View view){
        Intent intent=new Intent(MainActivity.this,EducationActivity.class);
        startActivity(intent);
    }

    public void clickProfil(View view){
        Intent intent=new Intent(MainActivity.this,ProfilActivity.class);
        startActivity(intent);
    }
    public void clickTranslate(View view){
        Intent intent=new Intent(getApplicationContext(),TranslateActivity.class);
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Yönlendirme");
        alert.setMessage("Translate sayfasına geçmek istiyor musunuz?");
        alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(intent);
            }
        });
        alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alert.show();

    }
}