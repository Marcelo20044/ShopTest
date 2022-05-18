package data;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {
    // TODO: поменять в билд градл test на systemProperty 'db.url', System.getProperty('db.url')
    // TODO: вставить в connection "System.getProperty(\"db.url\")"


    public static String getPaymentStatus() {
        QueryRunner runner = new QueryRunner();

        String amountSQL = "SELECT status FROM payment_entity;";

        String status = null;
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass")
        ) {
            status = runner.query(conn, amountSQL, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }


    public void afterPaymentClean() {
        QueryRunner runner = new QueryRunner();

        var paymentSQL = "DELETE FROM payment_entity WHERE TRUE;";
        var orderSQL = "DELETE FROM order_entity WHERE TRUE;";

        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass")
        ) {
            runner.update(conn, paymentSQL);
            runner.update(conn, orderSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
