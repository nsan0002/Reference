package com.example.jo_1.reference_fp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CommenceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commence);

        Button startCalc= (Button) findViewById(R.id.startCalc);
        startCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CommenceActivity.this, CalcActivity.class);
                startActivityForResult(i, 10);
            }

        });

        Button loginUser= (Button) findViewById(R.id.loginUser);
        loginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CommenceActivity.this, LoginPageActivity.class);
                startActivityForResult(i, 10);
            }
        });

        }



}
