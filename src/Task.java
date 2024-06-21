import java.io.Serializable;

// Task class implements Serializable to support object serialization
public class Task implements Serializable {
    private String name; // Private member variable to store task name
    private String description; // Private member variable to store task description
    private String dueDate; // Private member variable to store task due date
    private String priority; // Private member variable to store task priority
    private String status; // Private member variable to store task status

    // Constructor to initialize a Task object with provided data
    public Task(String name, String description, String dueDate, String priority, String status) {
        this.name = name; // Initialize name
        this.description = description; // Initialize description
        this.dueDate = dueDate; // Initialize due date
        this.priority = priority; // Initialize priority
        this.status = status; // Initialize status
    }

    // Getter method to retrieve task name
    public String getName() {
        return name; // Return the task name
    }

    // Getter method to retrieve task description
    public String getDescription() {
        return description; // Return the task description
    }

    // Getter method to retrieve task due date
    public String getDueDate() {
        return dueDate; // Return the task due date
    }

    // Getter method to retrieve task priority
    public String getPriority() {
        return priority; // Return the task priority
    }

    // Getter method to retrieve task status
    public String getStatus() {
        return status; // Return the task status
    }
}
/*
Description: Task class represents a task object with properties such as name, description, due date, priority, and status. It implements Serializable to support object serialization.

OOP Principles:

Encapsulation: Private fields (name, description, etc.) encapsulate task data, providing controlled access via getter methods.
Serializable Interface: Implements Serializable for object serialization, allowing tasks to be saved to files or transmitted over networks.
Constructor: Initializes a Task object with provided data upon instantiation, demonstrating initialization through constructors.


Summary
Libraries: Java Swing (javax.swing.*), AWT (java.awt.*), java.text.ParseException, java.io.Serializable.
OOP Principles: Inheritance, Encapsulation, Composition, Polymorphism, Abstraction are applied across classes (ToDoListApp, TaskPanel, TaskForm, Task) to create a modular, maintainable, and extensible to-do list application with a graphical user interface. Each class specializes in different aspects of the application, contributing to a cohesive and user-friendly experience for managing tasks.

 */


