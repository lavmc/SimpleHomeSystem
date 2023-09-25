package at.lav.database;


import at.lav.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
public class Database {

    private static Connection connection;

    public static void initialize() throws Exception {
        String url = "jdbc:mysql://" + Config.getDatabaseHost() + ":" + Config.getDatabasePort() + "/" + Config.getDatabaseName();
        connection = DriverManager.getConnection(url, Config.getDatabaseUser(), Config.getDatabasePassword());
    }

    public static Connection getConnection() {
        return connection;
    }
}