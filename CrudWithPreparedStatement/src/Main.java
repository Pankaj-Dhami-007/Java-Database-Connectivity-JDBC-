
import java.util.Scanner;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Students {
    public void inputdata() {
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/mydb";
            String name = "root";
            String password = "Dhami@123";
            Connection connection = DriverManager.getConnection(url, name, password);
            String query = "INSERT INTO students (name, age, marks) values (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.print("Enter name : ");
                String sName = sc.nextLine();
                System.out.print("Enter Age : ");
                int age = sc.nextInt();
                System.out.print("Enter marks : ");
                double marks = sc.nextDouble();
                sc.nextLine();// Consume newline left-over from nextInt() and nextDouble()
                System.out.print("Enter more data (Y/N) : ");
                String choice = sc.nextLine();
                preparedStatement.setString(1, sName);
                preparedStatement.setInt(2, age);
                preparedStatement.setDouble(3, marks);

                preparedStatement.addBatch();
                if (choice.toUpperCase().equals("N")) {
                    break;
                }
            }
            int[] arr = preparedStatement.executeBatch();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 0) {
                    System.out.println("Query " + i + " not executed successfully");
                }
            }

            preparedStatement.close();
            connection.close();
            sc.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData() {
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/mydb";
            String name = "root";
            String password = "Dhami@123";
            Connection connection = DriverManager.getConnection(url, name, password);

            String query = "UPDATE students SET marks = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDouble(1, 60.20);
            statement.setInt(2, 3);
            int rowAffected = statement.executeUpdate();
            if (rowAffected > 0) {
                System.out.println("Data update successfully");
            } else {
                System.out.println("Data not updated");
            }
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void retrieveData() {
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/mydb";
            String name = "root";
            String password = "Dhami@123";
            Connection connection = DriverManager.getConnection(url, name, password);

            String query = "SELECT marks FROM students WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, 1);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Double marks = resultSet.getDouble("marks");
                System.out.println("Marks " + marks);
            } else {
                System.out.println("Marks not found ..");
            }
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData() {
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/mydb";
            String name = "root";
            String password = "Dhami@123";
            Connection connection = DriverManager.getConnection(url, name, password);

            String query = "INSERT INTO students (name, age, marks) values (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "Saurabh");
            statement.setInt(2, 29);
            statement.setDouble(3, 55.5);

            int rowAffected = statement.executeUpdate();
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
}

public class Main {
    public static void main(String[] args) {
        Students s = new Students();
        // s.insertData();
        // s.retrieveData();
        // s.updateData();
        s.inputdata();
    }
}
