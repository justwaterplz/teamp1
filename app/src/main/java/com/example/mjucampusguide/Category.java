package com.example.mjucampusguide;

import androidx.annotation.NonNull;

import java.util.LinkedList;
import java.util.Iterator;

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

    public void CategorySort(int[] data){
        LinkedList<FC> SortExample = new LinkedList<>();
        for(int i=0;i<data.length-1;i++){
            Iterator<FC> exam = FCCategory.iterator();
            while(exam.hasNext()){
                FC l = exam.next();
                if(l.getAddress()==data[i]){
                    SortExample.add(l);
                    break;
                }
            }
        }
        SortExample = FCCategory;
    }
}