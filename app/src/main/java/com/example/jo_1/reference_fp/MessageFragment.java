package com.example.jo_1.reference_fp;



import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jo_1.reference_fp.finalproject.CalcActivity;

//import android.support.v4.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MessageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessageFragment extends Fragment {



    private TextView tMessage;
    private TextView messageId;
    private ImageButton deleteB;

    private String message;
    private int myId;
    private long dbID;


    CalcActivity calorieCheck;
    MessageFragment thisFragment;

    LayoutInflater inflater;
    ViewGroup viewG;


    public MessageFragment() {
        // Required empty public constructor
    }

    public void setRunning(CalcActivity carbohydrates) {
        this.calorieCheck = carbohydrates;
    }

    public static MessageFragment newInstance() {
        MessageFragment myFragment = new MessageFragment();

        return myFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            message = bundle.getString("chatMsg");
            myId = bundle.getInt("Id");
            //dbID = bundle.getLong("dbId");
            Log.i("MessageFragment", message);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        thisFragment = this;
        this.inflater = inflater;
        this.viewG = container;
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exercise_detail, container, false);
        tMessage = (TextView) view.findViewById(R.id.tMessage);
        tMessage.setText(message);

        messageId = (TextView) view.findViewById(R.id.messageId);
        messageId.setText(Integer.toString(myId));

        deleteB = (ImageButton) view.findViewById(R.id.deleteB);
        deleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //inflating the layout that contains custom message
                View warningMsgView = thisFragment.inflater.inflate(R.layout.dialog_warning, viewG,false);
                TextView customText = (TextView) warningMsgView.findViewById(R.id.customMsg);

                customText.setText("The activity: \"" + message + "\" will be lost! do you want to continue");

                AlertDialog.Builder warningDiagBuilder = new AlertDialog.Builder(thisFragment.getActivity());
                warningDiagBuilder.setView(warningMsgView).setMessage(R.string.dialog);
                warningDiagBuilder.setView(warningMsgView).setPositiveButton(R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                if (calorieCheck != null) {
                                    //tablet
                                    calorieCheck.deleteMessage(myId);
                                    // once deleted the fragment is disappeared
                                    thisFragment.getFragmentManager().popBackStack();

                                } else {
                                    Log.i("tag", "hello");
                                    Intent intent = new Intent();
                                    intent.putExtra("deleteMsgId", myId);
                                    //intent.putExtra("deleteDBMsgId", dbID);
                                    getActivity().setResult(10, intent);
                                    getActivity().finish();
                                }

                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                                dialog.cancel();
                            }
                        });
                // Create the AlertDialog object and return it
                AlertDialog dialog1 = warningDiagBuilder.create();
                dialog1.show();


            }
        });
        return view;
    }
}
