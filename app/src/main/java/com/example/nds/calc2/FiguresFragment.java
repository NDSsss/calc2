package com.example.nds.calc2;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

public class FiguresFragment extends Fragment implements AdapterView.OnItemClickListener {
    @Nullable
    Figures figure;
    FiguresAdapter figuresAdapter;
    int orientation;
    double firstNumber=0,secondNumber=0;
    String operation;
    Operations operations;
    int state =0;//Состояния 0-начало ввода первого числа, 1 -ввеод первого числа
                //2-ввод второго числа

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.figures_layout,container,false);
       final GridView gridView=(GridView) view.findViewById(R.id.figuresGrid);
        orientation=((MainActivity)getActivity()).getOrientation();
        operations = new Operations();
        if(orientation== Configuration.ORIENTATION_PORTRAIT) {
            gridView.setNumColumns(4);
            figuresAdapter = new FiguresAdapter(view.getContext(), R.layout.activity_main, orientation);
            gridView.setAdapter(figuresAdapter);
        }
        else{
            gridView.setNumColumns(6);
            figuresAdapter = new FiguresAdapter(view.getContext(), R.layout.activity_main, orientation,true);
            gridView.setAdapter(figuresAdapter);
        }

        gridView.setOnItemClickListener(this);

        if(savedInstanceState!=null) {
            firstNumber = savedInstanceState.getDouble("firstNumber");
            secondNumber = savedInstanceState.getDouble("secondNumber");
            state = savedInstanceState.getInt("state");
            operation = savedInstanceState.getString("operation");
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("firstNumber",firstNumber);
        outState.putDouble("secondNumber",secondNumber);
        outState.putInt("state",state);
        outState.putString("operation",operation);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        figure=figuresAdapter.getItem(position);
        String text =((MainActivity)getActivity()).getTxtViewText();
        if(figure.type!=figure.VOID){
            switch (state){
                case 0:
                    if(figure.type==figure.FIGURE) {
                        ((MainActivity) getActivity()).setTextViewText(figure.text);
                        state=1;
                    }
                    break;
                case 1:
                    if(figure.type==figure.FIGURE)
                        ((MainActivity)getActivity()).setTextViewText(text+figure.text);
                    if(figure.type==figure.OPERATION)
                    {
                        firstNumber=Double.valueOf(text);
                        operation=figure.text;
                        ((MainActivity)getActivity()).setTextViewExtraText(text+operation);
                        ((MainActivity)getActivity()).setTextViewText("");
                        state=2;
                    }
                    break;
                case 2:
                    if(figure.type==figure.FIGURE) {
                        ((MainActivity) getActivity()).setTextViewText(text + figure.text);
                    }
                    else
                    {
                        if (!text.equals("")){
                            if(figure.text.equals("=")) {
                                secondNumber = Double.valueOf(text);
                                ((MainActivity) getActivity()).setTextViewText(String.valueOf(perfomOperation(firstNumber, secondNumber, operation)));
                                state = 3;
                            }
                            else{
                                secondNumber = Double.valueOf(text);
                                firstNumber= perfomOperation(firstNumber,secondNumber,operation);
                                ((MainActivity)getActivity()).setTextViewExtraText(String.valueOf(firstNumber));
                                ((MainActivity)getActivity()).setTextViewText("");
                                operation=figure.text;
                                state=2;
                            }
                        }
                    }
                    break;
                case 3:
                    if(figure.type==figure.FIGURE){
                        state=0;
                        ((MainActivity)getActivity()).setTextViewText("0");
                        ((MainActivity)getActivity()).setTextViewExtraText("");
                        onItemClick(parent,view,position,id);
                    }
                    else{
                        if(!figure.text.equals("=")){
                            firstNumber=Double.valueOf(text);
                            operation=figure.text;
                            ((MainActivity)getActivity()).setTextViewExtraText(text+operation);
                            ((MainActivity)getActivity()).setTextViewText("");
                            state=2;
                        }
                    }
                    break;
            }

        }

    }

    double perfomOperation(double a,double b,String operation){
        switch (operation){
            case "+":
                return operations.Sum(a,b);
            case "-":
                return operations.Sub(a,b);
            case "*":
                return operations.Mul(a,b);
            case "/":
                return operations.Div(a,b);
        }

        return 0;
    }
}
