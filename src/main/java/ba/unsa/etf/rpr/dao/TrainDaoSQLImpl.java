package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.domain.Train;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TrainDaoSQLImpl extends AbstractDao<Train> implements TrainDao{
    public TrainDaoSQLImpl(){
        super("trains");
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
