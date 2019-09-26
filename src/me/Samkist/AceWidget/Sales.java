package me.Samkist.AceWidget;

public class Sales {

    //Instance variables
    private String lowNames = "", highNames = "";
    private double lowSales = 0, highSales = 0;


    //A ton of Get/Sets
    public String getLowNames() {
        return lowNames;
    }

    public void setLowNames(String lowNames) {
        this.lowNames = lowNames;
    }

    public String getHighNames() {
        return highNames;
    }

    public void setHighNames(String highNames) {
        this.highNames = highNames;
    }

    public double getLowSales() {
        return lowSales;
    }

    public void setLowSales(double lowSales) {
        this.lowSales = lowSales;
    }

    public double getHighSales() {
        return highSales;
    }

    public void setHighSales(double highSales) {
        this.highSales = highSales;
    }

    public void appendHighName(String name) {
        highNames = highNames + " " + name;
    }

    public void appendLowName(String name) {
        lowNames = lowNames + " " + name;
    }

}
