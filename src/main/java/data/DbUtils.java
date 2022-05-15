package data;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {


    public static int getPaymentAmount() {
        QueryRunner runner = new QueryRunner();
        System.getProperty("db.url");

        String amountSQL = "SELECT amount FROM payment_entity;";

        int amount = 0;
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass")
        ) {
            amount = runner.query(conn, amountSQL, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amount;
    }







    public void clean() {
        QueryRunner runner = new QueryRunner();

        var creditSQL = "DELETE FROM credit_request_entity WHERE TRUE;";
        var paymentSQL = "DELETE FROM payment_entity WHERE TRUE;";
        var orderSQL = "DELETE FROM order_entity WHERE TRUE;";

        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass")
        ) {
            runner.update(conn, creditSQL);
            runner.update(conn, paymentSQL);
            runner.update(conn, orderSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
