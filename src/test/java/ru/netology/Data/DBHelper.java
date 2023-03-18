package ru.netology.Data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBHelper {
    @SneakyThrows
    public static void clearDB() {

        var deleteOrder = "DELETE FROM order_entity;";
        var deletePayment = "DELETE FROM payment_entity;";
        var deleteCredit = "DELETE FROM credit_request_entity;";
        var runner = new QueryRunner();


        var conn = getConnection();

        runner.update(conn, deleteOrder);
        runner.update(conn, deletePayment);
        runner.update(conn, deleteCredit);

    }

    @SneakyThrows
    public static String getCreditStatusDB() {
        var status = "SELECT status FROM credit_request_entity;";
        var runner = new QueryRunner();
        String creditStatus;


        var conn = getConnection();

        creditStatus = runner.query(conn, status, new ScalarHandler<>());


        return creditStatus;
    }

    @SneakyThrows
    public static String getPaymentStatusDB() {
        var sql = "SELECT status FROM payment_entity;";
        var runner = new QueryRunner();
        String payStatus;


        var conn = getConnection();

        payStatus = runner.query(conn, sql, new ScalarHandler<>());


        return payStatus;
    }

    @SneakyThrows
    public static long getPaymentCount() {
        var sql = "SELECT COUNT(id) as count FROM payment_entity;";
        var runner = new QueryRunner();
        long payCount;


        var conn = getConnection();

        payCount = runner.query(conn, sql, new ScalarHandler<>());

        return payCount;
    }

    @SneakyThrows
    public static long getCreditCount() {
        var sql = "SELECT COUNT(id) as count FROM credit_request_entity;";
        var runner = new QueryRunner();
        long creditCount;


        var conn = getConnection();

        creditCount = runner.query(conn, sql, new ScalarHandler<>());

        return creditCount;
    }

    @SneakyThrows
    public static long getOrderCount() {
        var sql = "SELECT COUNT(id) as count FROM order_entity;";
        var runner = new QueryRunner();
        long orderCount;
        var conn = getConnection();
        orderCount = runner.query(conn, sql, new ScalarHandler<>());

        return orderCount;
    }

    @SneakyThrows
    private static Connection getConnection() {
        return DriverManager.getConnection(System.getProperty("dbUrl"), System.getProperty("dbUser"), System.getProperty("dbPass"));
    }
}
