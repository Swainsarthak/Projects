import java.sql.*;
import java.util.Scanner;

public class employee_implement implements EmployeeInterface{
Connection con;
Scanner sc=new Scanner(System.in);
    public void createEmployee(Employee new_employee) {
        try {
            con=jdbc_connection.request_connection();
            String query="insert into employee (`idEmployee`,`EmployeeName`,`salary`,`age`) values (?,?,?,?)";
            PreparedStatement pstm=con.prepareStatement(query);
            pstm.setInt(1,new_employee.getId());
            pstm.setString(2,new_employee.getName());
            pstm.setDouble(3,new_employee.getSalary());
            pstm.setInt(4,new_employee.getAge());
            int check=pstm.executeUpdate();
            if(check!=0){
                System.out.println("The new employee is added to the list.");
            }
            else {
                System.out.println("Something is wrong with the new employee details. please re check.");
            }

        }
        catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteEmployee(int id) {
        try {
            con=jdbc_connection.request_connection();
            String query="Delete from employee where idEmployee="+id;
            PreparedStatement pr=con.prepareStatement(query) ;
            int count=pr.executeUpdate();
            if(count!=0){
                System.out.println("The coloumn is successfully deleted.");
            }
            else{
                System.out.println("There is some error in the input.");
            }

        }
        catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public void showall()  {
        try {
            con=jdbc_connection.request_connection();
            String query="select * from employee";
            PreparedStatement pr=con.prepareStatement(query);
            ResultSet det=pr.executeQuery();
            while (det.next()){
                int id=det.getInt(1);
                String name=det.getString(2);
                double salary=det.getDouble(3);
                int age=det.getInt(4);
                System.out.println("I'd:" + id+" name: "+name+" age: "+age+" Salary: "+salary);
                System.out.println();
            }

        }
        catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void showEmployeeBasedOnId(int id) {
        try {
            con=jdbc_connection.request_connection();
            String query="Select * from employee where idEmployee="+id;
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(query);
            while (rs.next()){
                int ind=rs.getInt(1);
                String name=rs.getString(2);
                double salary=rs.getDouble(3);
                int age=rs.getInt(4);
                System.out.println("I'd: " + ind+" name: "+name+" age: "+age+" Salary: "+salary);
                System.out.println();
            }

        }
        catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateEmployee(int id) {
        try {
            con=jdbc_connection.request_connection();
            System.out.println("What you want to update , Name,Age,Salary:");
            System.out.println("Press no for changes. 1 name,2 age,3 salary:");
            String changes=sc.nextLine();
            String[] to_change=changes.split(",");
            for (String sh:to_change){
                if(sh.equals("1")){
                    System.out.println("enter the name:");
                    String name=sc.next();
                    String query="update employee set EmployeeName=? where idEmployee="+id;
                    PreparedStatement pr=con.prepareStatement(query);
                    pr.setString(1,name);
                    int c=pr.executeUpdate();
                    if(c!=0){
                        System.out.println("the name is updated.");
                    }
                    else {
                        System.out.println("there is some issue with the input");
                    }
                }
                else if (sh.equals("2")) {
                    System.out.println("Enter the age:");
                    int ag = sc.nextInt();
                    String query = "update employee set age=? where idEmployee=" + id;
                    PreparedStatement pr = con.prepareStatement(query);
                    pr.setInt(1, ag);
                    int c = pr.executeUpdate();
                    if (c != 0) {
                        System.out.println("the age is updated.");
                    } else {
                        System.out.println("there is some issue with the input");
                    }
                }
                else if (sh.equals("3")){
                    System.out.println("Enter the salary:");
                    double sal=sc.nextDouble();
                    String query = "update employee set Salary=? where idEmployee=" + id;
                    PreparedStatement pr = con.prepareStatement(query);
                    pr.setDouble(1, sal);
                    int c = pr.executeUpdate();
                    if (c != 0) {
                        System.out.println("the salary is updated.");
                    } else {
                        System.out.println("there is some issue with the input");
                    }
                }

            }
        }
        catch (SQLException|ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
}
