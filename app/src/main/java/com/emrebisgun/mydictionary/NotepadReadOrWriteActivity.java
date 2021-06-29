package com.emrebisgun.mydictionary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NotepadReadOrWriteActivity extends AppCompatActivity {
    EditText editTextTitle;
    EditText editTextContent;
    String titleName; //Veri tabanına eklemek için editText'deki textleri string değişkeninde tuttum.
    String contentName;
    SQLiteDatabase database;
    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad_read_or_write);

        editTextTitle=findViewById(R.id.editTextTitle);
        editTextContent=findViewById(R.id.editTextContent);
        database=this.openOrCreateDatabase("Notepad",MODE_PRIVATE,null);
        Intent intent=getIntent();
        noteId=intent.getIntExtra("noteId",1);

        try{
            Cursor cursor=database.rawQuery("SELECT * FROM Notepad Where id=?",new String[]{String.valueOf(noteId)}); //Id ye gore verileri aldı.
            int titleIndex=cursor.getColumnIndex("title");
            int contentIndex=cursor.getColumnIndex("content");

            while(cursor.moveToNext()){
                editTextTitle.setText(cursor.getString(titleIndex));
                editTextContent.setText(cursor.getString(contentIndex));
            }

            cursor.close();
        }catch (Exception e){

        }
    }
    public boolean onCreateOptionsMenu(Menu menu){ //Hangi menu eklenecekse burada belirtilecek.
        MenuInflater menuInflater=getMenuInflater(); //Menu yayınlayıcı oluştur.
        menuInflater.inflate(R.menu.menu_notepad_details,menu); //Bu adresteki menuyu yayınla.
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //Menudeki itemler seçildiğinde yapılacak işlemler burada yazılacak.
        Intent intent=new Intent(getApplicationContext(),NotepadActivity.class);

        //Eğer menude sil'e tıklanırsa yapılacak işlemler.(Başlık ve İçerik komple silinecek ve listView'da artık görüntülenmeyecek.)
        if(item.getItemId()==R.id.note_delete){
            titleName=editTextTitle.getText().toString();
            contentName=editTextContent.getText().toString();
            try{
                SQLiteDatabase database=this.openOrCreateDatabase("Notepad",MODE_PRIVATE,null); //Notepad adında bir veritabanı oluştur eğer bu isimde bir veri tabanı varsa onu aç.
                database.execSQL("CREATE TABLE IF NOT EXISTS notepad(id INTEGER PRIMARY KEY,title VARCHAR,content VARCHAR)"); //Belirtilen veri tipi ve isimleri ile tablo oluşturuldu.
                String cmd="DELETE FROM notepad WHERE id=?"; //Sorgu metni değişkende tutulacak.
                SQLiteStatement sqLiteStatement=database.compileStatement(cmd); //SQLiteStatement'ın hangi veri tabanında ve hangi sorgu değişkeniyle compile edileceği belirtildi.
                sqLiteStatement.bindLong(1,noteId); //1.soru işareti yerine eklenecek olan değer belirtildi.
                sqLiteStatement.execute();
                Toast.makeText(getApplicationContext(), "Not silindi!", Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }catch (Exception e){ //hata varsa yakala.
                Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show(); //hatayı Toast'da göster.
            }
        }

        /*
        //Menudeki Kaydet'e tıklandığında yapılacak işlemler.
        if(item.getItemId()==R.id.note_save){
            titleName=editTextTitle.getText().toString();
            contentName=editTextContent.getText().toString();
            try{
                SQLiteDatabase database=this.openOrCreateDatabase("Notepad",MODE_PRIVATE,null); //Notepad adında bir veritabanı oluştur eğer bu isimde bir veri tabanı varsa onu aç.
                //database.execSQL("CREATE TABLE IF NOT EXISTS notepad(id INTEGER PRIMARY KEY,title VARCHAR,content VARCHAR)"); //Belirtilen veri tipi ve isimleri ile tablo oluşturuldu.
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
        }*/

        //Menudeki Değişiklikleri kaydet'e tıklandığında yapılacak işlmeler.
        if(item.getItemId()==R.id.note_save_changes){
            titleName=editTextTitle.getText().toString();
            contentName=editTextContent.getText().toString();
            try{
                SQLiteDatabase database=this.openOrCreateDatabase("Notepad",MODE_PRIVATE,null);
                String cmd="UPDATE notepad SET title=?,content=? WHERE id=?";
                SQLiteStatement sqLiteStatement=database.compileStatement(cmd);
                sqLiteStatement.bindString(1,titleName);
                sqLiteStatement.bindString(2,contentName);
                sqLiteStatement.bindLong(3,noteId);
                sqLiteStatement.execute();
                Toast.makeText(getApplicationContext(), "Değişiklikler Kaydedildi!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }


        return super.onOptionsItemSelected(item);
    }

    public void homeClick(View view){ //ev simgesine tıklandığında yapılacak işlemler.
        Intent intent=new Intent(getApplicationContext(),MainActivity.class); //intent ile bulunan aktivite ve gidilecek aktivite belirlendi.
        startActivity(intent); //aktivite başlatılacak.
    }

}