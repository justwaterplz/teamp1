package com.example.mjucampusguide;

import java.io.Serializable;

public class FC implements Serializable {

    protected String Name;
    protected int Address;
    protected String Category;
    protected String Comment;

    public FC(){

    }

    public FC(String Name, int Address, String Category, String Comment){
        this.Name = Name;
        this.Address = Address;
        this.Category = Category;
        this.Comment = Comment;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getAddress() {
        return Address;
    }

    public void setAddress(int Address) {
        this.Address = Address;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

}
