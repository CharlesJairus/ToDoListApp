import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// TaskPanel class extends JPanel, representing a panel for displaying tasks
public class TaskPanel extends JPanel {
    private DefaultTableModel tableModel; // Table model for managing task data
    private JTable taskTable; // Table component to display tasks
    private JComboBox<String> filterComboBox; // Combo box for filtering tasks
    private JTextField filterTextField; // Text field for searching tasks
    private List<Object[]> completedTasks; // List to store completed tasks

    // Constructor initializes the task panel layout and components
    public TaskPanel() {
        setLayout(new BorderLayout()); // Set layout for the panel
        setBorder(new EmptyBorder(5, 5, 5, 5)); // Set empty border to provide spacing
        setBackground(new Color(0x95D2B3)); // Set background color for the panel

        tableModel = createNonEditableTableModel(); // Create non-editable table model
        tableModel.addColumn("Task Name"); // Add columns to the table model
        tableModel.addColumn("Description");
        tableModel.addColumn("Due Date");
        tableModel.addColumn("Priority");
        tableModel.addColumn("Status");

        taskTable = new JTable(tableModel); // Create table with the table model
        taskTable.setBackground(new Color(0xF1F8E8)); // Set background color for the table

        add(createFilterPanel(), BorderLayout.NORTH); // Add filter panel to the top of the panel
        add(new JScrollPane(taskTable), BorderLayout.CENTER); // Add scrollable table to the center of the panel
        add(createButtonPanel(), BorderLayout.SOUTH); // Add button panel to the bottom of the panel

        completedTasks = new ArrayList<>(); // Initialize list for completed tasks
    }

    // Method to create filter panel with combo box and search field
    private JPanel createFilterPanel() {
        JPanel panel = new JPanel(); // Create new panel for filter components
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS)); // Set layout for horizontal arrangement
        panel.setBorder(new EmptyBorder(5, 5, 5, 5)); // Set empty border for spacing
        panel.setBackground(new Color(0x95D2B3)); // Set background color for the panel

        filterComboBox = new JComboBox<>(new String[]{"All", "Completed", "Not Completed"}); // Create combo box for filtering
        filterComboBox.addActionListener(e -> filterTasks()); // Add action listener to filter tasks
        JLabel filterLabel = new JLabel("Filter: "); // Create label for filter combo box
        filterLabel.setForeground(Color.BLACK); // Set text color for the label
        panel.add(filterLabel); // Add filter label to the panel
        panel.add(filterComboBox); // Add filter combo box to the panel

        filterTextField = new JTextField(20); // Create text field for searching tasks
        filterTextField.addActionListener(e -> filterTasks()); // Add action listener to filter tasks
        JLabel searchLabel = new JLabel("   Search Task Name:  "); // Create label for search text field
        searchLabel.setForeground(Color.BLACK); // Set text color for the label
        panel.add(searchLabel); // Add search label to the panel
        panel.add(filterTextField); // Add search text field to the panel

        return panel; // Return the created filter panel
    }

    // Method to create button panel with various task management buttons
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(); // Create new panel for buttons
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5)); // Set layout for right-aligned flow with padding
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Set empty border for spacing
        panel.setBackground(new Color(0x95D2B3)); // Set background color for the panel

        // Create buttons with specific labels and actions
        JButton addTaskButton = createButton("Add Task", e -> addTask());
        JButton editTaskButton = createButton("Edit Task", e -> editTask());
        JButton deleteTaskButton = createButton("Delete Task", e -> deleteTask());
        JButton completeTaskButton = createButton("Complete Task", e -> completeTask());
        JButton refreshButton = createButton("Refresh", e -> refreshTasks());

        // Add buttons to the panel
        panel.add(addTaskButton);
        panel.add(editTaskButton);
        panel.add(deleteTaskButton);
        panel.add(completeTaskButton);
        panel.add(refreshButton);

        return panel; // Return the created button panel
    }

    // Method to create a button with specified text and action listener
    private JButton createButton(String text, java.awt.event.ActionListener actionListener) {
        JButton button = new JButton(text); // Create new button with specified text
        button.setBackground(new Color(0x55AD9B)); // Set background color for the button
        button.setBorder(BorderFactory.createLineBorder(new Color(0xF1F8E8))); // Set border for the button
        button.setPreferredSize(new Dimension(120, 40)); // Set preferred size for the button
        button.setForeground(Color.BLACK); // Set text color for the button
        button.addActionListener(actionListener); // Add action listener to the button
        return button; // Return the created button
    }

    // Method to add a new task
    private void addTask() {
        TaskForm taskForm = new TaskForm(); // Create task form dialog
        int result = JOptionPane.showConfirmDialog(this, taskForm, "Add Task", JOptionPane.OK_CANCEL_OPTION); // Show dialog and get result
        if (result == JOptionPane.OK_OPTION) { // If user clicks OK
            Task task = taskForm.getTask(); // Get task object from task form
            if (task.getName().isEmpty()) { // Validate task name
                JOptionPane.showMessageDialog(this, "Task Name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE); // Show error message
                return; // Exit method
            }
            Object[] rowData = {task.getName(), task.getDescription(), task.getDueDate(), task.getPriority(), task.getStatus()}; // Create row data array
            tableModel.addRow(rowData); // Add row to the table model
        }
    }

    // Method to edit an existing task
    private void editTask() {
        int selectedRow = taskTable.getSelectedRow(); // Get selected row from the table
        if (selectedRow != -1) { // If a row is selected
            TaskForm taskForm = new TaskForm( // Create task form with data from selected row
                    tableModel.getValueAt(selectedRow, 0).toString(),
                    tableModel.getValueAt(selectedRow, 1).toString(),
                    tableModel.getValueAt(selectedRow, 2).toString(),
                    tableModel.getValueAt(selectedRow, 3).toString()
            );

            int result = JOptionPane.showConfirmDialog(this, taskForm, "Edit Task", JOptionPane.OK_CANCEL_OPTION); // Show edit dialog
            if (result == JOptionPane.OK_OPTION) { // If user clicks OK
                Task task = taskForm.getTask(); // Get task object from task form
                tableModel.setValueAt(task.getName(), selectedRow, 0); // Update table model with edited data
                tableModel.setValueAt(task.getDescription(), selectedRow, 1);
                tableModel.setValueAt(task.getDueDate(), selectedRow, 2);
                tableModel.setValueAt(task.getPriority(), selectedRow, 3);
            }
        }
    }

    // Method to delete a task
    private void deleteTask() {
        int selectedRow = taskTable.getSelectedRow(); // Get selected row from the table
        if (selectedRow != -1) { // If a row is selected
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this task?", "Confirm Deletion", JOptionPane.YES_NO_OPTION); // Confirm deletion
            if (option == JOptionPane.YES_OPTION) { // If user confirms deletion
                tableModel.removeRow(selectedRow); // Remove selected row from the table model
            }
        }
    }

    // Method to mark a task as complete
    private void completeTask() {
        int selectedRow = taskTable.getSelectedRow(); // Get selected row from the table
        if (selectedRow != -1) { // If a row is selected
            tableModel.setValueAt("Complete", selectedRow, 4); // Set status of the task to "Complete"
        }
    }

    // Method to refresh task list
    private void refreshTasks() {
        filterComboBox.setSelectedIndex(0); // Reset filter combo box
        filterTextField.setText(""); // Clear search text field
        taskTable.setModel(tableModel); // Set table model to refresh the task list
    }

    // Method to filter tasks based on filter criteria
    private void filterTasks() {
        String filterText = filterTextField.getText().toLowerCase(); // Get filter text and convert to lowercase
        String filterType = filterComboBox.getSelectedItem().toString(); // Get selected filter type

        DefaultTableModel filteredModel = new DefaultTableModel(); // Create new table model for filtered tasks
        filteredModel.setColumnIdentifiers(new String[]{"Task Name", "Description", "Due Date", "Priority", "Status"}); // Set column identifiers

        for (int i = 0; i < tableModel.getRowCount(); i++) { // Iterate through rows in the original table model
            String taskName = tableModel.getValueAt(i, 0).toString().toLowerCase(); // Get task name from the table and convert to lowercase
            String status = tableModel.getValueAt(i, 4).toString(); // Get task status from the table

            // Check filter conditions and add matching rows to the filtered model
            if ((filterType.equals("Completed") && status.equals("Complete")) ||
                    (filterType.equals("Not Completed") && !status.equals("Complete")) ||
                    (filterType.equals("All") && (filterText.isEmpty() || taskName.contains(filterText)))) {
                filteredModel.addRow(new Object[]{ // Add row to the filtered model
                        tableModel.getValueAt(i, 0),
                        tableModel.getValueAt(i, 1),
                        tableModel.getValueAt(i, 2),
                        tableModel.getValueAt(i, 3),
                        tableModel.getValueAt(i, 4)
                });
            }
        }

        taskTable.setModel(filteredModel); // Set table model to display filtered tasks
    }

    // Method to create a non-editable table model
    private DefaultTableModel createNonEditableTableModel() {
        return new DefaultTableModel() { // Create new default table model
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
    }
}
/*
Description: TaskPanel extends JPanel and serves as a container for managing tasks within the to-do list application. It includes components for filtering tasks, displaying them in a table, and performing CRUD operations on tasks.

OOP Principles:

Inheritance: Extends JPanel to create a custom panel with specialized task management features.
Encapsulation: Private fields (tableModel, taskTable, etc.) encapsulate the state and behavior related to managing tasks.
Composition: Composes Swing components (JTable, JComboBox, JTextField, JButton) to create a cohesive UI for task management.
Polymorphism: Overrides methods (createNonEditableTableModel()) to customize behavior, demonstrating polymorphic behavior.
Abstraction: Methods (addTask(), editTask(), etc.) abstract away the complexity of task management operations, providing simplified interfaces.
 */