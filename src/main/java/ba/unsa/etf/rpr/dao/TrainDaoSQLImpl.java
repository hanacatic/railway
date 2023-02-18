package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.domain.Train;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;
/**
 * MySQL implementation of TrainDao
 * @author Hana Catic
 * */
public class TrainDaoSQLImpl extends AbstractDao<Train> implements TrainDao{
    private static TrainDaoSQLImpl instance = null;
    private TrainDaoSQLImpl(){
        super("Trains");
    }

    public static TrainDaoSQLImpl getInstance(){
        if(instance == null){
            instance = new TrainDaoSQLImpl();
        }
        return instance;
    }
    public static void removeInstance(){
        instance = null;
    }

    @Override
    public Train row2object(ResultSet rs) throws RailwayException {
        try{
            Train train = new Train();
            train.setId(rs.getInt("id"));
            train.setName(rs.getString("name"));
            train.setDateBought(rs.getDate("dateBought"));
            return train;
        }
        catch(Exception e){
            throw new RailwayException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Train object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("name", object.getName());
        row.put("dateBought", object.getDateBought());
        return row;
    }

    @Override
    public Train searchByName(String name) throws RailwayException {
        return executeQueryUnique("SELECT * FROM Trains WHERE name = ?", new Object[]{name});
    }
}
