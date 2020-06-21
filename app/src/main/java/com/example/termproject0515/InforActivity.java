package com.example.termproject0515;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class InforActivity extends AppCompatActivity {

    TextView info_title, info_author, info_publisher, info_location, info_number, info_claim, info_state;
    Button take_btn, find_btn;

    private SearchAdapter SAdapter;
    public static String booktitle2;

    myDBHelper myHelper;
    SQLiteDatabase sqlDB;

    public InforActivity(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);
        setTitle("도서 상세 정보");

        booktitle2 = SAdapter.booktitle;

        info_title = (TextView) findViewById(R.id.info_title);
        info_author = (TextView) findViewById(R.id.info_author);
        info_publisher = (TextView) findViewById(R.id.info_publisher);
        info_location = (TextView) findViewById(R.id.info_location);
        info_number = (TextView) findViewById(R.id.info_number);
        info_claim = (TextView) findViewById(R.id.info_claim);
        info_state = (TextView) findViewById(R.id.info_state);

        info_title.setText(booktitle2);

        myHelper = new myDBHelper(this);


        take_btn = (Button) findViewById(R.id.take_button);

        take_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info_state.getText().equals("대출 불가")){
                    Toast.makeText(InforActivity.this, "대출을 할 수 없습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(InforActivity.this, "대출 완료", Toast.LENGTH_SHORT).show();
                    sqlDB = myHelper.getWritableDatabase();
                    sqlDB.execSQL("UPDATE infoTBL SET state = 0 WHERE Title = '"+ booktitle2 +"';");
                    sqlDB.close();
                    info_state.setText("대출 불가");
                    info_state.setTextColor(Color.parseColor("#ff0000"));
                }
            }
        });

        find_btn = (Button) findViewById(R.id.find_button);

        find_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LocationActivity.class);
                startActivity(intent);
            }
        });

        sqlDB = myHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT * FROM infoTBL WHERE Title = '"+ booktitle2 +"';", null);

        while (cursor.moveToNext()) {
            String get_number = cursor.getString(0);
            String get_claim = cursor.getString(1);
            String get_title = cursor.getString(2);
            String get_author = cursor.getString(3);
            String get_publisher = cursor.getString(4);
            String get_location = cursor.getString(6);
            int get_state = cursor.getInt(7);

            info_number.setText(get_number);
            info_claim.setText(get_claim);
            info_title.setText(get_title);
            info_author.setText(get_author);
            info_publisher.setText(get_publisher);
            info_location.setText(get_location);
            if(get_state == 1){
                info_state.setText("대출 가능");
                info_state.setTextColor(Color.parseColor("#00cc00"));
            } else {
                info_state.setText("대출 불가");
                info_state.setTextColor(Color.parseColor("#ff0000"));
            }
        }

        cursor.close();
        sqlDB.close();


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
