package com.generalassembly.oop.intro;

public class Mankind {
    private int ID;
    private String name;
    private String address;

    public Mankind() {
    }

    public Mankind(int ID) {
        this.ID = ID;
    }

    public Mankind(int ID, String name) {
        this(ID);
        this.name = name;
    }

    public Mankind(int ID, String name, String address) {
        this(ID, name);
        this.address = address;
    }

    public static void main(String[] args) {
        Mankind johnSmith = new Mankind();
        System.out.println(johnSmith);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
