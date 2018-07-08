package com.ahmedkhaled.bmicalculator.model;

/**
 * Created by Ahmed Khaled on 9/27/2017.
 */

public class history {

    public int id;
    public String weight;
    public String height;
    public String result;
    String date;

    public history(int id, String weight, String height, String result, String date) {
        this.id = id;
        this.weight = weight;
        this.height = height;
        this.result = result;
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public String getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
    }

    public String getResult() {
        return result;
    }

    public String getDate() {
        return date;
    }
}
