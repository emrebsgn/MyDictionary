package com.emrebisgun.mydictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NotepadAddActivity extends AppCompatActivity {

    EditText editTextTitle; //Başlık texti
    EditText editTextContent; //İçerik text,
    String titleName; //Veri tabanına eklemek için editText'deki textleri string değişkeninde tuttum.
    String contentName;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad_add);

        editTextTitle=findViewById(R.id.editTextTitle); //Id tarafından görünümü bul.
        editTextContent=findViewById(R.id.editTextContent); //Id tarafından görünümü bul.
    }
    public boolean onCreateOptionsMenu(Menu menu){ //Hangi menu eklenecekse burada belirtilecek.
        MenuInflater menuInflater=getMenuInflater(); //Menu yayınlayıcı oluştur.
        menuInflater.inflate(R.menu.menu_notepad,menu); //Bu adresteki menuyu yayınla.
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //Menudeki itemler seçildiğinde yapılacak işlemler burada yazılacak.
        Intent intent=new Intent(getApplicationContext(),NotepadActivity.class);
        if(item.getItemId()==R.id.menu_notepad){ //eğer menunun item'ine tıklandıysa. Yani item'de yazan kaydet seçeneğine tıklandıysa başlık ve içeriği kaydedecek.
            titleName=editTextTitle.getText().toString();
            contentName=editTextContent.getText().toString();
            try{
                SQLiteDatabase database=this.openOrCreateDatabase("Notepad",MODE_PRIVATE,null); //Notepad adında bir veritabanı oluştur eğer bu isimde bir veri tabanı varsa onu aç.
                database.execSQL("CREATE TABLE IF NOT EXISTS notepad(id INTEGER PRIMARY KEY,title VARCHAR,content VARCHAR)"); //Belirtilen veri tipi ve isimleri ile tablo oluşturuldu.
                String cmd="INSERT INTO notepad(title,content) VALUES(?,?)"; //Sorgu metni değişkende tutulacak.
                SQLiteStatement sqLiteStatement=database.compileStatement(cmd); //SQLiteStatement'ın hangi veri tabanında ve hangi sorgu değişkeniyle compile edileceği belirtildi.
                sqLiteStatement.bindString(1,titleName); //1.soru işareti yerine eklenecek olan değer belirtildi.
                sqLiteStatement.bindString(2,contentName);
                sqLiteStatement.execute();
                Toast.makeText(getApplicationContext(), "Not eklendi!", Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }catch (Exception e){ //hata varsa yakala.
                Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show(); //hatayı Toast'da göster.
            }


        }

        return super.onOptionsItemSelected(item);
    }
    public void homeClick(View view){ //ev simgesine tıklandığında yapılacak işlemler.
        Intent intent=new Intent(getApplicationContext(),MainActivity.class); //intent ile bulunan aktivite ve gidilecek aktivite belirlendi.
        startActivity(intent); //aktivite başlatılacak.
    }
}