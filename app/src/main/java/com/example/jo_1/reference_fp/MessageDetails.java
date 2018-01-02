package com.example.jo_1.reference_fp;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MessageDetails extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);

        String message = getIntent().getExtras().getString("chatMsg");
        int id = getIntent().getExtras().getInt("Id");

        MessageFragment mf = new MessageFragment();

        Bundle bundle = new Bundle();
        bundle.putString("chatMsg", message);
        bundle.putInt("Id",id);

        mf.setArguments(bundle);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame, mf);
        ft.commit();


    }
}

