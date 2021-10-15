package db;

import db.parsers.Parser;
import entities.Entity;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseHelper {
    private final static String DIRECTORY_PROPERTY = "user.dir";
    private final static String JDBC_CONFIGURATION = "jdbc:sqlite:";
    private final static String DATABASE_LOCAL_PATH = "/teso_craft_database.s3db";
    private final static String DATABASE_FILE_NOT_FOUND_ERR_MSG = "Database file \"teso_craft_database.s3db\" not found in the root directory! Application will be closed.";
    private static final String DATABASE_ERROR_DIALOG_TITLE = "Database error";
    private final static Logger logger = Logger.getLogger(DatabaseHelper.class.getSimpleName());

    public interface DatabaseHelperErrorListener {
        void onError(String errorTitle, String errorMessage);
    }

    private static DatabaseHelper instance;

    private Connection connection;
    private DatabaseHelperErrorListener errorListener;

    private DatabaseHelper() {
    }

    public static DatabaseHelper getInstance() {
        if (instance == null) {
            instance = new DatabaseHelper();
        }
        return instance;
    }

    public void setErrorListener(DatabaseHelperErrorListener listener) {
        errorListener = listener;
    }

    public void connect() {
        try {
            String projectDir = System.getProperty(DIRECTORY_PROPERTY);
            String databasePath = projectDir + DATABASE_LOCAL_PATH;
            if (!isDatabaseExists(databasePath)) {
                fireError(DATABASE_FILE_NOT_FOUND_ERR_MSG);
                return;
            }
            connection = DriverManager.getConnection(JDBC_CONFIGURATION + databasePath);
            logger.log(Level.INFO, "Established database connection");
        } catch (SQLException throwables) {
            fireError(throwables.getMessage());
            throwables.printStackTrace();
            logger.log(Level.INFO, "Failed to connect to database", throwables);
        }
    }

    private boolean isDatabaseExists(String databasePath) {
        return new File(databasePath).exists();
    }

    private void fireError(String errorMessage) {
        if (errorListener != null) {
            errorListener.onError(DATABASE_ERROR_DIALOG_TITLE, errorMessage);
        }
    }

    public void close() {
        if (connection == null) {
            logger.log(Level.WARNING, "Can't close connection, connection is null");
            return;
        }
        try {
            connection.close();
            logger.log(Level.INFO, "Connection closed");
        } catch (SQLException throwables) {
            fireError(throwables.getMessage());
            throwables.printStackTrace();
            logger.log(Level.WARNING, "Failed to close connection", throwables);
        }
    }

    public void executeStatement(String query) {
        //executeStatementWithResult(query,null);
        if (!isAlive()) {
            logger.log(Level.WARNING, "Can't send query, connection is null");
        }
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException throwables) {
            fireError(throwables.getMessage());
            throwables.printStackTrace();
        } finally {
            try {
                closeStatement(statement);
            } catch (SQLException e) {
                fireError(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public <T extends Entity> List<T> executeStatementWithResult(String query, Parser<T> parser) {
        if (!isAlive()) {
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
                List<T> resultList = new ArrayList();
                while (resultSet.next()) {
                    resultList.add(parser.parse(resultSet));
                }
                return resultList;
            }

        } catch (SQLException throwables) {
            fireError(throwables.getMessage());
            throwables.printStackTrace();
        } finally {
            try {
                closeStatement(statement);
                closeResultSet(resultSet);
            } catch (SQLException e) {
                fireError(e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }

    private void closeStatement(Statement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }

    private void closeResultSet(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
    }

    private boolean isAlive() {
        return connection != null;
    }
}
