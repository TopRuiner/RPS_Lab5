package ru.krylov.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<Entity, Key> {
    Integer create(Entity model);

    Entity read(Key key);

    boolean update(Entity model);

    boolean delete(Key key);

    List<Entity> getAll();
    List<Entity> getAllFiltered(String field,String value);
    List<String> getAllGroupedBy(String field);
    List<Entity> getAllWhere(String field,String value);
}
