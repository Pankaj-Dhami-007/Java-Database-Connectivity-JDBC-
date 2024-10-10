import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Students {
    public void createDatabase() {
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/";
            String name = "root";
            String password = "Dhami@123";
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "create database Dhami";
            statement.execute(query);
            System.out.println("Database create successfully ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/Dhami";
            String name = "root";
            String password = "Dhami@123";
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = "create table student(s_id int primary key, s_name varchar(50), s_email_id varchar(200))";
            statement.execute(query);
            System.out.println("Table create successfully ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createData() {// insert
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/Dhami";
            String name = "root";
            String password = "Dhami@123";
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = String.format("insert into student(s_id ,s_name, s_email_id) values (%d, '%s', '%s')", 101,
                    "Amit", "amit@gmail.com");
            int rowAffected = statement.executeUpdate(query);
            if (rowAffected > 0) {
                System.out.println("Data Insert successfully");
            } else {
                System.out.println("Data not inserted");
            }
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateData(){
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/mydb";
            String name = "root";
            String password = "Dhami@123";
            Connection connection = DriverManager.getConnection(url, name, password);
            Statement statement = connection.createStatement();
            String query = String.format("update students set marks = %f where id = %d", 90.5, 2);
            int rowAffected = statement.executeUpdate(query);
            if (rowAffected > 0) {
                System.out.println("Data updated successfully");
            } else {
                System.out.println("Data not updated");
            }
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
