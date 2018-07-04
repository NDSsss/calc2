package com.example.nds.calc2;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nds.calc2.Manager.FiguresArray;

public class FiguresAdapter extends ArrayAdapter{
    private Figures[] figures;
    int orientation;

    Context mContext;

    // Конструктор
    public FiguresAdapter(Context context, int textViewResourceId,int orientation,Figures[] figures) {
        super(context, textViewResourceId, figures);
        // TODO Auto-generated constructor stub
        this.orientation=orientation;
        this.mContext = context;
        this.figures=figures;
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
        label.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        if(orientation==1) {
            label.setTextSize(TypedValue.COMPLEX_UNIT_SP,90);
        }
        else {
            label.setTextSize(TypedValue.COMPLEX_UNIT_SP,47);
        }
        label.setText(figures[position].text);


        return (convertView);
    }

    // возвращает содержимое выделенного элемента списка
    public Figures getItem(int position) {

            return figures[position];
    }

}
