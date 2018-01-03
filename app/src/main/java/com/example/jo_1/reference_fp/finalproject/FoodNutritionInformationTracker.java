package com.example.jo_1.reference_fp.finalproject;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;

import com.example.jo_1.reference_fp.R;


public class FoodNutritionInformationTracker extends ToolbarActivity {
    Button totalCaloriesB;
    Button totalFatB;
    Button totalCarbohydratesB;
    Button totalCholesterolB;
    Button totalProteinB;

    DatePicker datePicker;

    String TAG = "FoodNutritionInformationTracker";
    //String sendBackMsgToOne = "Option 1 is selected";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_nutrition_information_tracker);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        datePicker = (DatePicker)findViewById(R.id.datePicker);

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "DATE button is clicked");
                Intent intent=new Intent(view.getContext(), CalcActivity.class);
                startActivity(intent);
            }
        });

        totalCaloriesB = (Button)findViewById(R.id.totalCaloriesB);

        totalCaloriesB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Total Calories button is clicked");
                Intent intent=new Intent(view.getContext(), CalcActivity.class);
                //Intent intent=new Intent(view.getContext(), runningActivity.class);
                startActivity(intent);
            }
        });


        totalFatB = (Button)findViewById(R.id.totalFatB);

        totalFatB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Total Fats button is clicked");
                Intent intent=new Intent(view.getContext(), CalcActivity.class);
                //Intent intent=new Intent(view.getContext(), runningActivity.class);
                startActivity(intent);
            }
        });

        totalCarbohydratesB = (Button)findViewById(R.id.totalCarbohydratesB);

        totalCarbohydratesB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Total Carbohydrates button is clicked");
                Intent intent=new Intent(view.getContext(), CalcActivity.class);
                //Intent intent=new Intent(view.getContext(), runningActivity.class);
                startActivity(intent);
            }
        });

        totalCholesterolB = (Button)findViewById(R.id.totalCholesterolB);

        totalCholesterolB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Total Cholesterol button is clicked");
                Intent intent=new Intent(view.getContext(), CalcActivity.class);
                //Intent intent=new Intent(view.getContext(), runningActivity.class);
                startActivity(intent);
            }
        });


        totalProteinB = (Button)findViewById(R.id.totalProteinB);

        totalProteinB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Total Calories button is clicked");
                Intent intent=new Intent(view.getContext(), CalcActivity.class);
                //Intent intent=new Intent(view.getContext(), runningActivity.class);
                startActivity(intent);
            }
        });
        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.entryTypeSummary);

        MonthlySummary fragment = new MonthlySummary();
        //fragment.setRunning(this);
        Bundle bundle = new Bundle();
        bundle.putString("summaryValue", "June 2, 2018");
        bundle.putString("avgRunning","3.8");
        //bundle.putLong("dbId",id);
        fragment.setArguments(bundle);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.entryTypeSummary, fragment);
        ft.addToBackStack(null);
        ft.commit();

    }

}
