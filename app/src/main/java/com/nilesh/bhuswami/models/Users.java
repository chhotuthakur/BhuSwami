package com.nilesh.bhuswami.models;

public class Users {
    String id,uname,uemail,upassw,umobile;

    public Users() {
    }

    public Users(String id,String uname, String uemail, String upassw, String umobile) {
        this.id =id;
        this.uname = uname;
        this.uemail = uemail;
        this.upassw = upassw;
        this.umobile = umobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUpassw() {
        return upassw;
    }

    public void setUpassw(String upassw) {
        this.upassw = upassw;
    }

    public String getUmobile() {
        return umobile;
    }

    public void setUmobile(String umobile) {
        this.umobile = umobile;
    }
}
