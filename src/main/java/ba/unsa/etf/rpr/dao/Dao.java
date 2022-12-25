package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Exceptions.RailwayException;

import java.util.List;
/**
 * Generic root Dao interface for all classes
 * @author Hana Catic
 * */
public interface Dao<Type>{
    /**
     * Saves entity in database
     * @param item to be saved in the database
     * @return saved item
     * */
    Type add(Type item) throws RailwayException;/**
     * Updates entity in database based on id
     * @param item entity to be updated
     * @return updated item
     * */

    Type update(Type item) throws RailwayException;
    /**
     * Finds entity in database with given id
     * @param id primary key of entity
     * @return database entity
     * */
    Type getById(int id) throws RailwayException;
    /**
     * Hard delete of entity
     * @param id primary key of entity to be deleted from database
     * */
    void delete(int id) throws RailwayException;
    /**
     * Gets list of all entities from database
     * @return List of all entities form database
     * */
    List<Type> getAll() throws RailwayException;
}
