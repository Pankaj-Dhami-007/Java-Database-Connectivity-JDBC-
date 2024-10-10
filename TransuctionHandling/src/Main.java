import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class Bank {
    static boolean isSufficient(Connection connection, int account_number, double ammount) {
        try {
            String query = "SELECT balance from accounts WHERE account_number = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, account_number);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                double current_balance = resultSet.getDouble("balance");
                if (ammount > current_balance) {
                    return false;
                }
            } else {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void debitCredit() {
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/lenden";
            String name = "root";
            String password = "Dhami@123";
            Connection connection = DriverManager.getConnection(url, name, password);

            String debit_query = "UPDATE students SET balance =balance - ?WHERE account_number = ?";
            String credit_query = "UPDATE accounts SET balance = balance + ?WHERE account_number = ?";

            PreparedStatement debitPreparedStatement = connection.prepareStatement(debit_query);
            PreparedStatement creditPreparedStatement = connection.prepareStatement(credit_query);
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter amount");
            double amount = sc.nextDouble();
            System.out.println("Enter account no ");
            int account_number = sc.nextInt();
            debitPreparedStatement.setDouble(1, amount);
            debitPreparedStatement.setInt(2, account_number);
            creditPreparedStatement.setDouble(1, amount);
            creditPreparedStatement.setInt(2, account_number);
            if (isSufficient(connection, account_number, amount)) {
                int affectedRows1 = debitPreparedStatement.executeUpdate();
                int affectedROws2 = creditPreparedStatement.executeUpdate();
            } else {
                System.out.println("Isufficient balance ...");
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
