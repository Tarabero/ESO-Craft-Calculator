package db.parsers;

import db.entities.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Parser<T extends Entity> {
    T parse(ResultSet resultSet) throws SQLException;
}
