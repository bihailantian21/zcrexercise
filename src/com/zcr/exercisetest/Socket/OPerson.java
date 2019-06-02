package com.zcr.exercisetest.Socket;


import java.io.Serializable;
public class OPerson implements Serializable{
    private static final long serialVersionUID = 1L;
    int age;
    String name;
    public OPerson(int age, String name) {
        super();
        this.age = age;
        this.name = name;
    }
    @Override
    public String toString() {
        return "Person [age=" + age + ", name=" + name + "]";
    }
}