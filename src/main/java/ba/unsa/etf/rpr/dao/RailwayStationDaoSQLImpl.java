package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.domain.RailwayStation;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RailwayStationDaoSQLImpl extends AbstractDao<RailwayStation> implements RailwayStationDao{

    private static RailwayStationDaoSQLImpl instance = null;
    private RailwayStationDaoSQLImpl() {
        super("RailwayStations");
    }

    public static RailwayStationDaoSQLImpl getInstance(){
        if( instance == null){
            instance = new RailwayStationDaoSQLImpl();
        }
        return instance;
    }

    public static void removeInstance(){
        instance = null;
    }

    @Override
    public RailwayStation row2object(ResultSet rs) throws RailwayException {
        try{
            RailwayStation railwayStation = new RailwayStation();
            railwayStation.setId(rs.getInt("id"));
            railwayStation.setName(rs.getString("name"));
            railwayStation.setAddress(rs.getString("address"));
            railwayStation.setCity(rs.getString("city"));
            railwayStation.setCountry(rs.getString("country"));
            return railwayStation;
        }catch(Exception e){
            throw new RailwayException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(RailwayStation object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("name", object.getName());
        row.put("address", object.getAddress());
        row.put("city", object.getCity());
        row.put("country", object.getCountry());
        return row;
    }

    @Override
    public RailwayStation searchByName(String name) throws RailwayException {
        return executeQueryUnique("SELECT * FROM RailwayStations WHERE name = ?", new Object[]{name});
    }

    @Override
    public List<RailwayStation> searchByCity(String city) throws RailwayException {
        return executeQuery("SELECT * FROM RailwayStations WHERE city = ?", new Object[]{city});
    }

    @Override
    public List<RailwayStation> searchByCountry(String country) throws RailwayException {
        return executeQuery("SELECT * FROM RailwayStations WHERE country = ?", new Object[]{country});
    }
}
