package com.nilesh.bhuswami.models;

public class Plots {

    String id,pname,pdetails,pimage,price;

    public Plots() {
    }

    public Plots(String id, String pname, String pdetails, String pimage, String price) {
        this.id = id;
        this.pname = pname;
        this.pdetails = pdetails;
        this.pimage = pimage;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPdetails() {
        return pdetails;
    }

    public void setPdetails(String pdetails) {
        this.pdetails = pdetails;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
