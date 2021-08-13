package db;

import db.parsers.Parser;
import entitites.DatabaseEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseHelper {
    private final static String DIRECTORY_PROPERTY = "user.dir";
    private final static String JDBC_CONFIGURATION = "jdbc:sqlite:";
    private final static String DATABASE_LOCAL_PATH = "/db/teso_craft_database.s3db";
    private final static Logger logger = Logger.getLogger(DatabaseHelper.class.getSimpleName());

    private static DatabaseHelper instance;

    private Connection connection;

    private DatabaseHelper(){
    }

    public static DatabaseHelper getInstance(){
        if (instance == null){
            instance = new DatabaseHelper();
        }
        return instance;
    }

    public void connect() {
        try {
            String projectDir = System.getProperty(DIRECTORY_PROPERTY);
            if (!projectDir.endsWith("src")){
                projectDir = projectDir + "/src";
            }
            String databasePath = projectDir + DATABASE_LOCAL_PATH;
            connection = DriverManager.getConnection(JDBC_CONFIGURATION + databasePath);
            logger.log(Level.INFO, "Established database connection");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.log(Level.INFO, "Failed to connect to database", throwables);
        }
    }

    public void close(){
        if (connection == null){
            logger.log(Level.WARNING, "Can't close connection, connection is null");
            return;
        }
        try {
            connection.close();
            logger.log(Level.INFO, "Connection closed");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.log(Level.WARNING, "Failed to close connection", throwables );
        }
    }

    public void executeStatement(String query){
        executeStatementWithResult(query, null);
    }

    // TO READ: generics <T extends smth>
    public<T extends DatabaseEntity> List<T> executeStatementWithResult(String query, Parser<T> parser){
        if (!isAlive()){
            logger.log(Level.WARNING, "Can't send query, connection is null");
            return null;
        }
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            statement.setQueryTimeout(30);
            resultSet = statement.executeQuery(query);

            if (parser != null) {
                List<T> resultList = new ArrayList<>();
                while (resultSet.next()) {
                    resultList.add(parser.parse(resultSet));
                }
                return resultList;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                closeStatement(statement);
                closeResultSet(resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void closeStatement(Statement statement) throws SQLException {
        if (statement != null){
            statement.close();
        }
    }

    private void closeResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet != null){
            resultSet.close();
        }
    }

    private boolean isAlive(){
        return connection != null;
    }

    // TODO: 12.07.2021 remove later
    public void testStatement() {
        if (connection == null) {
            return;
        }
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.setQueryTimeout(30);

            ResultSet resultSet = statement.executeQuery("SELECT * FROM materials");
            while (resultSet.next()) {
                System.out.println("Material id: " + resultSet.getInt("id")
                        + ", name: " + resultSet.getString("material_name")
                        + ", price: " + resultSet.getInt("price"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
