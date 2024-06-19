import java.sql.*;

class DatabaseConnectivity{
    private static final String URL = "jdbc:mysql://localhost:3306/TestDB";
    private static final String user ="root";
    private static final String password = "root";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL,user,password);
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
    A. login to employee check --- he is dont have permission to adding home
        a. book home with userDetail
        
    B. everyOne check remening houses

 */