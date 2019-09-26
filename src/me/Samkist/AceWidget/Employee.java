package me.Samkist.AceWidget;

public class Employee {

    //Instance variabless
    private double total;
    private String name;
    private double[] quarters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getQuarters() {
        return quarters;
    }

    public void setQuarters(double[] quarters) {
        this.quarters = quarters;
    }

    //Constructor
    public Employee(String name, double[] quarters) {
        this.name = name;
        this.quarters = quarters;
        for(double d : quarters) {
            total += d;
        }
    }

    public double getTotal() {
        return total;
    }




}
