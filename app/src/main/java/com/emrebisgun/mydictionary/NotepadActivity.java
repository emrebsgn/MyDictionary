package com.emrebisgun.mydictionary;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

import java.util.ArrayList;

public class NotepadActivity extends AppCompatActivity {
    ListView listNote; //Not defterindeki başlıklar bu listede listelenecek.
    ImageView imageNoteAdd;
    int noteIndex;
    int titleIndex;
    ArrayList<Integer> idArray;
    ArrayList<String> titleArray;
    ArrayAdapter arrayAdapter;

    //android:background="#BDE8C1"
    //Burada görsel değişikliğine gidildi.
    /*
    * <ImageView
        android:id="@+id/imageNoteAdd"
        android:layout_width="88dp"
        android:layout_height="71dp"
        android:onClick="clickNoteAdd"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.941"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.981"
        app:srcCompat="@android:drawable/ic_input_add" />*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);

        listNote=findViewById(R.id.listNote); //ListView'ın gorunumunu bul.
        imageNoteAdd=findViewById(R.id.imageNoteAdd); //Image'ın gorunumunu bul.
        idArray=new ArrayList<>(); //İd'leri tutacağım bir ArrayList tanımladım.
        titleArray=new ArrayList<>(); //Title'ları tutacağım bir ArrayList tanımladım.
        arrayAdapter=new ArrayAdapter(this, android.R.layout.preference_category,titleArray); //Notların başlıklarını tutmuştum onları ArrayAdaptere aktardım ki listView da görüntülenecek.
        listNote.setAdapter(arrayAdapter); //ArrayAdapterde tuttuğum title listesini listView'de gösterdim.



        listNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent=new Intent(getApplicationContext(),NotepadReadOrWriteActivity.class);
                intent.putExtra("noteId",idArray.get(i));
                startActivity(intent);
            }
        });


        bringNote();
    }
    public void bringNote(){ //Notları getirmesi için kullandığım bir metod.
        try{
            SQLiteDatabase database=this.openOrCreateDatabase("Notepad",MODE_PRIVATE,null);
            Cursor cursor=database.rawQuery("SELECT * FROM notepad",null);
            noteIndex=cursor.getColumnIndex("id");
            titleIndex=cursor.getColumnIndex("title");

            while (cursor.moveToNext()){
                idArray.add(cursor.getInt(noteIndex));
                titleArray.add(cursor.getString(titleIndex));
            }
            arrayAdapter.notifyDataSetChanged(); //değişlik olduğu zaman arrayAdapter güncellenecek.(Veri kümesinin değiştiğini bildir.)
            cursor.close();


        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Not defteri boş", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickNoteAdd(View view){ // + gorseline tıklandığında yapılacak işlemler.
        Intent intent=new Intent(getApplicationContext(),NotepadAddActivity.class);
        startActivity(intent);

    }


}