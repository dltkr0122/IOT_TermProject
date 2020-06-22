package com.example.termproject0515;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class LocationActivity extends AppCompatActivity {

    ImageView map;
    Button take_btn, return_btn;
    private SearchAdapter SAdapter;
    public static String booktitle3;
    String target;
    String locate;

    myDBHelper myHelper;
    SQLiteDatabase sqlDB;

    public LocationActivity(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        setTitle("도서 위치 맵");

        booktitle3 = SAdapter.booktitle;

        map = (ImageView) findViewById(R.id.location_map);
        take_btn = (Button) findViewById(R.id.take_button2);
        return_btn = (Button) findViewById(R.id.return_main_button);

        myHelper = new myDBHelper(this);
        sqlDB = myHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT Claim_sign, Location FROM infoTBL WHERE Title = '"+ booktitle3 +"';", null);

        while (cursor.moveToNext()) {
            String get_Claim = cursor.getString(0);
            String get_Location = cursor.getString(1);
            target = get_Claim;
            locate = get_Location;
        }

        cursor.close();
        sqlDB.close();

        // 청구 번호 앞자리 숫자 기준으로 세자리 가져오기
        String target_num = target.replaceAll("[^\\d]", "");
        String target_num1 = target_num.substring(0,3);
        int target_n = Integer.parseInt(target_num1);

        if (locate.equals("종합자료실")){
            if (target_n <= 70){
                map.setImageResource(R.drawable.total1);
            } else if (71 < target_n && target_n <= 140) {
                map.setImageResource(R.drawable.total2);
            } else if (141 < target_n && target_n <= 210) {
                map.setImageResource(R.drawable.total3);
            } else if (211 < target_n && target_n <= 280) {
                map.setImageResource(R.drawable.total4);
            } else if (281 < target_n && target_n <= 350) {
                map.setImageResource(R.drawable.total5);
            } else if (351 < target_n && target_n <= 420) {
                map.setImageResource(R.drawable.total6);
            } else if (421 < target_n && target_n <= 490) {
                map.setImageResource(R.drawable.total7);
            } else if (491 < target_n && target_n <= 560) {
                map.setImageResource(R.drawable.total8);
            } else if (561 < target_n && target_n <= 700) {
                map.setImageResource(R.drawable.total9);
            } else if (701 < target_n && target_n <= 800) {
                map.setImageResource(R.drawable.total10);
            } else if (801 < target_n && target_n <= 900) {
                map.setImageResource(R.drawable.total11);
            } else {
                map.setImageResource(R.drawable.total12);
            }
        } else if (locate.equals("어린이자료실")){
            if (target_n <= 70){
                map.setImageResource(R.drawable.child1);
            } else if (71 < target_n && target_n <= 140) {
                map.setImageResource(R.drawable.child2);
            } else if (141 < target_n && target_n <= 210) {
                map.setImageResource(R.drawable.child3);
            } else if (211 < target_n && target_n <= 280) {
                map.setImageResource(R.drawable.child4);
            } else if (281 < target_n && target_n <= 350) {
                map.setImageResource(R.drawable.child5);
            } else if (351 < target_n && target_n <= 420) {
                map.setImageResource(R.drawable.child6);
            } else if (421 < target_n && target_n <= 490) {
                map.setImageResource(R.drawable.child7);
            } else if (491 < target_n && target_n <= 560) {
                map.setImageResource(R.drawable.child8);
            } else if (561 < target_n && target_n <= 700) {
                map.setImageResource(R.drawable.child9);
            } else if (701 < target_n && target_n <= 800) {
                map.setImageResource(R.drawable.child10);
            } else if (801 < target_n && target_n <= 900) {
                map.setImageResource(R.drawable.child11);
            } else {
                map.setImageResource(R.drawable.child12);
            }
        } else {
            if (target_n <= 70){
                map.setImageResource(R.drawable.preservation1);
            } else if (71 < target_n && target_n <= 140) {
                map.setImageResource(R.drawable.preservation2);
            } else if (141 < target_n && target_n <= 210) {
                map.setImageResource(R.drawable.preservation3);
            } else if (211 < target_n && target_n <= 280) {
                map.setImageResource(R.drawable.preservation4);
            } else if (281 < target_n && target_n <= 350) {
                map.setImageResource(R.drawable.preservation5);
            } else if (351 < target_n && target_n <= 420) {
                map.setImageResource(R.drawable.preservation6);
            } else if (421 < target_n && target_n <= 490) {
                map.setImageResource(R.drawable.preservation7);
            } else if (491 < target_n && target_n <= 560) {
                map.setImageResource(R.drawable.preservation8);
            } else if (561 < target_n && target_n <= 700) {
                map.setImageResource(R.drawable.preservation9);
            } else if (701 < target_n && target_n <= 800) {
                map.setImageResource(R.drawable.preservation10);
            } else if (801 < target_n && target_n <= 900) {
                map.setImageResource(R.drawable.preservation11);
            } else {
                map.setImageResource(R.drawable.preservation12);
            }
        }

        take_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InforActivity.class);
                startActivity(intent);
            }
        });

        return_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent2);
            }
        });
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
}
