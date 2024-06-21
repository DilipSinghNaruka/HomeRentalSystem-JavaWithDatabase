import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

class DatabaseConnectivity{
    private static final String URL = "jdbc:mysql://localhost:3306/homerental";
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
        int getId(){
            return id;
        }

}
// Initialize admin data type that contain
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

// Initialize House data type that contain
class House{
    private
        int HouseNumber =0;
        static int idCounter = 0;
        String location;
        boolean isRented ;
        final int rent = 15000;

    public
        House(String location, boolean isRented){
            this.location = location;
            this.isRented = isRented;
            this.HouseNumber = idCounter++;
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

// adding and see Employee by Admin
class StoreEmployee{
    void insertData(Employee emp) throws SQLException {
        String InsertQuary = "INSERT INTO employee (name,post, branch, age)VALUES(?,?,?,?);";
        try(Connection con = DatabaseConnectivity.getConnection();
            PreparedStatement pstmt = con.prepareStatement(InsertQuary)){
//            pstmt.setInt(1, emp.getId());
            pstmt.setString(1,emp.getName());
            pstmt.setString(2, emp.getPost());
            pstmt.setString(3,emp.getBranch());
            pstmt.setInt(4,emp.getAge());
            pstmt.executeUpdate();
        }


    }

    List<Employee> displayData() throws SQLException{
        List<Employee> EmpDatas = new ArrayList<>();
        String DisplayQuery = "Select * from employee;";
        Connection con = DatabaseConnectivity.getConnection();
        Statement st =  con.createStatement();
        ResultSet rs = st.executeQuery(DisplayQuery);
        while(rs.next()){
            String name = rs.getString("name");
            String post = rs.getString("post");
            String branch = rs.getString("branch");
            int age = rs.getInt("age");
            Employee emp = new Employee(name,branch,age);
            EmpDatas.add(emp);
        }
        return EmpDatas;
    }

}
class StoreHouse{
    void insertHouse(House house) throws SQLException {
        String InsertQuary = "INSERT INTO house (location,isRented)VALUES(?,?);";
        try(Connection con = DatabaseConnectivity.getConnection();
            PreparedStatement pstmt = con.prepareStatement(InsertQuary)){
//            pstmt.setInt(1, emp.getId());
            pstmt.setString(1, house.getLocation());
            pstmt.setBoolean(2,house.getIsRented());
            pstmt.executeUpdate();
        }


    }

    List<House> displayAllHouse() throws SQLException{
        List<House> HouseData = new ArrayList<>();
        String DisplayQuery = "Select * from house;";
        Connection con = DatabaseConnectivity.getConnection();
        Statement st =  con.createStatement();
        ResultSet rs = st.executeQuery(DisplayQuery);
        while(rs.next()){
            String housenumber = rs.getString("housenumber");
            String location = rs.getString("location");
            Boolean isRented = rs.getBoolean("isRented");
            House house = new House( location,isRented);
            HouseData.add(house);
        }
        return HouseData;
    }
}



public class Main {
    public static void main(String[] args) throws SQLException {
        Employee emp ;
        House house;
        StoreEmployee stEmp = new StoreEmployee() ;
        StoreHouse stHouse = new StoreHouse();
        Scanner sc = new Scanner(System.in);
        int press;
        System.out.println("1 -> Admin");
        System.out.println("2 -> Booking");
        System.out.println("0 -> Exit");
        while (true) {
            press = sc.nextInt();
            switch(press){
                case 1:
                    System.out.println("Enter Admin Email :-");
                    String email = sc.next();
                    System.out.println("Enter Admin Password");
                    String password = sc.next();
                    Admin admin = new Admin();
                    if(Objects.equals(admin.getEmail(), email) && Objects.equals(admin.getPassword(), password)){
                        int press2;
                        while(true){
                            System.out.println("1 -> Add Employee");
                            System.out.println("2 -> See All Employee Data");
                            System.out.println("3 -> Add New House");
                            System.out.println("4 -> See All Houses");
                            System.out.println("0 -> Exit");
                            System.out.println("Choose One :- ");
                             press2 = sc.nextInt();
                            switch (press2) {
                                case 1:
                                    System.out.println("Enter Employee " + " Name :-");
                                    String name = sc.next();
                                    System.out.println("Enter Employee "  + " Branch :-");
                                    String branch = sc.next();
                                    System.out.println("Enter Employee "  + " Age :-");
                                    int age = sc.nextInt();
                                    emp = new Employee(name, branch, age);
                                    stEmp.insertData(emp);
//                        arr.add(emp);


                                    break;
                                case 2:
                                    List<Employee> arr = stEmp.displayData();
                                    for (Employee ar:arr) {
                                        System.out.println( ar.getId()+" "+ar.getName()+" "+ar.getAge()+" "+ ar.getBranch()+" ");
                                    }
                                    break;
//
                                case 3:
                                    System.out.println("Enter Location of house :-");
                                    String location = sc.next();
                                    System.out.println("Enter isRented  :-");
                                    Boolean isRented = sc.nextBoolean();
                                    house = new House(location, isRented);
                                    stHouse.insertHouse(house);
                                    break;
                                case 4:
                                    List<House> houses = stHouse.displayAllHouse();
                                    for (House hs:houses) {
                                        System.out.println( hs.getHouseNumber()+" "+hs.getLocation()+" "+hs.getIsRented()+" "+ hs.getRent()+" ");
                                    }
                                    break;
                                case 0:
                                    break;

                                default:
                                    System.out.println("Choose Wrong number !!");
                            }

                            if (press2 == 0) break;

                        }

                    }else{
                        System.out.println("Wrong Admin Password");
                        break;
                    }
                    break;
                case 2:
                    System.out.println("Enter Booking");

            }if (press == 0) {
                break;


        }
        }


    }

}


/*

1. Admin
    A. adding houses
    B. adding employee

2. Booking
    A. login to employee check --- he dont have permission to adding home
        a. book home with userDetail   --  if(admin.post == admin)
                SHOW LOCATION WITH ID
                Enter id: and get whole object and change
                isRented - true;


              inherite home and add RentTime and Rentername column and send it in new Table in sql that is RentedHouseWithDetail
    B.
    C. everyOne check remening houses with details like location, rent, BHK,





------------SQL-------------
1. home
2. Employee
3. admin

CREATE DATABASE homerental
CREATE TABLE employee (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255) NOT NULL, post VARCHAR(50),branch VARCHAR(50),age INT);
CREATE TABLE house (
    housenumber INT AUTO_INCREMENT PRIMARY KEY,
    location VARCHAR(255) NOT NULL,
    isRented BOOLEAN
);


 */

