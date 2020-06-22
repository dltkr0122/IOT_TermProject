package com.example.termproject0515;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import static java.lang.Thread.sleep;

public class LoanActivity extends AppCompatActivity {

    private Button buttonScan;
    private TextView Lnumber, Lclaim, Ltitle, Lauthor, Lpublisher, Llocation, textViewResult;

    public static String booktitle3;

    //qr code scanner object
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);
        //View Objects
        buttonScan = (Button) findViewById(R.id.buttonScan);
        Lnumber = (TextView) findViewById(R.id.loan_number);
        Lclaim = (TextView) findViewById(R.id.loan_claim);
        Ltitle = (TextView) findViewById(R.id.loan_title);
        Lauthor = (TextView) findViewById(R.id.loan_author);
        Lpublisher = (TextView) findViewById(R.id.loan_publisher);
        Llocation = (TextView) findViewById(R.id.loan_location);
        textViewResult = (TextView)  findViewById(R.id.textViewResult);

        // QR 스캔 오브젝트 선언
        qrScan = new IntentIntegrator(this);

        // 버튼 이벤트
        buttonScan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 스캔 옵션
                qrScan.setPrompt("Scanning...");
                // QR 스캐너 동작
                qrScan.initiateScan();
            }
        });
    }

    // QR 코드 스캔 결과 가져오기
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            // QR코드가 없으면
            if (result.getContents() == null) {
                Toast.makeText(LoanActivity.this, "취소!", Toast.LENGTH_SHORT).show();
            } else {
                // QR코드 결과가 있으면
                Toast.makeText(LoanActivity.this, "스캔완료!", Toast.LENGTH_SHORT).show();
                try {
                    //data를 json으로 변환
                    JSONObject obj = new JSONObject(result.getContents());
                    Lnumber.setText(obj.getString("Number"));
                    Lclaim.setText(obj.getString("Claim_sign"));
                    Ltitle.setText(obj.getString("Title"));
                    Lauthor.setText(obj.getString("Author"));
                    Lpublisher.setText(obj.getString("Publisher"));
                    Llocation.setText(obj.getString("Location"));

                    sleep(3000);

                    booktitle3 = obj.getString("Title");
                    Intent intent = new Intent(getApplicationContext(), InforActivity.class);
                    startActivity(intent);

                } catch (JSONException | InterruptedException e) {
                    e.printStackTrace();
                    textViewResult.setText(result.getContents());
                }
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}