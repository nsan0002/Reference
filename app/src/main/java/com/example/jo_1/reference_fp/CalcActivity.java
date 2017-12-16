package com.example.jo_1.reference_fp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jo_1.reference_fp.finalproject.FoodDatabaseHelper;

import java.util.ArrayList;

public class CalcActivity extends Activity {


    protected ListView foodListView;
    protected TextView foodInfoT;
    protected EditText foodInfoEdit;
    protected  TextView timeText;
    protected EditText timeEdit;
    protected Button addB;

    protected String TAG = "Nutrition";
    protected String displayInfo;
    protected String displayTime;

    final ArrayList<String> foodArray = new ArrayList<String>();
    final ArrayList<String> timeArray = new ArrayList<String>();

    protected SQLiteDatabase activityDB;
    protected Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);


        Context context = getApplicationContext();
        foodInfoEdit = (EditText) findViewById(R.id.foodInfoEdit);
        foodInfoT = (TextView) findViewById(R.id.foodInfoT);
        timeText = (TextView) findViewById(R.id.timeText);
        timeEdit = (EditText) findViewById(R.id.timeEdit);

        foodListView = (ListView) findViewById(R.id.foodListView);
        final commentAdapter commentsAdapter = new commentAdapter(this);
        foodListView.setAdapter(commentsAdapter);

        addB = (Button) findViewById(R.id.addB);

        final FoodDatabaseHelper FoodDatabaseHelper = new FoodDatabaseHelper(context);
        activityDB = FoodDatabaseHelper.getWritableDatabase();

        final ContentValues contentValues = new ContentValues();

        //cursor = commentDatabaseHelper.getActivity(activityDB);
        cursor = FoodDatabaseHelper.getActivity(activityDB, TAG);

        if (cursor.moveToFirst()) {
            do {
                String displayInfo = cursor.getString(cursor.getColumnIndex(FoodDatabaseHelper.KEY_COMMENT));
                Log.i("Nutrition Information: ", displayInfo);
                String timeInfo = cursor.getString(cursor.getColumnIndex(FoodDatabaseHelper.KEY_ACTIVITY_TIME)).toString();
                Log.i("Time Details: ", timeInfo);
                foodArray.add(displayInfo);
                timeArray.add(timeInfo);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }


        Log.i(TAG, "Cursor's column: " + cursor.getColumnCount());
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            Log.i(TAG, cursor.getColumnName(i));

        }

        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayInfo = (foodInfoT.getText().toString());
                Log.i("Running Comment: ", displayInfo);
                displayTime = timeText.getText().toString();
                Log.i("Running time: ", displayTime);
                Toast.makeText(getBaseContext(), displayInfo, Toast.LENGTH_SHORT).show();

                foodArray.add(displayInfo);
                timeArray.add(displayTime);


                //map.put (displayComment,displayTime);
                //commentArray.add(map);


                commentsAdapter.notifyDataSetChanged();
                foodInfoT.setText("");
                timeText.setText("");

                contentValues.put("Comment", displayInfo);
                contentValues.put("Time", displayTime);
                contentValues.put("Activity", TAG.toLowerCase());
                activityDB.insert(FoodDatabaseHelper.TABLE_NAME, null, contentValues);
                cursor = FoodDatabaseHelper.getActivity(activityDB, TAG);

            }
        });

    }


    public void onDestroy() {
        super.onDestroy();
        activityDB.close();
    }


    //public class commentAdapter extends ArrayAdapter<String, String> {

    public class commentAdapter extends ArrayAdapter<String> {


        public commentAdapter(Context c) {
            super(c, 0);
        }

        @Override
        public int getCount() {
            return
                    foodArray.size();
        }

        @Override
        public String getItem(int position) {
            return foodArray.get(position);
        }

        public String getItemTime(int position) {
            return timeArray.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = CalcActivity.this.getLayoutInflater();
            View result = view;

            if (position % 2 == 0) {
                result = inflater.inflate(R.layout.info_incoming, viewGroup, false);
            } else {
                result = inflater.inflate(R.layout.info_outgoing, viewGroup, false);
            }


            foodInfoT.setText(getItem(position));
            timeText.setText(getItemTime(position));
            return result;
        }
    }
}


