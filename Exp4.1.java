/*Experiment 4.1: Employee Management System*/
import java.util.ArrayList;
class Employee {
    private int id;
    private String name;
    private double salary;
    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Salary: " + salary;
    }
}
class EmployeeManagementSystem {
    private ArrayList<Employee> employees;
    public EmployeeManagementSystem() {
        employees = new ArrayList<>();
    }
    public void addEmployee(int id, String name, double salary) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                System.out.println("Error: Employee with ID " + id + " already exists.");
                return;
            }
        }
        employees.add(new Employee(id, name, salary));
        System.out.println("Employee Added: " + employees.get(employees.size() - 1));
    }
    public void updateEmployee(int id, double newSalary) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                emp.setSalary(newSalary);
                System.out.println("Employee ID " + id + " updated successfully.");
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
    }
    public void removeEmployee(int id) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                employees.remove(emp);
                System.out.println("Employee ID " + id + " removed successfully.");
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
    }
    public void searchEmployeeById(int id) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                System.out.println("Employee Found: " + emp);
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
    }
    public void displayAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Employee emp : employees) {
                System.out.println(emp);
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem();
        // Test cases
        ems.displayAllEmployees(); // Test Case 1
        ems.addEmployee(101, "Anish", 50000); // Test Case 2
        ems.addEmployee(102, "Bobby", 60000);
        ems.updateEmployee(101, 55000); // Test Case 3
        ems.searchEmployeeById(102); // Test Case 4
        ems.removeEmployee(101); // Test Case 5
        ems.displayAllEmployees(); // Test Case 6
        ems.addEmployee(101, "Charlie", 70000); // Test Case 7
    }
}


/*Experiment 4.1: Employee Management System
The Employee Management System is a simple Java-based application that uses ArrayList to manage employee records. The system allows users to perform the following operations:
Add Employee → Store Employee ID, Name, and Salary.
Update Employee → Modify employee details based on their ID.
Remove Employee → Delete an employee using their ID.
Search Employee → Find employees by ID or Name.
Display All Employees → Show a complete list of employees.
Test Cases
Test Case 1: Adding Employees (No Employees Initially)
Display Employees
Expected Output:
No employees found.
Test Case 2: Add Employees
Input:
Add Employee (ID=101, Name="Anish", Salary=50000)
Add Employee (ID=102, Name="Bobby", Salary=60000)
Expected Output:
Employee Added: ID=101, Name=Anish, Salary=50000
Employee Added: ID=102, Name=Bobby, Salary=60000
Test Case 3: Update Employee Salary
Input:
Update Employee (ID=101, New Salary=55000)
Expected Output:
Employee ID 101 updated successfully.
Test Case 4: Search Employee by ID
Input:
Search Employee by ID=102
Expected Output:
Employee Found: ID=102, Name=Bobby, Salary=60000
Test Case 5: Remove Employee
Input:
Remove Employee (ID=101)
Expected Output:
Employee ID 101 removed successfully.
Test Case 6: Display All Employees
Input:
Display Employees
Expected Output:
ID: 102, Name: Bobby, Salary: 60000
Test Case 7: Adding Duplicate Employee ID
Input:
Add Employee (ID=101, Name="Charlie", Salary=70000)
Expected Output:
Error: Employee with ID 101 already exists.*/

