package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.domain.Idable;
import java.sql.*;
import java.util.*;

/**
 * Abstract class that implements DAO CRUD methods for every entity
 *
 * @author Hana Catic
 * */
public abstract class AbstractDao<Type extends Idable> implements Dao<Type>{
    private Connection connection;
    private String tableName;

    public AbstractDao(String tableName){
        try{
            this.tableName = tableName;
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("application.properties").openStream());
            String url = p.getProperty("db.connection_string");
            String username = p.getProperty("db.username");
            String password = p.getProperty("db.password");
            this.connection = DriverManager.getConnection(url, username, password);
        }catch(Exception e){
            System.out.println("Connection to database failed.");
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return this.connection;
    }
    /**
     * Method for mapping ResultSet into Object
     * @param rs  - result set from database
     * @return an object for specific table
     * @throws RailwayException in case of error with database
     * */
    public abstract Type row2object(ResultSet rs) throws RailwayException;
    /**
     * Method for mapping Object map
     * @param object  -  an object for specific table
     * @return map of object
     * */
    public abstract Map<String, Object> object2row(Type object);
    /**
     * Method for executing any kind of query
     * @param query - SQL query
     * @param params - params for query
     * @return list of objects from database
     * @throws RailwayException in case of error with database
     * */
    public List<Type> executeQuery(String query, Object[] params) throws RailwayException {
        try{
            PreparedStatement stmt = getConnection().prepareStatement(query);
            if(params != null){
                for(int i = 1; i <= params.length; i++){
                    stmt.setObject(i, params[i-1]);
                }
            }
            ResultSet rs = stmt.executeQuery();
            List<Type> results = new ArrayList<>();
            while(rs.next()){
                results.add(row2object(rs));
            }
            return results;
        }
        catch(SQLException e){
            throw new RailwayException(e.getMessage(), e);
        }
    }
    /**
     * Method for executing queries that always return single record
     * @param query - SQL query
     * @param params - params for query
     * @return an object from database
     * @throws RailwayException in case of error with database
     * */
    public Type executeQueryUnique(String query, Object[] params) throws RailwayException {
        List<Type> result = executeQuery(query, params);
        if(result != null && result.size() == 1){
            return result.get(0);
        }
        else{
            throw new RailwayException("Object not found.");
        }
    }
    /**
     * Method that gets a record with given id from a specific table in database
     * @param id - id of object
     * @return an object from database
     * @throws RailwayException in case of error with database
     * */
    public Type getById(int id) throws RailwayException {
        return executeQueryUnique("SELECT * FROM " + this.tableName + " WHERE id = ?", new Object[]{id});
    }
    /**
     * Method that gets all records from a specific table in database
     * @return list of objects from database
     * @throws RailwayException in case of error with database
     * */
    public List<Type> getAll() throws RailwayException {
        return executeQuery("SELECT * FROM " + tableName, null);
    }
    /**
     * Method that adds a new record to database
     * @param item - object to be added to database
     * @return object that was added to database with set id
     * @throws RailwayException in case of error with database
     * */
    public Type add(Type item) throws RailwayException {
        try{
            Map<String, Object> row = object2row(item);
            Map.Entry<String, String> columns = prepareInsertParts(row);

            StringBuilder builder = new StringBuilder();
            builder.append("INSERT INTO ").append(this.tableName);
            builder.append(" (").append(columns.getKey()).append(") ");
            builder.append("VALUES (").append(columns.getValue()).append(") ");

            PreparedStatement stmt = this.connection.prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            int counter = 1;
            for(Map.Entry<String, Object> entry:row.entrySet()){
                if(entry.getKey().equals("id")) continue;
                stmt.setObject(counter, entry.getValue());
                counter ++;
            }
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            item.setId(rs.getInt(1));
            return item;
        }
        catch(SQLException e){
            throw new RailwayException(e.getMessage(), e);
        }
    }
    /**
     * Method that updates a record in database
     * @param item - object to be updated
     * @return object that was updated in database
     * @throws RailwayException in case of error with database
     * */
    public Type update(Type item) throws RailwayException {
        try {
            Map<String, Object> row = object2row(item);
            String updateColumns = prepareUpdateParts(row);
            StringBuilder builder = new StringBuilder();
            builder.append("UPDATE ").append(this.tableName).append(" SET ")
                    .append(updateColumns).append(" WHERE id = ?");
            PreparedStatement stmt = this.connection.prepareStatement(builder.toString());
            int counter = 1;
            for(Map.Entry<String, Object> entry : row.entrySet()){
                if(entry.getKey().equals("id")) continue;
                stmt.setObject(counter, entry.getValue());
                counter ++;
            }
            stmt.setInt(counter, item.getId());
            stmt.executeUpdate();
            return item;
        }
        catch(Exception e){
            throw new RailwayException(e.getMessage(), e);
        }
        }
    /**
     * Method that deletes a record from database
     * @param id - id of object to be deleted from database
     * @throws RailwayException in case of error with database
     * */
    public void delete(int id) throws RailwayException {
        try{
            String del = "DELETE FROM " + tableName + " WHERE id = ?";
            PreparedStatement stmt = this.connection.prepareStatement(del);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch(SQLException e){
            throw new RailwayException(e.getMessage(), e);
        }
    }

    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();
        int entries = 0;
        for(Map.Entry<String, Object> entry: row.entrySet()){
            entries++;
            if(entry.getKey().equals("id")) continue;
            columns.append(entry.getKey());
            questions.append("?");
            if(entries != row.size()){
                columns.append(",");
                questions.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<String, String>(columns.toString(), questions.toString());
    }

    private String prepareUpdateParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();
        int entries = 0;
        for(Map.Entry<String, Object> entry: row.entrySet()){
            entries ++;
            if(entry.getKey().equals("id")) continue;
            columns.append(entry.getKey()).append("= ?");
            if(entries != row.size()){
                columns.append(",");
            }
        }
        return columns.toString();
    }

}
