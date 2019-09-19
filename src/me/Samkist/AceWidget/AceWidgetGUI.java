package me.Samkist.AceWidget;

import BreezySwing.Format;
import BreezySwing.GBDialog;
import BreezySwing.GBFrame;

import javax.swing.*;
import java.text.DecimalFormat;

public class AceWidgetGUI extends GBFrame {
    private static JFrame frame = new AceWidgetGUI();
    private JButton addEmployee = addButton("Add Employee", 2, 1, 1, 1);
    private JTextArea tableHeader = addTextArea("", 1, 1, 3, 1);
    private JList employeeList = addList(3, 1, 3, 1);
    private final int COLUMNS = 50;
    private EmployeeGUI employeeGui = new EmployeeGUI(frame, this);
    DecimalFormat formatter = new DecimalFormat("$0.00");
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
        double[] quarters;
        JTextField[] fields;
        AceWidgetGUI gui;


        public EmployeeGUI(JFrame parent, AceWidgetGUI gui) {
            super(parent);
            this.gui = gui;
            this.setTitle("Add Employee");
            this.setSize(400, 400);
            fields = new JTextField[5];
            quarters = new double[4];
            fields[0] = employeeNameField;
            fields[1] = quarterOneField;
            fields[2] = quarterTwoField;
            fields[3] = quarterThreeField;
            fields[4] = quarterFourField;
        }



        public void buttonClicked(JButton jButton) {
            if(jButton.equals(addEmployee)) {
                if(errorCheck()) {
                    messageBox("Invalid Data Set");
                    for(JTextField field : fields)
                        field.setText("");
                    employeeGui.setVisible(false);
                    return;
                }
                e = new Employee(employeeNameField.getText(), quarters);
                gui.addEmployee(e);
                for(JTextField field : fields)
                    field.setText("");
                employeeGui.setVisible(false);
            }
        }
        public JFrame getFrame() {
            return frame;
        }

        private boolean errorCheck() {
            try {
                quarters[0] = Double.parseDouble(quarterOneField.getText());
                quarters[1] = Double.parseDouble(quarterTwoField.getText());
                quarters[2] = Double.parseDouble(quarterThreeField.getText());
                quarters[3] = Double.parseDouble(quarterFourField.getText());
            } catch(Exception e) {
                return true;
            }
            return false;
        }

    }

    public AceWidgetGUI() {
        tableHeader.setEditable(false);
        String name = Format.justify('l', "Name", 10);
        String q1Label = Format.justify('c', "Q1", COLUMNS);
        String q2Label = Format.justify('c', "Q2", COLUMNS);
        String q3Label = Format.justify('c', "Q3", COLUMNS);
        String q4Label = Format.justify('c', "Q4", COLUMNS);
        String totalLabel = Format.justify('c', "Total", COLUMNS);
        String lineBreak = "————————————————————————————————————————————————————————————————————";
        table = name+q1Label+q2Label+q3Label+q4Label+totalLabel + "\n" + lineBreak;
        tableHeader.setText(table);

    }

    public static void main(String[] args) {

        frame.setTitle("Ace Widget Employee Sales");
        frame.setSize(1000, 400);
        frame.setVisible(true);
    }

    public void addEmployee(Employee emp) {
        double[] quarters = emp.getQuarters();
        double total = quarters[0] + quarters[1] + quarters[2] + quarters[3];
        String name = Format.justify('l', emp.getName(), 10);
        String q1Label = Format.justify('r', formatter.format(quarters[0]), COLUMNS - formatter.format(quarters[0]).length());
        String q2Label = Format.justify('r', formatter.format(quarters[1]), COLUMNS - formatter.format(quarters[1]).length());
        String q3Label = Format.justify('r', formatter.format(quarters[2]), COLUMNS - formatter.format(quarters[2]).length());
        String q4Label = Format.justify('r', formatter.format(quarters[3]), COLUMNS - formatter.format(quarters[3]).length());
        String totalLabel = Format.justify('r', formatter.format(total), COLUMNS - formatter.format(total).length());
        String add = "\n" + name + q1Label + q2Label + q3Label + q4Label + totalLabel;
        tableHeader.append(add);
    }


    public void buttonClicked(JButton jButton) {
        if(jButton.equals(addEmployee))
            employeeGui.setVisible(true);
    }

    public static JFrame getInstance() {
        return frame;
    }
}
