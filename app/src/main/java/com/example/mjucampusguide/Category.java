package com.example.mjucampusguide;

import java.util.LinkedList;

public class Category {

    private Sorting sort;
    private LinkedList<FC> FCCategory;
    private String Category_name;

    public Category(String name){
        Category_name = name;
        FCCategory.clear();
    }

    public void Add(FC fc){
        FCCategory.add(fc);
    }

    public int TotalSize(){
        return FCCategory.size();
    }
    /*함수 더 추가 예정*/
}