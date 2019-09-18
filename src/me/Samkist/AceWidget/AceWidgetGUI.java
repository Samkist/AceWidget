package me.Samkist.AceWidget;

import BreezySwing.Format;
import BreezySwing.GBDialog;
import BreezySwing.GBFrame;

import javax.swing.*;

public class AceWidgetGUI extends GBFrame {
    private static JFrame frame = new AceWidgetGUI();
    private JButton addEmployee = addButton("Add Employee", 1, 1, 1, 1);
    private JTextArea tableHeader = addTextArea("", 1, 3, 1, 1);
    private final int COLUMNS = 50;
    private EmployeeGUI employeeGui = new EmployeeGUI(frame, this);
    String table;

    class EmployeeGUI extends GBDialog {
        Employee e;

        private JLabel employeeNameLabel = addLabel("Employee Name:", 1, 1, 1, 1);
        private JTextField employeeNameField = addTextField("", 1, 2, 1, 1);
        private JLabel quarterOneLabel = addLabel("Quarter 1:", 2, 1, 1, 1);
        private JTextField quarterOneField = addTextField("", 2, 2, 1, 1);
        private JLabel quarterTwoLabel = addLabel("Quarter 2:", 3, 1, 1, 1);
        private JTextField quarterTwoField = addTextField("", 3, 2, 1, 1);
        private JLabel quarterThreeLabel = addLabel("Quarter 3:", 4, 1, 1, 1);
        private JTextField quarterThreeField = addTextField("", 4, 2, 1, 1);
        private JLabel quarterFourLabel = addLabel("Quarter 4:", 5, 1, 1, 1);
        private JTextField quarterFourField = addTextField("", 5, 2, 1, 1);
        private JButton addEmployee = addButton("Add", 6, 1, 1, 1);
        int[] quarters;
        JTextField[] fields;
        AceWidgetGUI gui;
        public EmployeeGUI(JFrame parent, AceWidgetGUI gui) {
            super(parent);
            this.gui = gui;
            this.setTitle("Add Employee");
            this.setSize(400, 400);
            fields = new JTextField[5];
            quarters = new int[4];
            fields[0] = employeeNameField;
            fields[1] = quarterOneField;
            fields[2] = quarterTwoField;
            fields[3] = quarterThreeField;
            fields[4] = quarterFourField;
        }
        public void buttonClicked(JButton jButton) {
            if(errorCheck()) {
                messageBox("Invalid Data Set");
                for(JTextField field : fields)
                    field.setText("");
            }
            if(jButton.equals(addEmployee)) {
                e = new Employee(employeeNameField.getText(), quarters);
                gui.addEmployee(e);
            }
        }
        public JFrame getFrame() {
            return frame;
        }

        private boolean errorCheck() {
            try {
                quarters[0] = Integer.parseInt(quarterOneField.getText());
                quarters[1] = Integer.parseInt(quarterTwoField.getText());
                quarters[2] = Integer.parseInt(quarterThreeField.getText());
                quarters[3] = Integer.parseInt(quarterFourField.getText());
            } catch(Exception e) {
                return true;
            }
            return false;
        }

    }



    public static void main(String[] args) {

        frame.setTitle("Ace Widget Employee Sales");
        frame.setSize(800, 400);
        frame.setVisible(true);
    }

    public void addEmployee(Employee emp) {
        int[] quarters = emp.getQuarters();
        String name = Format.justify('l', emp.getName(), 10);
        String q1Label = Format.justify('c', quarters[0], COLUMNS);
        String q2Label = Format.justify('c', quarters[1], COLUMNS);
        String q3Label = Format.justify('c', quarters[2], COLUMNS);
        String q4Label = Format.justify('c', quarters[3], COLUMNS);
        String add = "\n" + name + q1Label + q2Label + q3Label + q4Label;
        tableHeader.append(add);
    }


    public void buttonClicked(JButton jButton) {
        if(jButton.equals(addEmployee))
            employeeGui.setVisible(true);
    }

    public AceWidgetGUI() {
        tableHeader.setEditable(false);
        String name = Format.justify('l', "Name", 10);
        String q1Label = Format.justify('c', "Q1", COLUMNS);
        String q2Label = Format.justify('c', "Q2", COLUMNS);
        String q3Label = Format.justify('c', "Q3", COLUMNS);
        String q4Label = Format.justify('c', "Q4", COLUMNS);
        String lineBreak = "————————————————————————————————————————————————————————";
        table = name+q1Label+q2Label+q3Label+q4Label + "\n" + lineBreak;
        tableHeader.setText(table);

    }

    public static JFrame getInstance() {
        return frame;
    }
}
