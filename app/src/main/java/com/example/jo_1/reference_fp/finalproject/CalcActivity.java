package com.example.jo_1.reference_fp.finalproject;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jo_1.reference_fp.MessageDetails;
import com.example.jo_1.reference_fp.MessageFragment;
import com.example.jo_1.reference_fp.R;


import java.util.ArrayList;
import java.util.List;

public class CalcActivity extends ToolbarActivity {
    //TextView comment;
    EditText foodInfoEdit;
    //TextView time;
    EditText timeEdit;
    FrameLayout frameLayout;

  //  protected ListView foodListView;
//    protected TextView foodInfoT;

//    protected EditText timeEdit;

    //TextView displayText;

    String TAG = "CalcActivity";
    Button addB;


    String displayInfo;
    String displayTime;
    final ArrayList<String> foodArray = new ArrayList<String>();
    final ArrayList<String> timeArray = new ArrayList<String>();

    Cursor cursor;
    boolean isTab;
    int deleteId;
    long deleteBDid;
    FoodListAdapter fNAdapter;


    CalcActivity calc;
    FoodDatabaseHelper mydbase;
    ContentValues contentValues = new ContentValues();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Context context = getApplicationContext();

        frameLayout = (FrameLayout)findViewById(R.id.fragment_frame);

        //phone
        if(frameLayout == null){
            Log.i(TAG, "frame is not loaded");
            isTab = false;
        }
        //tab
        else{
            Log.i(TAG, "frame is loaded");
            isTab = true;
        }


        foodInfoEdit = (EditText)findViewById(R.id.foodInfoEdit);
        editTextOnFocusChangeListener(foodInfoEdit);

        timeEdit = (EditText)findViewById(R.id.editTextRunningTime);
        editTextOnFocusChangeListener(timeEdit);

        List<String> convertString;
        ListView list = (ListView) findViewById(R.id.msgRunningListView);
        fNAdapter = new FoodListAdapter(this);
        list.setAdapter(fNAdapter);
        addB = (Button)findViewById(R.id.addButtonRunning);
        mydbase = new FoodDatabaseHelper(context);
        mydbase.open();

        cursor = mydbase.getActivity(TAG);


        if(cursor.moveToFirst()){
            do{
                String comment = cursor.getString(cursor.getColumnIndex(mydbase.KEY_COMMENT));
                Log.i("DailyList Comment: ", comment);
                String time = cursor.getString(cursor.getColumnIndex(mydbase.KEY_ACTIVITY_TIME)).toString();
                Log.i("DailyList time: ", time);
                foodArray.add(comment);
                timeArray.add(time);
                cursor.moveToNext();
            }while(!cursor.isAfterLast());
        }


        Log.i(TAG, "Cursor's column: " + cursor.getColumnCount());
        for(int i = 0; i< cursor.getColumnCount();  i++){
            Log.i(TAG, cursor.getColumnName(i));

        }

        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayInfo = (foodInfoEdit.getText().toString());
                Log.i("DailyList Comment: ", displayInfo);
                displayTime = timeEdit.getText().toString();
                Log.i("DailyList time: ", displayTime);
                Toast.makeText(getBaseContext(),displayInfo,Toast.LENGTH_SHORT).show();

                foodArray.add(displayInfo);
                timeArray.add(displayTime);

                //map.put (displayComment,displayTime);
                //commentArray.add(map);

                fNAdapter.notifyDataSetChanged();
                foodInfoEdit.setText("");
                timeEdit.setText("");

                contentValues.put("Info",displayInfo);
                contentValues.put("Time",displayTime);
                contentValues.put("Activity",TAG.toLowerCase());
                mydbase.insert( contentValues);
                cursor = mydbase.getActivity(TAG);

            }
        });

        calc = this;

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = fNAdapter.getItem(position);
                String str = (String) o; //Default String Adapter
                Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT).show();

                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                    //Do some stuff

                }

                if(isTab){
                    // if the app is running on a tablet
                    MessageFragment fragment = new MessageFragment();
                    fragment.setRunning(calc);
                    Bundle bundle = new Bundle();
                    bundle.putString("chatMsg", str);
                    bundle.putInt("Id",position);
                    //bundle.putLong("dbId",id);
                    fragment.setArguments(bundle);

                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_frame, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }
                /* sending the activity to the newly created MessageDetails class */
                else{
                    Intent intent = new Intent(getApplicationContext(), MessageDetails.class);
                    intent.putExtra("chatMsg",str);
                    intent.putExtra("Id", position);
                    //intent.putExtra("dbId",id);
                    startActivityForResult(intent, 10);
                }

            }

        });

    }

    public void onActivityResult(int requestCode, int responseCode, Intent data){
        if(requestCode == 10  && responseCode == 10) {
            // received data from fragment to delete the message
            Bundle bundle = data.getExtras();
            deleteId = bundle.getInt("deleteMsgId");
            //deleteBDid = bundle.getLong("deleteDBMsgId");
            deleteBDid = fNAdapter.getItemId(deleteId);
            mydbase.remove(deleteBDid);
            foodArray.remove(deleteId);
            cursor = mydbase.getChatMessages();
            fNAdapter.notifyDataSetChanged();

        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Hide keyboard when focus is changed from edittext
     * @param editText
     */
    public void editTextOnFocusChangeListener(EditText editText){
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }


    public void deleteMessage(int id){
        long deleteDBIdTab = fNAdapter.getItemId(id);
        mydbase.remove(deleteDBIdTab);
        foodArray.remove(id);
        fNAdapter.notifyDataSetChanged();

    }

    public void onDestroy(){
        super.onDestroy();
        this.mydbase.close();
    }


    //public class ExerciseListAdapter extends ArrayAdapter<String, String> {

    private class FoodListAdapter extends ArrayAdapter<String> {


        public FoodListAdapter(Context c){
            super(c, 0);
        }

        @Override
        public int getCount() {
            return foodArray.size();
        }

        @Override
        public String getItem(int position) {
            return foodArray.get(position);
        }

        public String getItemTime(int position) {
            return timeArray.get(position);
        }

        @Override
        //public long getItemId(int position) {
            //return position;
        //}

        public long getItemId(int position){
            cursor.moveToPosition(position);
            int  id = mydbase.getIdFromCursor(cursor);
            return id;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = CalcActivity.this.getLayoutInflater();
            View result = view;

            result = inflater.inflate(R.layout.info_outgoing, viewGroup, false);

            TextView commentText = (TextView)result.findViewById(R.id.commentText);
            //
            if(isTab){
                commentText.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);
            }else{
                commentText.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

            }


            commentText.setText( getItem(position));
            //ImageView imgView = (ImageView)result.findViewById(R.id.commentImg);
            return result;
        }
    }
}
