package db.parsers;

import entities.DatabaseEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Parser<T extends DatabaseEntity> {
    T parse(ResultSet resultSet) throws SQLException;
}
