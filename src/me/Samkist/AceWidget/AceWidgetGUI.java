package me.Samkist.AceWidget;

import BreezySwing.GBFrame;

import javax.swing.*;

public class AceWidgetGUI extends GBFrame {
    private static JFrame frame = new AceWidgetGUI();
    private JButton inputButton = addButton("Input", 1, 1, 1, 1);
    private JButton calculateButton = addButton("Calculate", 1, 1, 1, 1);
    private JButton resetAllButton = addButton("Reset All", 1, 1, 1, 1);
    private JLabel employeeNameLabel = addLabel("Employee Name:", 1, 1, 1, 1);
    private JLabel quarterOneLabel = addLabel("Quarter 1:", 1, 1, 1, 1);
    private JLabel quarterTwoLabel = addLabel("Quarter 2:", 1, 1, 1, 1);
    private JLabel quarterThreeLabel = addLabel("Quarter 3:", 1, 1, 1, 1);
    private JLabel quarterFourLabel = addLabel("Quarter 4:", 1, 1, 1, 1);

    public static void main(String[] args) {
        frame.setTitle("Ace Widget Employee Sales");
        frame.setSize(800, 400);
        frame.setVisible(true);
    }
}
