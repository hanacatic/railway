package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.RailwayStation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RailwayStationDaoSQLImpl implements RailwayStationDao{
    private Connection connection;

    public RailwayStationDaoSQLImpl(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql6583526", "sql6583526", "");        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public RailwayStation add(RailwayStation item) {
        try{
            String insert = "INSERT INTO RailwayStations(name) VALUES(?)";
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getName());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            item.setId(rs.getInt(1));
            return item;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RailwayStation update(RailwayStation item) {
        try{
            String insert = "UPDATE RailwayStations SET name = ? WHERE id = ?";
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getName());
            stmt.setInt(2, item.getId());
            stmt.executeUpdate();
            return item;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RailwayStation getById(int id) {
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
    public List<RailwayStation> getAll() {
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
