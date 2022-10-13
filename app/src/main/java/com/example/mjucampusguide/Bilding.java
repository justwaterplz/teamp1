package com.example.mjucampusguide;

import java.util.ArrayList;

public class Bilding {
    private String Name;
    private ArrayList<FC> myFC;
    private int X, Y;
    private int B_num;
    public Bilding(){}
    public Bilding(String name, ArrayList<FC> array, int x, int y, int b_num){
        Name = name;
        myFC = array;
        X = x;
        Y = y;
        B_num = b_num;
    }

    public int getB_num() {
        return B_num;
    }

    public void setB_num(int b_num) {
        B_num = b_num;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<FC> getMyFC() {
        return myFC;
    }

    public void setMyFC(ArrayList<FC> myFC) {
        this.myFC = myFC;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }
}
