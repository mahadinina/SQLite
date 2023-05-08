package com.example.sqlite;

import androidx.annotation.NonNull;

public class Customer_Model {
    private int id;
    private String name;
    private int age;
    private boolean isActive;

    public Customer_Model(int id, String name, int age, boolean isActive) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isActive = isActive;
    }

    public Customer_Model(int id) {
        this.id = id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String activityYesNo(boolean isActive){
        if (isActive){
            return "Yes";
        }
        else
            return "No";
    }

    @NonNull
    @Override
    public String toString() {
        return " Name='" + name + '\'' +
                ", Age=" + age +
                ", Active=" + activityYesNo(isActive)
                ;
    }
}
