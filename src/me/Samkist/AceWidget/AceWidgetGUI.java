package me.Samkist.AceWidget;

import BreezySwing.Format;
import BreezySwing.GBDialog;
import BreezySwing.GBFrame;

import javax.swing.*;
import java.text.DecimalFormat;

public class AceWidgetGUI extends GBFrame {

    //Instance Variables
    private static JFrame frame = new AceWidgetGUI();
    private JButton addEmployee = addButton("Add Employee", 2, 1, 1, 1);
    private JTextArea tableHeader = addTextArea("", 1, 1, 4, 1);
    private final int COLUMNS = 50;
    private EmployeeGUI employeeGui = new EmployeeGUI(frame, this);
    private Employee[] employeeList = new Employee[10];
    private FindEmployeeGUI findEmployeeGui = new FindEmployeeGUI(frame, this, employeeList);
    private JButton findEmployee = addButton("Find Employee", 2, 2, 1, 1);
    private JButton findLowestSales = addButton("Lowest Sales", 2, 3 , 1 ,1);
    private JButton findHighestSales = addButton("Highest Sales", 2, 4 , 1 ,1);
    private Sales sales = new Sales();
    private DecimalFormat formatter = new DecimalFormat("$0.00");
    private String table;

    public Employee[] getEmployeeList() { //Returns the list object of employees
        return employeeList;
    }

    public DecimalFormat getFormatter() { //Utility method for grabbing the dollar formatter
        return formatter;
    }

    class EmployeeGUI extends GBDialog {
        private Employee e;
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
        private double[] quarters;
        private JTextField[] fields;
        private AceWidgetGUI gui;


        public EmployeeGUI(JFrame parent/*Necessary for dialogue instantiation*/, AceWidgetGUI gui/*Points to main GUI*/) {
            super(parent);
            this.gui = gui;
            this.setTitle("Add Employee");
            this.setSize(400, 400);
            fields = new JTextField[5];
            quarters = new double[4];
            //List of fields, just for large operations on all felds
            fields[0] = employeeNameField;
            fields[1] = quarterOneField;
            fields[2] = quarterTwoField;
            fields[3] = quarterThreeField;
            fields[4] = quarterFourField;
        }


        /*Listener for the add employee button, will take the data and submit it to
        the main GUI class::addEmployee
        */
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
        //Getter method for this frame
        public JFrame getFrame() {
            return frame;
        }

        //Just a quick error check on some data
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
    //Employee listing GUI class
    class FindEmployeeGUI extends GBDialog {
        //Instance Variables
        private AceWidgetGUI gui;
        private JList employeeList = addList(1, 1 , 1, 1);
        private JTextArea employeeDetails = addTextArea("", 1, 3, 2,1 );

        //Instantiates the object same as the other nested class
        public FindEmployeeGUI(JFrame parent, AceWidgetGUI gui, Employee[] empArr) {
            super(parent);
            this.gui = gui;
            employeeDetails.setEditable(false);
            this.setTitle("Add Employee");
            this.setSize(400, 400);
        }

        //Adds an element to the list with the name of the employee, if clicked it sends the index to the array grabbing the info
        @SuppressWarnings("unchecked")
        public void addListItem(String add) {
            DefaultListModel model = (DefaultListModel)employeeList.getModel();
            model.addElement(add);
        }

        //Will set the locked text area to the employee selected
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

        //Listens for list object selections same as buttonClicked
        public void listItemSelected (JList listObj) {
            if(listObj.equals(employeeList)) {
                updateEmployeeDetails(gui.getEmployeeList()[listObj.getSelectedIndex()]);
            }
        }
    }

    //Constructor for main class, sets up main table and sets buttons disabled until further notice
    public AceWidgetGUI() {
        tableHeader.setEditable(false);
        String name = Format.justify('l', "Name", 10);
        String q1Label = Format.justify('c', "  Q1", COLUMNS);
        String q2Label = Format.justify('c', "  Q2", COLUMNS);
        String q3Label = Format.justify('c', "  Q3", COLUMNS);
        String q4Label = Format.justify('c', "  Q4", COLUMNS);
        String totalLabel = Format.justify('c', "Total", COLUMNS);
        String lineBreak = "————————————————————————————————————————————————————————————————————";
        table = name+q1Label+q2Label+q3Label+q4Label+totalLabel + "\n" + lineBreak;
        tableHeader.setText(table);
        findEmployee.setEnabled(false);
        findHighestSales.setEnabled(false);
        findLowestSales.setEnabled(false);

    }

    //Main, doesn't do much except start the program
    public static void main(String[] args) {
        frame.setTitle("Ace Widget Employee Sales");
        frame.setSize(800, 400);
        frame.setVisible(true);
    }

    //This is the method called from the EmployeeGUI, the data is taken from there and sent to this method for processing
    public void addEmployee(Employee emp) {
        for(int i = 0; i < employeeList.length; i++) {
            if(i + 1 == employeeList.length) {
                System.out.println(employeeList.length + " " + i);
                messageBox("Could not add anymore employees!");
                return;
            } else if(employeeList[i] == null) { //This else if bracket handles the sales for all the employees
                employeeList[i] = emp;
                if(sales.getLowSales() == 0) {
                    sales.setLowSales(emp.getTotal());
                    sales.setLowNames(emp.getName());
                }
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
        //Sets up local employee quarters array
        double[] quarters = emp.getQuarters();
        double total = quarters[0] + quarters[1] + quarters[2] + quarters[3];
        //Formats employee data
        String name = Format.justify('l', emp.getName(), 10);
        String q1Label = Format.justify('c', formatter.format(quarters[0]), COLUMNS);
        String q2Label = Format.justify('c', formatter.format(quarters[1]), COLUMNS);
        String q3Label = Format.justify('c', formatter.format(quarters[2]), COLUMNS);
        String q4Label = Format.justify('c', formatter.format(quarters[3]), COLUMNS);
        String totalLabel = Format.justify('c', formatter.format(total), COLUMNS);
        String add = "\n" + name + q1Label + q2Label + q3Label + q4Label + totalLabel;
        tableHeader.append(add);
        //Enables disabled buttons
        if(!findEmployee.isEnabled())
            findEmployee.setEnabled(true);
        if(!findLowestSales.isEnabled())
            findLowestSales.setEnabled(true);
        if(!findHighestSales.isEnabled())
            findHighestSales.setEnabled(true);
    }

    //Button event listener
    public void buttonClicked(JButton jButton) {
        if(jButton.equals(addEmployee))
            employeeGui.setVisible(true);
        if(jButton.equals(findEmployee))
            findEmployeeGui.setVisible(true);
        if(jButton.equals(findHighestSales))
            messageBox("The employee with the highest sales is: " + sales.getHighNames() + ": " + sales.getHighSales());
        if(jButton.equals(findLowestSales))
            messageBox("The employee with the lowest sales is: " + sales.getLowNames() + ": " + sales.getLowSales());
    }

    //Getter method
    public static JFrame getInstance() {
        return frame;
    }
}
