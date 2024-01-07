package com.isher2k1.myapplication.classes;

public class Medicine {
    private String title;
    private double cost;
    private String description;

    public Medicine() {
    }

    public Medicine(String title, double cost, String description) {
        this.title = title;
        this.cost = cost;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public double getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }
}
