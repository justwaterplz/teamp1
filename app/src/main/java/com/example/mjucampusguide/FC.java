package com.example.mjucampusguide;

public class FC {

    protected String Name;
    protected int Address;
    protected String Category;
    protected String Comment;

    public FC(){

    }

    public FC(String name, int address, String category){
        Name = name;
        Address = address;
        Category = category;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public FC getCS(){
        return this;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAddress() {
        return Address;
    }

    public void setAddress(int address) {
        Address = address;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

}
