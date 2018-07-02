package com.example.nds.calc2;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    TextView textView,orientationTextView,textViewExtra;
    int orientation;
    FiguresFragment figuresFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewExtra = (TextView)findViewById(R.id.txtViewExtra);
        textView=(TextView) findViewById(R.id.txtViewMain);
        orientationTextView = (TextView)findViewById(R.id.orientationTxtView);
        orientation=findOrientation();
        Log.d("myTag","MainActivityOnCreate");

        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        figuresFragment = new FiguresFragment();
        fragmentTransaction.add(R.id.container,figuresFragment).commit();
    }
    public String getTxtViewText(){
        return textView.getText().toString();
    }
    public void setTextViewText(String text){
        textView.setText(text);
    }
    public void setTextViewExtraText(String text){textViewExtra.setText(text);}
    public int findOrientation(){
        if(orientationTextView.getText().toString().equals("portrait"))
            return Configuration.ORIENTATION_PORTRAIT;
        else
            return Configuration.ORIENTATION_LANDSCAPE;
    }

    public int getOrientation(){return orientation;}

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("textViewText",getTxtViewText());
        outState.putString("textViewExtraText",textViewExtra.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setTextViewText(savedInstanceState.get("textViewText").toString());
        setTextViewExtraText(savedInstanceState.getString("textViewExtraText"));
    }
}
