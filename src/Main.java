import java.sql.*;

class DatabaseConnectivity{
    private static final String URL = "jdbc:mysql://localhost:3306/TestDB";
    private static final String user ="root";
    private static final String password = "root";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL,user,password);
    }
}



class Employee{
    private
        int id;
        String name;
        final String post = "Employee";
        String branch;
        int age;
        static int idCounter = 1;


    public

        Employee(String name, String branch, int age) {
            this.name = name;
            this.branch = branch;
            this.age = age;
            this.id = idCounter++;

        }

        String getName(){
            return name;
        }

        String getPost(){
            return post;
        }

        String getBranch(){
            return branch;
        }
        int getAge(){
            return age;
        }

}
class Admin{
    private final String Name = "admin";
    private final String email = "dilipsinghnaruka03@gmail.com";
    private final String password = "password";

    String getName(){
        return Name;
    }
    String getEmail(){
        return email;
    }
    String getPassword(){
        return password;
    }

}

class House{
    private
        int HouseNumber;
        static int idCounter = 1;
        String location;
        boolean isRented;
        final int rent = 15000;

    public
        House(String location, boolean isRented, int rent){
            this.location = location;
            this.isRented = isRented;
        }
    int getHouseNumber(){
        return HouseNumber;
    }
    String getLocation(){
        return location;
    }

    Boolean getIsRented(){
        return isRented;
    }

    int getRent(){
        return rent;
    }
}


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}


/*

1. Admin
    A. adding houses
    B. adding employee

2. Booking
    A. login to employee check --- he dont have permission to adding home
        a. book home with userDetail
    B. everyOne check remening houses with details like location, rent, BHK,





------------SQL-------------
1. home
2. Employee
3. admin
 */