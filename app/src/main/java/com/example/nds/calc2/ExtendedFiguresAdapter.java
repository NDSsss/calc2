package com.example.nds.calc2;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ExtendedFiguresAdapter extends ArrayAdapter {
    private static final String[] mContacts = { "0", "1", "2",
            "3", "4", "5", "6", "7", "8",
            "9","." };
    private static final Figures[] mContacts2={new Figures("1",1),new Figures("2",1),new Figures("3",1),
            new Figures("+",2),new Figures("4",1),new Figures("5",1), new Figures("6",1),
            new Figures("-",2),new Figures("7",1), new Figures("8",1),new Figures("9",1),
            new Figures("/",2),new Figures("0",1),new Figures(".",1),new Figures("*",2),
            new Figures("=",2),};
    private static final Figures[] mContacts3={new Figures("",3),new Figures("",3),new Figures("",3),
            new Figures("1",1),new Figures("2",1),new Figures("3",1),
            new Figures("+",2),new Figures("4",1),new Figures("5",1), new Figures("6",1),
            new Figures("-",2),new Figures("7",1), new Figures("8",1),new Figures("9",1),
            new Figures("/",2),new Figures("0",1),new Figures(".",1),new Figures("*",2),
            new Figures("=",2),};
    int orientation;

    Context mContext;

    // Конструктор
    public ExtendedFiguresAdapter(Context context, int textViewResourceId,int orientation) {
        super(context, textViewResourceId, mContacts2);
        // TODO Auto-generated constructor stub
        this.orientation=orientation;
        this.mContext = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        TextView label = (TextView) convertView;

        if (convertView == null) {
            convertView = new TextView(mContext);
            label = (TextView) convertView;
        }
        label.setTextColor(Color.BLUE);
        if(orientation==1)
            label.setTextSize(97);
        else
            label.setTextSize(50);
        label.setText(mContacts2[position].text);
        return (convertView);
    }

    // возвращает содержимое выделенного элемента списка
    public Figures getItem(int position) {
        return mContacts2[position];
    }

}