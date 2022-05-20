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

        String paymentStatusSQL = "SELECT status FROM payment_entity;";

        String status = null;
        try (
                Connection conn = DriverManager.getConnection( "System.getProperty(\"db.url\")", "app", "pass")
        ) {
            status = runner.query(conn, paymentStatusSQL, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static String getCreditStatus() {
        QueryRunner runner = new QueryRunner();

        String creditStatusSQL = "SELECT status FROM credit_request_entity;";

        String status = null;
        try (
                Connection conn = DriverManager.getConnection( "System.getProperty(\"db.url\")", "app", "pass")
        ) {
            status = runner.query(conn, creditStatusSQL, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }



    public void clean() {
        QueryRunner runner = new QueryRunner();

        var creditSQL = "DELETE FROM credit_request_entity WHERE TRUE;";
        var paymentSQL = "DELETE FROM payment_entity WHERE TRUE;";
        var orderSQL = "DELETE FROM order_entity WHERE TRUE;";

        try (
                Connection conn = DriverManager.getConnection( "System.getProperty(\"db.url\")", "app", "pass")
        ) {
            runner.update(conn, creditSQL);
            runner.update(conn, paymentSQL);
            runner.update(conn, orderSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
