package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.domain.RailwayStation;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class RailwayStationDaoSQLImpl extends AbstractDao<RailwayStation> implements RailwayStationDao{


    public RailwayStationDaoSQLImpl() {
        super("railwayStations");
    }

    @Override
    public RailwayStation row2object(ResultSet rs) throws RailwayException {
        try{
            RailwayStation railwayStation = new RailwayStation();
            railwayStation.setId(rs.getInt("id"));
            railwayStation.setName(rs.getString("name"));
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
        return row;
    }

    @Override
    public RailwayStation searchByName(String name) throws RailwayException {
        return executeQueryUnique("SELECT * FROM RailwaStations WHERE name = ?", new Object[]{name});
    }
}
