package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import java.sql.*;

public abstract class AbstractDao<Type extends Idable> implements Dao<Type>{
    private Connection connection;
    private String tableName;
}
