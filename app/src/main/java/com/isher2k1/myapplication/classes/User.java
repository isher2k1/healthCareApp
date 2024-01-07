package com.isher2k1.myapplication.classes;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String uid, email, password;
    private String firstName, lastName, address, city;
    private String gender, goal, activity;
    private double weight, height;
    private int age;
    private ArrayList<String> orders = new ArrayList<>();

    public User() {
    }

//    public User(String id, String email, String password) {
//        this.uid = id;
//        this.email = email;
//        this.password = password;
//    }

    public User(String uid, String email, String password, String firstName,
                String lastName, String address, String city, String gender,
                String goal, String activity, double weight, double height, int age) {
        this.uid = uid;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.gender = gender;
        this.goal = goal;
        this.activity = activity;
        this.weight = weight;
        this.height = height;
        this.age = age;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String id) {
        this.uid = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", getUid());
        result.put("email", getEmail());
        result.put("password", getPassword());
        result.put("firstName", firstName);
        result.put("lastName", lastName);
        result.put("address", address);
        result.put("city", city);
        result.put("gender", gender);
        result.put("goal", goal);
        result.put("activity", activity);
        result.put("height", height);
        result.put("weight", weight);
        result.put("age", age);

        return result;
    }

}
