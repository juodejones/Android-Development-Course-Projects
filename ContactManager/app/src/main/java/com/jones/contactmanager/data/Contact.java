package com.jones.contactmanager.data;

public class Contact {

    private int id;
    private String name;
    private String ph_no;

    public Contact() {
    }

    public Contact(String name, String ph_no) {
        this.name = name;
        this.ph_no = ph_no;
    }

    public Contact(int id, String name, String ph_no) {
        this.id = id;
        this.name = name;
        this.ph_no = ph_no;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPh_no() {
        return ph_no;
    }

    public void setPh_no(String ph_no) {
        this.ph_no = ph_no;
    }
}
