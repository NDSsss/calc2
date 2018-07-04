package com.example.nds.calc2;

public class Figures {
    public final int FIGURE=1;
    public final int OPERATION=2;
    public final int VOID=3;

    public int type;
    public String text;

    void setType(int type){
        this.type=type;
    }
    void setText(String text){
        this.text=text;
    }
    int getType(){
        return type;
    }
    String getText(){
        return text;
    }
    public Figures(String text,int type){
        this.text=text;
        this.type=type;
    }
}
