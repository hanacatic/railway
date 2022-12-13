package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.RailwayStation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RailwayStationDaoSQLImpl implements RailwayStationDao{
    private Connection connection;

    public RailwayStationDaoSQLImpl(){
        try{

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public Object add(Object item) {
        return null;
    }

    @Override
    public Object update(Object item) {
        return null;
    }

    @Override
    public Object getById(int id) {
        try{
            String query = "SELECT * FROM RailwayStations WHERE id = ?";
            PreparedStatement stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                RailwayStation station = new RailwayStation();
                station.setId(id);
                station.setName(rs.getString("name"));
                return station;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        try{
            String insert = "DELETE FROM RailwayStations WHERE id = ?";
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List getAll() {
        List<RailwayStation> stations = new ArrayList<RailwayStation>();
        try{
            String query = "SELECT * FROM RailwayStations";
            PreparedStatement stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                RailwayStation station = new RailwayStation();
                station.setId(rs.getInt("id"));
                station.setName(rs.getString("name"));
                stations.add(station);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return stations;
    }

    @Override
    public RailwayStation searchByName(String name) {
        return null;
    }
}
