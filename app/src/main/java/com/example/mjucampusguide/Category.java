package com.example.mjucampusguide;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Category implements Serializable {

    private ArrayList<FC> FCCategory;
    private String Category_name;

    public Category(String name){
        Category_name = name;
        FCCategory = new ArrayList<>();
        FCCategory.clear();
    }

    public void Add(FC fc){
        FCCategory.add(fc);
    }

    public int TotalSize(){
        return FCCategory.size();
    }

    public void CategorySort(int[] data){
        ArrayList<FC> SortExample = new ArrayList<>();
        for(int i=0;i< 14;i++){
            Iterator<FC> exam = FCCategory.iterator();
            while(exam.hasNext()){
                FC l = exam.next();
                if(l.getAddress()==data[i]){
                    SortExample.add(l);
                    break;
                }
            }
        }
        FCCategory = SortExample;
    }

    public ArrayList<FC> getList(){
        return this.FCCategory;
    }
}