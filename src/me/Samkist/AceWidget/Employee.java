package me.Samkist.AceWidget;

public class Employee {

    double total;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public double[] getQuarters() {
        return quarters;
    }

    public void setQuarters(double[] quarters) {
        this.quarters = quarters;
    }

    private double[] quarters;

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
