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
    private final int COLUMNS = 50;
    private EmployeeGUI employeeGui = new EmployeeGUI(frame, this);
    private Employee[] employeeList = new Employee[10];
    private FindEmployeeGUI findEmployeeGui = new FindEmployeeGUI(frame, this, employeeList);
    private JButton findEmployee = addButton("Find Employee", 2, 2, 1, 1);
    private JButton findLowestSales = addButton("Lowest Sales", 2, 3 , 1 ,1);
    private JButton findHighestSales = addButton("Lowest Sales", 2, 3 , 1 ,1);
    private Sales sales = new Sales();

    public Employee[] getEmployeeList() {
        return employeeList;
    }

    public DecimalFormat getFormatter() {
        return formatter;
    }

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

    class FindEmployeeGUI extends GBDialog {
        AceWidgetGUI gui;
        JList employeeList = addList(1, 1 , 1, 1);
        JTextArea employeeDetails = addTextArea("", 1, 3, 2,1 );

        public FindEmployeeGUI(JFrame parent, AceWidgetGUI gui, Employee[] empArr) {
            super(parent);
            this.gui = gui;
            employeeDetails.setEditable(false);
            this.setTitle("Add Employee");
            this.setSize(400, 400);
        }

        @SuppressWarnings("unchecked")
        public void addListItem(String add) {
            DefaultListModel model = (DefaultListModel)employeeList.getModel();
            model.addElement(add);
        }

        public void updateEmployeeDetails(Employee emp) {
            double[] quarters = emp.getQuarters();
            employeeDetails.setText("" +
                    "Name: " + emp.getName() + "\n"
                    + "Q1: " + gui.getFormatter().format(quarters[0]) + "\n"
                    + "Q2: " + gui.getFormatter().format(quarters[1]) + "\n"
                    + "Q3: " + gui.getFormatter().format(quarters[2]) + "\n"
                    + "Q4: " + gui.getFormatter().format(quarters[3]) + "\n"
                    + "");
        }

        public void listItemSelected (JList listObj) {
            if(listObj.equals(employeeList)) {
                updateEmployeeDetails(gui.getEmployeeList()[listObj.getSelectedIndex()]);
            }
        }
    }

    public AceWidgetGUI() {
        tableHeader.setEditable(false);
        String name = Format.justify('l', "Name", 10);
        String q1Label = Format.justify('l', "  Q1", COLUMNS);
        String q2Label = Format.justify('l', "  Q2", COLUMNS);
        String q3Label = Format.justify('l', "  Q3", COLUMNS);
        String q4Label = Format.justify('l', "  Q4", COLUMNS);
        String totalLabel = Format.justify('l', "Total", COLUMNS);
        String lineBreak = "————————————————————————————————————————————————————————————————————";
        table = name+q1Label+q2Label+q3Label+q4Label+totalLabel + "\n" + lineBreak;
        tableHeader.setText(table);
        findEmployee.setEnabled(false);

    }

    public static void main(String[] args) {

        frame.setTitle("Ace Widget Employee Sales");
        frame.setSize(800, 400);
        frame.setVisible(true);
    }

    public void addEmployee(Employee emp) {
        for(int i = 0; i < employeeList.length; i++) {
            if(i + 1 == employeeList.length) {
                System.out.println(employeeList.length + " " + i);
                messageBox("Could not add anymore employees!");
                return;
            } else if(employeeList[i] == null) {
                employeeList[i] = emp;
                if(emp.getTotal() > sales.getHighSales()) {
                    sales.setHighNames(emp.getName());
                    sales.setHighSales(emp.getTotal());
                } else if(emp.getTotal() < sales.getLowSales()) {
                    sales.setLowNames(emp.getName());
                    sales.setLowSales(emp.getTotal());
                 } else if(emp.getTotal() == sales.getLowSales()) {
                    sales.appendLowName(emp.getName());
                } else if(emp.getTotal() == sales.getHighSales()) {
                    sales.appendHighName(emp.getName());
                }
                findEmployeeGui.addListItem(emp.getName());
                break;
            }
        }
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
        if(!findEmployee.isEnabled())
            findEmployee.setEnabled(true);
    }


    public void buttonClicked(JButton jButton) {
        if(jButton.equals(addEmployee))
            employeeGui.setVisible(true);
        if(jButton.equals(findEmployee))
            findEmployeeGui.setVisible(true);
        if(jButton.equals(findLowestSales)) {
            messageBox("The employee with the highest sales is: " + sales.getHighNames() + ": " + sales.getHighSales() );
        }
    }

    public static JFrame getInstance() {
        return frame;
    }
}
