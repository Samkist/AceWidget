package me.Samkist.AceWidget;

public class Employee {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public int[] getQuarters() {
        return quarters;
    }

    public void setQuarters(int[] quarters) {
        this.quarters = quarters;
    }

    private int[] quarters;

    public Employee(String name, int[] quarters) {
        this.name = name;
        this.quarters = quarters;
    }




}
