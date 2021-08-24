package com.example.myrecyclerview;

public class myCities {

    private String name;
    private String community;
    private String icon;

    public myCities(String name, String community, String icon) {
        this.name = name;
        this.community = community;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public void add(myCities myCities) {
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}







