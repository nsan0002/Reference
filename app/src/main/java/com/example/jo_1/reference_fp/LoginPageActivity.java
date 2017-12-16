package com.example.jo_1.reference_fp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginPageActivity extends Activity {


    protected static final String ACTIVITY_NAME = "LoginPageActivity";
    protected String defaultEmail = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        final SharedPreferences email = getSharedPreferences("AnyFileName", MODE_PRIVATE);
        final EditText editT = (EditText) findViewById(R.id.loginEdit);

        String address = email.getString("DefaultEmail",defaultEmail);
        editT.setText(address);

      Button userSignIn = (Button) findViewById(R.id.userSignIn);
        email.getString("DefaultEmail",defaultEmail);
        userSignIn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                String inputEmail = editT.getEditableText().toString();
                SharedPreferences.Editor writer = email.edit();
                writer.putString("DefaultEmail",inputEmail);
                writer.commit();

                Intent intent = new Intent(LoginPageActivity.this, CalcActivity.class);
                startActivity(intent);

            }
        });

        email.getString("DefaultEmail", "nsan0002@algonquinlive.com");
    }

    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }


    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }


}
