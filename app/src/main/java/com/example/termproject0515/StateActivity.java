package com.example.termproject0515;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class StateActivity extends AppCompatActivity {

    private List<String> list;          // 데이터를 넣은 리스트변수
    private ListView listView;          // 검색을 보여줄 리스트변수
    private StateAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arraylist;

    ArrayList<String> number = new ArrayList<String>();
    ArrayList<String> claim = new ArrayList<String>();
    ArrayList<String> title = new ArrayList<String>();
    ArrayList<String> author = new ArrayList<String>();
    ArrayList<String> publisher = new ArrayList<String>();
    ArrayList<String> state  = new ArrayList<String>();
    String state_str;

    Date currentTime = Calendar.getInstance().getTime();
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
    SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());

    String year = yearFormat.format(currentTime);
    String month = monthFormat.format(currentTime);
    String day = dayFormat.format(currentTime);
    String date;
    myDBHelper myHelper;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
        setTitle("대여 현황");

        listView = (ListView) findViewById(R.id.state_lv);

        myHelper = new myDBHelper(this);
        sqlDB = myHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT * FROM infoTBL WHERE state = 0;", null);
        cursor.moveToFirst();

        while (cursor.moveToNext()) {
            number.add(cursor.getString(0));
            claim.add(cursor.getString(1));
            title.add(cursor.getString(2));
            author.add(cursor.getString(3));
            publisher.add(cursor.getString(4));
            state_str = cursor.getString(7);
            if(state_str.equals("1")){
                state.add(year + "년 " + month + "월 " + day +"일");
            }
        }

        int day1 = Integer.parseInt(day) + 7 ;
        date = String.valueOf(day1);

        cursor.close();
        sqlDB.close();

        // 리스트를 생성
        list = new ArrayList<String>();

        // 검색에 사용할 데이터을 미리 저장
        settingList();

        // 리스트의 모든 데이터를 arraylist에 복사 // list 복사본을 생성
        arraylist = new ArrayList<String>();
        arraylist.addAll(list);

        // 리스트에 연동될 아답터 생성
        adapter = new StateAdapter(list, this);
        adapter.notifyDataSetChanged();

    }

    // DB
    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "Book_info.db", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    private void settingList() {
        for (int i=0; i<title.size(); i++){
            list.add("제목 : "+ title.get(i)+"\n"+"저자 : "+ author.get(i)+"\n"+ "출판사 : "+ publisher.get(i)+"\n"+
                    "등록 번호 : "+ number.get(i)+"\n"+ "청구 번호 : "+ claim.get(i)+ "\n"+"대출 일자 :" + state.get(i) + "\n" +
                    "반납 일자(기한) : " + year + "년 " + month + "월 " + date +"일 (7일 남음)" );
        }

    }
}
