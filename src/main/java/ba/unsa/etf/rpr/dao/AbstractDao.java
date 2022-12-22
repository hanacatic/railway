package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import java.sql.*;
import java.util.*;

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

    public Connection getConnection(){ return this.connection;}
    public Type getById(int id){return null;}
    public List<Type> getAll() {return null;}
    public Type add(Type item){return null;}
    public Type update(Type item){return null;}

}
