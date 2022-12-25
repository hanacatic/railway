package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.domain.Train;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        return null;
    }

    @Override
    public Train searchByName(String name) {
        try{
            String query = "SELECT * FROM Trains WHERE name = ?";
            PreparedStatement stmt = this.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Train train = new Train();
                train.setName(name);
                train.setId(rs.getInt(1));
                train.setDateBought(rs.getDate(3));
                return train;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
