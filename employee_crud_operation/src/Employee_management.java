import java.util.Scanner;

public class Employee_management {
    public Employee_management() {
        System.out.println("Welcome to the crud operation project.");
        employee_implement emp = new employee_implement();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Enter what you want to do.");
            System.out.println("press 1 to create employee.\npress 2 to delete employee.\npress 3 to show all employee.\npress 4 to get the exact  employee.\npress 5 to upadte employee.");
            int task_no = sc.nextInt();
            ;
            switch (task_no) {
                case 0:
                    System.out.println("Thanku for using our project.");
                    System.exit(0);
                case 1:
                    Employee new_emp = new Employee();
                    System.out.println("Enter the details of the new employee.");
                    System.out.println("Enter the i'd of the employee:");
                    int id = sc.nextInt();
                    System.out.println("Enter the name:");
                    String name = sc.next();
                    System.out.println("Enter the salary of the employee:");
                    double salary = sc.nextDouble();
                    System.out.println("Enter the age of the employee:");
                    int age = sc.nextInt();
                    new_emp.setId(id);
                    new_emp.setName(name);
                    new_emp.setSalary(salary);
                    new_emp.setAge(age);
                    emp.createEmployee(new_emp);
                    break;
                case 2:
                    System.out.println("Enter the employee id wanna delete:");
                    int id_no = sc.nextInt();
                    emp.deleteEmployee(id_no);
                case 3:
                    emp.showall();
                    break;
                case 4:
                    System.out.println("Please enter the id of the employeee: ");
                    int emp_id = sc.nextInt();
                    emp.showEmployeeBasedOnId(emp_id);
                    break;
                case 5:
                    System.out.println("Enter the id of the Employe_database_management.Employee");
                    int id_to_change = sc.nextInt();
                    emp.updateEmployee(id_to_change);
                    break;
                default:
                    System.out.println("Please enter the valid no.");
            }
        }
        while (true);
    }

}
