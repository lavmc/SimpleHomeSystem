package at.lav.database;


import at.lav.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {

    private static Connection connection;

    public static void initialize() throws Exception {
        String urlWithoutDB = "jdbc:mysql://" + Config.getDatabaseHost() + ":" + Config.getDatabasePort();
        connection = DriverManager.getConnection(urlWithoutDB, Config.getDatabaseUser(), Config.getDatabasePassword());

        if (!databaseExists(Config.getDatabaseName())) {
            createDatabase();
        }

        String urlWithDB = urlWithoutDB + "/" + Config.getDatabaseName();
        connection = DriverManager.getConnection(urlWithDB, Config.getDatabaseUser(), Config.getDatabasePassword());

        if (!tableExists("homes")) {
            createTable();
        }
    }

    private static boolean databaseExists(String dbName) throws Exception {
        ResultSet resultSet = connection.getMetaData().getCatalogs();
        while (resultSet.next()) {
            if (dbName.equals(resultSet.getString(1))) {
                return true;
            }
        }
        return false;
    }

    private static void createDatabase() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE DATABASE " + Config.getDatabaseName());
        }
    }

    private static boolean tableExists(String tableName) throws Exception {
        ResultSet resultSet = connection.getMetaData().getTables(null, null, tableName, null);
        return resultSet.next();
    }

    private static void createTable() throws Exception {
        try (Statement stmt = connection.createStatement()) {
            String tableCreationSql = "CREATE TABLE homes (" +
                    "player_uuid VARCHAR(36) NOT NULL, " +
                    "home_name VARCHAR(50) NOT NULL, " +
                    "x DOUBLE NOT NULL, " +
                    "y DOUBLE NOT NULL, " +
                    "z DOUBLE NOT NULL, " +
                    "world VARCHAR(100) NOT NULL, " +
                    "PRIMARY KEY (player_uuid, home_name)" +
                    ")";
            stmt.execute(tableCreationSql);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}