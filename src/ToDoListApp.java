import javax.swing.*;
import java.awt.*;

// Define a class ToDoListApp which extends JFrame, making it a JFrame-based application
public class ToDoListApp extends JFrame {

    // Constructor for ToDoListApp class
    public ToDoListApp() {
        // Setting the title of the JFrame
        setTitle("To-Do List");

        // Setting the size of the JFrame
        setSize(800, 600);

        // Setting the default close operation to exit the application when the JFrame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Centering the JFrame on the screen
        setLocationRelativeTo(null);

        // Creating an instance of TaskPanel, which extends JPanel and encapsulates UI for tasks
        TaskPanel taskPanel = new TaskPanel();

        // Adding the taskPanel instance to the JFrame (ToDoListApp), which is a container
        add(taskPanel);

        // Setting the JFrame (ToDoListApp) to be visible
        setVisible(true);
    }

    // Main method to start the application
    public static void main(String[] args) {
        // Invoking the ToDoListApp constructor on the event dispatch thread using SwingUtilities.invokeLater
        SwingUtilities.invokeLater(ToDoListApp::new);
    }
}


/*
libraries:
javax.swing.*: This package provides classes for creating GUI components in Java Swing, such as JFrame, JPanel, JButton, JLabel, JTextField, JComboBox, JTable, etc. These components are crucial for building interactive graphical user interfaces.

java.awt.*: This package contains classes for creating and managing graphical user interface components and for layout management in AWT, which Swing components also utilize.

java.text.ParseException: This exception class is used to handle parsing-related errors when working with formatted text fields, particularly useful in GUI applications where user input needs to conform to specific formats.

java.io.Serializable: This interface marks classes that can be serialized. It allows instances of the class to be converted into a format that can be stored, transmitted, or reconstructed later.



Description: ToDoListApp extends JFrame, serving as the main application window for a to-do list application. It instantiates a TaskPanel and adds it to the frame.

OOP Principles:

Inheritance: Extends JFrame to inherit and customize its behavior.
Composition: Composes a TaskPanel instance within the frame to encapsulate task management functionality.
Encapsulation: Encapsulates the UI and behavior related to managing the to-do list within the ToDoListApp class.

 */


