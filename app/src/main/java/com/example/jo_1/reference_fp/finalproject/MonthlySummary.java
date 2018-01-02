package com.example.jo_1.reference_fp.finalproject;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jo_1.reference_fp.R;


public class MonthlySummary extends Fragment {

    String summaryValue;

    String  avgCalories;
    String  avgFats;
    String  avgCarbohydrates;
    String  avgCholesterol;
    String  avgProtein;

    TextView monthlySummary;
    TextView summaryContent;
    TextView avgLabel;
    TextView avgContent;
    TextView idView;

    public MonthlySummary() {
        // Required empty public constructor
    }

    public static MonthlySummary newInstance()
    {
        MonthlySummary myFragment = new MonthlySummary();

        return myFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            summaryValue = bundle.getString("summaryValue");
            avgCalories = bundle.getString("avgCalories");
            avgFats = bundle.getString("avgFats");
            avgCarbohydrates = bundle.getString("avgCarbohydrates");
            avgCholesterol = bundle.getString("avgCholesterol");
            avgProtein = bundle.getString("avgProtein");
            //dbID = bundle.getLong("dbId");
            Log.i("MonthlySummary", summaryValue);
        }
        Log.i("Monthly Summary", "I am in MonthlySummary fragment class");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_monthly_summary, container, false);
        monthlySummary = (TextView) view.findViewById(R.id.monthlySummary);

        summaryContent = (TextView)view.findViewById(R.id.summaryDate);
        summaryContent.setText(summaryValue);

        avgLabel = (TextView) view.findViewById(R.id.avgFoodLabel);
        avgContent = (TextView)view.findViewById(R.id.avgFNLabel);
        avgContent.setText(avgCalories);
        avgContent.setText(avgFats);
        avgContent.setText(avgCarbohydrates);
        avgContent.setText(avgCholesterol);
        avgContent.setText(avgProtein);


        return view;
    }
}
