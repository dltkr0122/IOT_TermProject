package com.example.termproject0515;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button search_btn, return_btn, loan_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("책 어디에 있나?");

        search_btn = (Button)findViewById(R.id.search_button);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        return_btn = (Button)findViewById(R.id.return_button);

        return_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intetn1 = new Intent(getApplicationContext(), StateActivity.class);
                startActivity(intetn1);
            }
        });

        loan_btn = (Button) findViewById(R.id.loan_button);

        loan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intetn2 = new Intent(getApplicationContext(), LoanActivity.class);
                startActivity(intetn2);
            }
        });


    }
}
