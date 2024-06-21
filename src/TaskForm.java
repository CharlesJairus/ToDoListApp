import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

// TaskForm class extends JPanel, encapsulating a form for task input
public class TaskForm extends JPanel {
    // Private instance variables for form components
    private JTextField nameField;
    private JTextField descriptionField;
    private JFormattedTextField dueDateField;
    private JComboBox<String> priorityComboBox;

    // Default constructor initializes the form with current date and low priority
    public TaskForm() {
        this("", "", java.time.LocalDate.now().toString(), "Low"); // Call parameterized constructor with default values
    }

    // Parameterized constructor to set initial values for the form components
    public TaskForm(String name, String description, String dueDate, String priority) {
        setLayout(new GridLayout(0, 2)); // Set layout for the JPanel
        setBackground(new Color(0xECEFF1)); // Set background color

        // Add task name field
        JLabel nameLabel = new JLabel("Task Name:"); // Create a label for task name
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font for the label
        nameLabel.setForeground(new Color(0x424242)); // Set text color for the label
        add(nameLabel); // Add label to the panel
        nameField = new JTextField(name); // Create text field for task name with initial value
        nameField.setBackground(new Color(0xFFFFFF)); // Set background color for text field
        add(nameField); // Add text field to the panel

        // Add description field
        JLabel descriptionLabel = new JLabel("Description:"); // Create a label for description
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font for the label
        descriptionLabel.setForeground(new Color(0x424242)); // Set text color for the label
        add(descriptionLabel); // Add label to the panel
        descriptionField = new JTextField(description); // Create text field for description with initial value
        descriptionField.setBackground(new Color(0xFFFFFF)); // Set background color for text field
        add(descriptionField); // Add text field to the panel

        // Add due date field with formatted text using MaskFormatter
        JLabel dueDateLabel = new JLabel("Due Date (YYYY-MM-DD):"); // Create a label for due date
        dueDateLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font for the label
        dueDateLabel.setForeground(new Color(0x424242)); // Set text color for the label
        add(dueDateLabel); // Add label to the panel
        try {
            MaskFormatter dateMask = new MaskFormatter("####-##-##"); // Create a mask formatter for date input
            dateMask.setPlaceholderCharacter('_'); // Set placeholder character for the mask
            dueDateField = new JFormattedTextField(dateMask); // Create formatted text field with the mask
            dueDateField.setText(dueDate); // Set initial text for the field
            dueDateField.setBackground(new Color(0xFFFFFF)); // Set background color for text field
            add(dueDateField); // Add formatted text field to the panel
        } catch (ParseException e) {
            e.printStackTrace(); // Print stack trace if there's a parsing exception
        }

        // Add priority selection dropdown
        JLabel priorityLabel = new JLabel("Priority:"); // Create a label for priority
        priorityLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font for the label
        priorityLabel.setForeground(new Color(0x424242)); // Set text color for the label
        add(priorityLabel); // Add label to the panel
        priorityComboBox = new JComboBox<>(new String[]{"Low", "Medium", "High"}); // Create combo box with priority options
        priorityComboBox.setSelectedItem(priority); // Set selected item based on parameter
        priorityComboBox.setBackground(new Color(0xFFFFFF)); // Set background color for combo box
        add(priorityComboBox); // Add combo box to the panel
    }

    // Method to retrieve a Task object based on user input from the form
    public Task getTask() {
        return new Task(
                nameField.getText(), // Get text from name field
                descriptionField.getText(), // Get text from description field
                dueDateField.getText(), // Get text from due date field
                (String) priorityComboBox.getSelectedItem(), // Get selected priority from combo box
                "Not Complete" // Default status for the task
        );
    }
}
/*
Description: TaskForm extends JPanel and provides a form interface for adding or editing tasks. It includes fields for task name, description, due date, and priority, allowing users to input and modify task details.

OOP Principles:

Inheritance: Extends JPanel to create a custom form panel for task input.
Encapsulation: Private fields (nameField, descriptionField, etc.) encapsulate form state and behavior.
Composition: Composes Swing components (JTextField, JComboBox, etc.) to create an interactive form interface.
Abstraction: Provides a method (getTask()) to abstract the retrieval of task data from form fields.
Polymorphism: Utilizes polymorphism through event handling and layout management within the form panel.
 */