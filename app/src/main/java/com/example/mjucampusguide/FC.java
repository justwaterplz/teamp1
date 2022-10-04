package com.example.mjucampusguide;

public class FC {

    protected String Name;
    protected String Address;

    public FC(){

    }

    public FC(String name){
        Name = name;
    }

    public FC(String name, String address){
        Name = name;
        Address = address;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
