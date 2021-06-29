package com.emrebisgun.mydictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;


public class DictionaryActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> dict;
    ArrayList<String> dictDeatils;
    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        listView=findViewById(R.id.listView); //listView'ın görünümünü buldum.




        bringDictList();


        //ArrayAdapter oluşturdum bu adapter bu aktivitede yer alacak ve basit bir görünüme sahip olacak içinede dict'in elemanlarını yazdıracağız.
        arrayAdapter=new ArrayAdapter(DictionaryActivity.this, android.R.layout.simple_list_item_1,dict);
        listView.setAdapter(arrayAdapter); //listview'ın içini arrayadapter de tuttuğunu aktaracağız.

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //listView'daki itemlere tıklandığında yapılacak işlemler.
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) { //Buradaki "i" değişkeni tıklanan itemin indeksidir.
                Intent intent=new Intent(getApplicationContext(),DictionaryDetailsActivity.class); //Intent oluşturup bulunduğu ve gideceği aktiviteyi yazdık.
                intent.putExtra("detail",dictDeatils.get(i)); //intent ile tıklanan item'in indeks numarasına karşılık gelen detay listesinin elemanını gönderdik.

                startActivity(intent); //aktivite başlatılacak.
            }
        });
    }

    public void bringDictList(){
        dict=new ArrayList<>();  //ArrayList oluşturdum bu listeye verileri ekleyeceğiz.
        dict.add("A'da ");
        dict.add("A'lem ");
        dict.add("Ab");
        dict.add("Ab-ı Efsun ");
        dict.add("Ab-ı Hayvan");
        dict.add("Ab-ı Kevser");
        dict.add("Ab-ı Mutahhar");
        dict.add("Ab-ı Nisan");
        dict.add("Abad ");
        dict.add("Abı-puş");
        dict.add("Abd ");
        dict.add("Abdal ");
        dict.add("Abes ");
        dict.add("Abeş");
        dict.add("Ab-ı zemzem");
        dict.add("Abı Hayat");
        dict.add("Abidane");
        dict.add("Abus ");
        dict.add("Acem");
        dict.add("Acem dağları");
        dict.add("Acep");
        dict.add("Açak");
        dict.add("Açaram");
        dict.add("Açılcağ");
        dict.add("Açılıptur");
        dict.add("Adib ");
        dict.add("Adalet ");
        dict.add("Adave ");
        dict.add("Adavet ");
        dict.add("Adem ");
        dict.add("Ademiyet ");
        dict.add("Adet ");
        dict.add("Adlım");
        dict.add("Adu taşı");
        dict.add("Adu");
        dict.add("Adü ");
        dict.add("Adüvan ");
        dict.add("Afak ");
        dict.add("Ağ");
        dict.add("Ağca");
        dict.add("Adu");
        dict.add("Agah");
        dict.add("Ağ lavaş");
        dict.add("Ağ mercan");
        dict.add("Ağca ceyran");
        dict.add("Ağ-gızıl:");
        dict.add("Ağıl");
        dict.add("Ağır sufra");
        dict.add("Ağır zürbe");
        dict.add("Ağlaram");
        dict.add("Ağmak");
        dict.add("Ağu");
        dict.add("Ağyar");
        dict.add("Ah ü firaz");
        dict.add("Aharam");
        dict.add("Ahd ü peyman");
        dict.add("Ahd");
        dict.add("Ahdipeyman-ahdipeyman");
        dict.add("Ahenger");
        dict.add("Aheste ");
        dict.add("Ahıl");
        dict.add("Ahi ");
        dict.add("Ahibba ");
        dict.add("Ahir");
        dict.add("Ahlak ");
        dict.add("Ahmer");
        dict.add("Ahsen-i takvim");
        dict.add("Ahsen ");
        dict.add("Aht ");
        dict.add("Ah-u zar");
        dict.add("Ahü");
        dict.add("Ahval");
        dict.add("Ahval");
        dict.add("Ahz ");
        dict.add("Akça ");
        dict.add("Akdem ");
        dict.add("Akıl yetirmek");
        dict.add("Akl-ı cüz");
        dict.add("Akl-ı Küll");
        dict.add("Akl-ı Mead");
        dict.add("Akşamaca");
        dict.add("Aktöre, Atayi");
        dict.add("Al");
        dict.add("Al-i aba");


        dict.add("Bab");
        dict.add("Babullah");
        dict.add("Bac");
        dict.add("Baç");
        dict.add("Bade");
        dict.add("Baden");
        dict.add("Bad-ı saba");
        dict.add("Bad-ı saba");
        dict.add("Bad-ı sabah");
        dict.add("Bağ ı Cennet");
        dict.add("Bağ");
        dict.add("Bağ-bağat");
        dict.add("Bağban");
        dict.add("Bağır");
        dict.add("Bağman");
        dict.add("Bağrı veran");
        dict.add("Bağu bahçe-bağu bahca");
        dict.add("Bağvan");
        dict.add("Baha");
        dict.add("Bahah");
        dict.add("Bahar");
        dict.add("Bahaya kalmak");
        dict.add("Bahça-bahça");
        dict.add("Bahr");
        dict.add("Bahr-ı muhit");
        dict.add("Bahr-ı zulmet");
        dict.add("Baka");
        dict.add("Bakaram");
        dict.add("Bakasız");
        dict.add("Bakı");
        dict.add("Bakırsan");
        dict.add("Bal ü per");
        dict.add("Bala");
        dict.add("Balaban göz");
        dict.add("Balaban");


        dict.add("Caba");
        dict.add("Cad");
        dict.add("Cah etmek");
        dict.add("Cah");
        dict.add("Cahallığ");
        dict.add("Caht");
        dict.add("Cam");
        dict.add("Can ürekten");
        dict.add("Canal");
        dict.add("Canan");
        dict.add("Canın");
        dict.add("Canpolat Dev");
        dict.add("Cansız at");
        dict.add("Car");
        dict.add("Cayız");
        dict.add("Cazu");


        dict.add("Çağrışmak");
        dict.add("Çal ");
        dict.add("Çalhandı ");
        dict.add("Çalhanmah ");
        dict.add("Çallı-çapraz");
        dict.add("Çalma");
        dict.add("Çalmak");
        dict.add("Çapraz");
        dict.add("Çar anasır");
        dict.add("Çar hisar");
        dict.add("Çar köşe");
        dict.add("Çar");
        dict.add("Çarh");
        dict.add("Çar-havuz");


        dict.add("Dad");
        dict.add("Dağ salı");
        dict.add("Dağ");
        dict.add("Dağdağa");
        dict.add("Dağlanmak");
        dict.add("Dağlı");
        dict.add("Daha");
        dict.add("Daim");
        dict.add("Dal");
        dict.add("Dalam");
        dict.add("Dalda");
        dict.add("Daldalanmak");
        dict.add("Daldalık");
        dict.add("Dalgerdan");
        dict.add("Dallanmak");
        dict.add("Daluptur");
        dict.add("Dam");


        dict.add("Eazi");
        dict.add("Ebrişim");
        dict.add("Ebru");
        dict.add("Ebrüm ebrüm");
        dict.add("Ebtüm");
        dict.add("Ecel kuşları");
        dict.add("Ecel kuşu");
        dict.add("Eda");
        dict.add("Eder ");
        dict.add("Edim ");
        dict.add("Edin");
        dict.add("Edip");
        dict.add("Edna");
        dict.add("Efgan");
        dict.add("Eflak");
        dict.add("Efsun");



        dict.add("Fakı");
        dict.add("Fakir Emrah");
        dict.add("Farı");
        dict.add("Farımak");
        dict.add("Farz");
        dict.add("Fasık");
        dict.add("Faş");
        dict.add("Fazl");
        dict.add("Fazlı yezdan");
        dict.add("Fel");
        dict.add("Felek");
        dict.add("Fena mülkü (Fena şehri");
        dict.add("Fena");
        dict.add("Fend");
        dict.add("Ferace");
        dict.add("Ferağ");
        dict.add("Fere keklik");
        dict.add("Ferhat");
        dict.add("Ferişte");
        dict.add("Fetalına");
        dict.add("Fe-tebarekallah");











        dictDeatils=new ArrayList<>();  //ArrayList oluşturdum bu listeye kelimeye karşılık gelen açıklamayı ekledim.
        dictDeatils.add("Düşmanlar.");
        dictDeatils.add("Daha iyi bilir, bilirim.");
        dictDeatils.add("Su.");
        dictDeatils.add("Göz yaşı.");
        dictDeatils.add("Dirilik suyu, bengisu.");
        dictDeatils.add("Kevser suyu.");
        dictDeatils.add("Temiz su.");
        dictDeatils.add("Nisan yağmuru, söylenceye göre, nisan ayında sedefler, deniz dibinden su yüzüne çıkıp, yağmur danelerini içine alıp. sedef yaparmış.");
        dictDeatils.add("Zengin olma, varlıklı olma, bayındır.");
        dictDeatils.add("Aba giyen, derviş, fakir.");
        dictDeatils.add("Kul, köle.");
        dictDeatils.add("Gezgin derviş. Derviş, Tanrı sevgilisi, kırk din ulusundan biri. Saçlarını, kaşlarını, bıyıklarını ve sakallarını usturayla tıraş ettiren, davul ve dümbeleklerle, sancaklarla toplu halde gezen Şii -Batıni bir derviş topluluğu, doğrudan doğruya derviş anlamına da gelir.");
        dictDeatils.add("Boş, asılsız, saçma");
        dictDeatils.add("Kula renkte at, alacalı hayvan.");
        dictDeatils.add("Kabe yakınlarında bir kuyu ve bu kuyunun Müslümanlarca kutsal sayılan suyu.");
        dictDeatils.add("Ölümsüzlük suyu, bengisu");
        dictDeatils.add("İbadet edene yakışacak bir surette.");
        dictDeatils.add("Somurtkan.");
        dictDeatils.add("İranlı.");
        dictDeatils.add("Batı İran dağları.");
        dictDeatils.add("Acaba");
        dictDeatils.add("Açalım");
        dictDeatils.add("Açarım");
        dictDeatils.add("Açılınca gelince.");
        dictDeatils.add("Açılmıştır.");
        dictDeatils.add("Edepler, töreler.");
        dictDeatils.add("Hak tüze.");
        dictDeatils.add("Düşmanlık.");
        dictDeatils.add("Düşmanlık, buğz, yağılık.");
        dictDeatils.add("İlk peygamberin adı, insan.");
        dictDeatils.add("İnsanlık, insancılılık.");
        dictDeatils.add("Görenek, sayı.");
        dictDeatils.add("Ünlü, ünü büyük.");
        dictDeatils.add("Düşman taşı.");
        dictDeatils.add("Düşman, hasım.");
        dictDeatils.add("Düşman, yağı.");
        dictDeatils.add("Can düşmanı.");
        dictDeatils.add("Ufuklar, gökyüzünün kenarları");
        dictDeatils.add("Ak.");
        dictDeatils.add("Akça, aka yakın, alacalı.");
        dictDeatils.add("Düşman.");
        dictDeatils.add("Vakıf, bilen.");
        dictDeatils.add("Yufka ekmek. Ak undan yapılmış yufka ekmek.");
        dictDeatils.add("Ak mercan. [mec. Ak meme, sevgilinin süt gibi ak olan memesi.");
        dictDeatils.add("Ak ceylan. ''Ağca ceyran sürme çekip gözüne.'' (Ak ceylana benzetilerek sevgilinin güzelliğinin vurgulanması.)");
        dictDeatils.add("Ak, kızıl karışığı renk, alacalı.");
        dictDeatils.add("Koyun ve keçi sürülerinin gecelediği çit ya da duvarla çevrildiği yer.");
        dictDeatils.add(" Şölen sofrası.");
        dictDeatils.add("Yabankazı, yabanördeği, turna gibi kuşların uçarken yaptıkları büyük dizi, katar.\n");
        dictDeatils.add("Ağlarım.");
        dictDeatils.add("Akmak, karışmak. ''Sırdaş olup ağ sulara.'");
        dictDeatils.add("Ağı, zehir.");
        dictDeatils.add("Başkaları.");
        dictDeatils.add("Ah edip inlemek, ağlamak.");
        dictDeatils.add("Akarım. ''Aharam seller içinde.''");
        dictDeatils.add("Yemin, and.");
        dictDeatils.add("Vadetme, söz verme.");
        dictDeatils.add("Ant, anta dayalı sözleşme, antlaşarak yapılan sözleşme.");
        dictDeatils.add("Demirci.");
        dictDeatils.add("Yavaş, ağır, yavaş yavaş.");
        dictDeatils.add("Akıl.");
        dictDeatils.add(" Esnafı öğütleyen Fütuvvet ehlinin şeyhi,");
        dictDeatils.add("Dostlar , sevgililer");
        dictDeatils.add("En son, sondaki, nihayet son olarak.");
        dictDeatils.add("Huylar, davranışlar, Etik.");
        dictDeatils.add("Kırmızı , kızıl.");
        dictDeatils.add("En güzel kıvama koyma, Cenab-ı Hakkın her şeyi kendisine layık en güzel kıvam, sıfat ve surette yaratılması.");
        dictDeatils.add("Çok güzel.");
        dictDeatils.add("Sözleşme.");
        dictDeatils.add("Yüksek sesle ağlama, dövünme.");
        dictDeatils.add("Ceylan, güzellerin gözü (Mec,)");
        dictDeatils.add("Durum, durumlar.");
        dictDeatils.add("Haller vaziyetler , oluşlar .");
        dictDeatils.add("Almak");
        dictDeatils.add("Para");
        dictDeatils.add("İlk, önce, önceki, daha önceki.");
        dictDeatils.add("Akıl erdirmek.");
        dictDeatils.add("Cüz'i akıl, tikel us");
        dictDeatils.add("Tüm akıl; Tanrı bilgisi");
        dictDeatils.add("Ahirete dönük akıl");
        dictDeatils.add("Akşama değin, akşama kadar.");
        dictDeatils.add("Armağan.");
        dictDeatils.add("Hile, aldatma işi.");
        dictDeatils.add("Muhammed, Ali, Fatıma, Hasan ve Hüseyin'den oluşan kutsal topluluk");


        dictDeatils.add("Bahis, kapı.");
        dictDeatils.add("Allah kapısı.");
        dictDeatils.add("Baç.");
        dictDeatils.add("Haraç, vergi,Osmanlı imparatorluğunda gümrük vergisi, zorla alınan para harç.");
        dictDeatils.add("1. Esriklik veren içki. 2. Pir'in, Üçler'in, Erenler'in içirdiğine inanılan aşık edici içki, şarap.");
        dictDeatils.add("Semiz, İri gövdeli kimse.");
        dictDeatils.add("Bahar sabahları, gün doğumunda esen hafif yel.");
        dictDeatils.add("Seher yeli.");
        dictDeatils.add("Bad-ı saba");
        dictDeatils.add("Cennet bağı, cennet benzeri bahçe.");
        dictDeatils.add("1. Demet, deste, 2. Üzüm kütüklerinin dikili olduğu toprak parçası, üzümlük. 3. Bahçe.");
        dictDeatils.add("Bağ, bağçe");
        dictDeatils.add("Bahçıvan, bağcı.");
        dictDeatils.add("1.Yürek, gönül 2.Göğüs 3. Sine");
        dictDeatils.add("Bahçıvan, bağcı.");
        dictDeatils.add("Gönlü yıkık, üzgün.");
        dictDeatils.add(" Bağ-bahçe.");
        dictDeatils.add("Bahçıvan, bağcı.");
        dictDeatils.add("Değer.");
        dictDeatils.add("Bakalım, görelim.");
        dictDeatils.add(" Bakar");
        dictDeatils.add("Değer biçilebilir olmak.");
        dictDeatils.add("Bahçe");
        dictDeatils.add("Deniz, büyük göl veya nehir .");
        dictDeatils.add("Okyanus.");
        dictDeatils.add("Zulmet denizi.");
        dictDeatils.add("utam, demet, beste.");
        dictDeatils.add("Bakarım.");
        dictDeatils.add("Destesiz.");
        dictDeatils.add("Baki, sürekli, kalıcı.");
        dictDeatils.add("Bakıyorsun.");
        dictDeatils.add("Kanat.");
        dictDeatils.add("Çocuk, yavru.");
        dictDeatils.add("Keskin bakışlı, iri güzel göz.");
        dictDeatils.add("1. Sazlıklarda yaşayan, tüyleri kızıl-külrengi karışığı renkli, iri bir kuş. 2. Atmaca, doğan gibi avcı kuşlara kimi bölgelerde verilen ad.");



        dictDeatils.add(" Fazladan, üstelik, bir şey ödemeden alman şey .");
        dictDeatils.add("Darı ekmeği.");
        dictDeatils.add("İtibar etmek.");
        dictDeatils.add("Makam, itibar.");
        dictDeatils.add("Gençlik çağı.");
        dictDeatils.add("Bile bile inkar etme.");
        dictDeatils.add("Kadeh, bardak, şişe ve toprak cinsinden şarap kadehi.");
        dictDeatils.add("Candan yürekten, içtenlikle, severekten.");
        dictDeatils.add("Canan, sevgili.");
        dictDeatils.add("Gönülden sevilen, gönül verilmiş olan kadın.");
        dictDeatils.add("Canımın.");
        dictDeatils.add(" Bir masal yaratığı.");
        dictDeatils.add("Tabut, salaca.");
        dictDeatils.add("Çarşaf, komşu, yardımcı, medet eden.");
        dictDeatils.add("Caiz, olabilir, yakışık alan.");
        dictDeatils.add(". Cadı, oyunbaz. 2. Çok güzel.");


        dictDeatils.add("Bir ağızdan bağırmak, yaygara etmek.");
        dictDeatils.add("Ala renk.");
        dictDeatils.add("Çalkandı");
        dictDeatils.add("Çalkanmak.");
        dictDeatils.add("Çapraz çizgili bir şal deseni.");
        dictDeatils.add("1.Başa sarık gibi bağlanan düz ya da işlemeli kumaş. 2.Çember de denilen baş örtüsü, çetme.");
        dictDeatils.add("Doğmak, vurmak, atmak");
        dictDeatils.add("Eğik olarak birbiriyle kesişen.");
        dictDeatils.add("Dört unsur , dört temel unsur .(Toprak-su-hava-güneş)");
        dictDeatils.add("Dört kale burcu.");
        dictDeatils.add("Dört köşe.");
        dictDeatils.add("Dört.");
        dictDeatils.add("Çark, felek, gök, devreden, dönen.");
        dictDeatils.add("Büyük havuz.");


        dictDeatils.add("Damgalı.");
        dictDeatils.add("Bundan sonra.");
        dictDeatils.add("Sürekli, her an, daima.");
        dictDeatils.add("Omuz, omuz başı.");
        dictDeatils.add("Dalayım.");
        dictDeatils.add("Gölge.");
        dictDeatils.add(" Gölgelenmek.");
        dictDeatils.add("Gölgelik.");
        dictDeatils.add("1.Güzel göğüs. 2.Vücudun omuzla birlikte göğüsten yukarı bölümü, büst. Dalıptır: Dalmıştır, dalıyor.");
        dictDeatils.add("Salınmak, sallanmak.");
        dictDeatils.add("Dalmıştır, dalıyor.");
        dictDeatils.add("Tuzak.");


        dictDeatils.add(" Aziz, izzetli, yüksek.");
        dictDeatils.add("Kalınca bükülmüş ipek, iplik, saç, ibrişim.");
        dictDeatils.add("Kaş.");
        dictDeatils.add("Büklüm büklüm, dalga dalga.");
        dictDeatils.add("Dalga, büklüm.");
        dictDeatils.add("Doğan, şahin, atmaca gibi avcı-yırtıcı-kuşlar.");
        dictDeatils.add("Ölüm.");
        dictDeatils.add("Biçem [üslup], çalım, işve, naz.");
        dictDeatils.add("Der, der ki.");
        dictDeatils.add("Edeyim.");
        dictDeatils.add("Edin, verilen, eyleyin.");
        dictDeatils.add("Ederek, etti.");
        dictDeatils.add(" Basit, değersiz.");
        dictDeatils.add("Yüksek sesle yakınma, inleme.");
        dictDeatils.add("Felek, felekler , gökler , alemler.");
        dictDeatils.add("Sihirli, büyülü, çekici.");


        dictDeatils.add("Fakih, hoca, alim, din bilgini.");
        dictDeatils.add("Ercişli Emrah.");
        dictDeatils.add("Yüce.");
        dictDeatils.add("Yaşlanmak, yıpranmak, yorulmak.");
        dictDeatils.add("1.Müslümanlıkta özür olmadıkça yapılması zorunlu, yapılmaması günah sayılan Tanrı buyruğu. 2.Doğru sonuca varmak için yapılması zorunlu olan.");
        dictDeatils.add("Günahkar , Hak yolundan hariç olan. Allah'ın emirlerine karşı zıt hareket eden. Büyük günah işleyen ya da küçük günahlarda ısrar eden kimse.");
        dictDeatils.add("Açma, ortaya çıkarma.");
        dictDeatils.add("Lütuf.");
        dictDeatils.add("Tanrının lütfu.");
        dictDeatils.add("Fi'il. İş, tutum, davranış, oyunbozanlık, dek, desise.");
        dictDeatils.add("Gökyüzü, sema.");
        dictDeatils.add("Kader, talih, baht, şans.");
        dictDeatils.add("Ağız.");
        dictDeatils.add("Geçici dünya, kendi varlığından geçme.");
        dictDeatils.add("Yok olma, yokluk, geçiş gitme. Tasavvufta maddi varlıktan sıyrılıp Hakk'a ulaşma.");
        dictDeatils.add("Hile, oyun.");
        dictDeatils.add("Kadınlar için bol ve uzun üst giysisi. Başörtü.");
        dictDeatils.add("Gözyaşı.");
        dictDeatils.add("Erginleşmemiş keklik.");
        dictDeatils.add("Ferhat ile Şirin Hikayesi'nin erkek kahramanı.");
        dictDeatils.add("Melek.");
        dictDeatils.add("Övgü.");
        dictDeatils.add("Ne kadar bereketli, ne kadar güzel anlamında şaşma bildirir. Allah övmüşte yaratmış anlamında bir söz.");
    }



    public void homeClick(View view){ //ev simgesine tıklandığında yapılacak işlemler.
        Intent intent=new Intent(getApplicationContext(),MainActivity.class); //intent ile bulunan aktivite ve gidilecek aktivite belirlendi.
        startActivity(intent); //aktivite başlatılacak.
    }
}