import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PharmacyManagementSystem extends JFrame implements ActionListener {

    // Components for the GUI
    private JTextField medicineNameField, medicinePriceField, medicineQuantityField;
    private JButton addButton, updateButton, deleteButton, clearButton;
    private JTable medicineTable;
    private DefaultTableModel tableModel;

    public PharmacyManagementSystem() {
        // Setting up the Frame
        setTitle("Pharmacy Management System");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel for input fields and buttons
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        // Creating input fields
        inputPanel.add(new JLabel("Medicine Name:"));
        medicineNameField = new JTextField();
        inputPanel.add(medicineNameField);

        inputPanel.add(new JLabel("Price:"));
        medicinePriceField = new JTextField();
        inputPanel.add(medicinePriceField);

        inputPanel.add(new JLabel("Quantity:"));
        medicineQuantityField = new JTextField();
        inputPanel.add(medicineQuantityField);

        // Adding buttons
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear");

        addButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        clearButton.addActionListener(this);

        inputPanel.add(addButton);
        inputPanel.add(updateButton);
        inputPanel.add(deleteButton);
        inputPanel.add(clearButton);

        // Table for displaying medicine data with an additional column for Total Price
        tableModel = new DefaultTableModel(new String[]{"Medicine Name", "Price", "Quantity", "Total Price"}, 0);
        medicineTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(medicineTable);

        // Adding components to the frame
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addMedicine();
        } else if (e.getSource() == updateButton) {
            updateMedicine();
        } else if (e.getSource() == deleteButton) {
            deleteMedicine();
        } else if (e.getSource() == clearButton) {
            clearFields();
        }
    }

    // Method to add a new medicine to the table
    private void addMedicine() {
        String name = medicineNameField.getText();
        String priceText = medicinePriceField.getText();
        String quantityText = medicineQuantityField.getText();

        if (!name.isEmpty() && !priceText.isEmpty() && !quantityText.isEmpty()) {
            try {
                double price = Double.parseDouble(priceText);
                int quantity = Integer.parseInt(quantityText);
                double totalPrice = price * quantity;

                tableModel.addRow(new Object[]{name, price, quantity, totalPrice});
                clearFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for price and quantity", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to update the selected medicine in the table
    private void updateMedicine() {
        int selectedRow = medicineTable.getSelectedRow();
        if (selectedRow >= 0) {
            String name = medicineNameField.getText();
            String priceText = medicinePriceField.getText();
            String quantityText = medicineQuantityField.getText();

            if (!name.isEmpty() && !priceText.isEmpty() && !quantityText.isEmpty()) {
                try {
                    double price = Double.parseDouble(priceText);
                    int quantity = Integer.parseInt(quantityText);
                    double totalPrice = price * quantity;

                    tableModel.setValueAt(name, selectedRow, 0);
                    tableModel.setValueAt(price, selectedRow, 1);
                    tableModel.setValueAt(quantity, selectedRow, 2);
                    tableModel.setValueAt(totalPrice, selectedRow, 3);
                    clearFields();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter valid numbers for price and quantity", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to update", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to delete the selected medicine from the table
    private void deleteMedicine() {
        int selectedRow = medicineTable.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to clear input fields
    private void clearFields() {
        medicineNameField.setText("");
        medicinePriceField.setText("");
        medicineQuantityField.setText("");
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PharmacyManagementSystem frame = new PharmacyManagementSystem();
            frame.setVisible(true);
        });
    }
}