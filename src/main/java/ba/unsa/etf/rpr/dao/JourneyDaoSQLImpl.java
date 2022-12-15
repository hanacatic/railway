package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Journey;
import ba.unsa.etf.rpr.domain.RailwayStation;
import ba.unsa.etf.rpr.domain.Train;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JourneyDaoSQLImpl implements JourneyDao{
    private Connection connection;

    public JourneyDaoSQLImpl(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql6583526", "sql6583526", "");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public Journey add(Journey item) {
        try{
            String insert = "INSERT INTO Journeys(name) VALUES(?)"; //will need to be written differently because journey not defined by name
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            item.setId(rs.getInt(1));
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Journey update(Journey item) {
        try{
            String insert = "INSERT INTO RaiwayStations(name) VALUES(?)";
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            item.setId(rs.getInt(1));
            return item;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Journey getById(int id) {
        try{
            String query = "SELECT * FROM Journeys WHERE id = ?";
            PreparedStatement stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Journey journey = new Journey();
                journey.setId(id);
                journey.setTrainId(rs.getInt("train"));
                journey.setDepartureStationId(rs.getInt("departureStation"));
                journey.setArrivalStationId(rs.getInt("arrivalStation"));
                journey.setDepartureDate(rs.getDate("departureDate"));
                journey.setArrivalDate(rs.getDate("arrivalDate"));
                journey.setDepartureTime(rs.getTime("departureTime"));
                journey.setArrivalTime(rs.getTime("arrivalTime"));
                return journey;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        try{
            String insert = "DELETE FROM Journeys WHERE id = ?";
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Journey> getAll() {
        List<Journey> journeys = new ArrayList<Journey>();
        try{
            String query = "SELECT * FROM Journeys";
            PreparedStatement stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Journey journey = new Journey();
                journey.setId(rs.getInt("id"));
                journey.setTrainId(rs.getInt("train"));
                journey.setDepartureStationId(rs.getInt("departureStation"));
                journey.setArrivalStationId(rs.getInt("arrivalStation"));
                journey.setDepartureDate(rs.getDate("departureDate"));
                journey.setArrivalDate(rs.getDate("arrivalDate"));
                journey.setDepartureTime(rs.getTime("departureTime"));
                journey.setArrivalTime(rs.getTime("arrivalTime"));
                journeys.add(journey);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return journeys;
    }

    @Override
    public List<Journey> searchByTrain(Train train) {
        List<Journey> journeys = new ArrayList<Journey>();
        try{
            String query = "SELECT * FROM Journeys WHERE train = ?";
            PreparedStatement stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, train.getId());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Journey journey = new Journey();
                journey.setId(rs.getInt("id"));
                journey.setTrainId(rs.getInt("train"));
                journey.setDepartureStationId(rs.getInt("departureStation"));
                journey.setArrivalStationId(rs.getInt("arrivalStation"));
                journey.setDepartureDate(rs.getDate("departureDate"));
                journey.setArrivalDate(rs.getDate("arrivalDate"));
                journey.setDepartureTime(rs.getTime("departureTime"));
                journey.setArrivalTime(rs.getTime("arrivalTime"));
                journeys.add(journey);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return journeys;
    }

    @Override
    public List<Journey> searchByDepartureStation(RailwayStation departureStation) {
        List<Journey> journeys = new ArrayList<Journey>();
        try{
            String query = "SELECT * FROM Journeys WHERE departureStation = ?";
            PreparedStatement stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, departureStation.getId());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Journey journey = new Journey();
                journey.setId(rs.getInt("id"));
                journey.setTrainId(rs.getInt("train"));
                journey.setDepartureStationId(rs.getInt("departureStation"));
                journey.setArrivalStationId(rs.getInt("arrivalStation"));
                journey.setDepartureDate(rs.getDate("departureDate"));
                journey.setArrivalDate(rs.getDate("arrivalDate"));
                journey.setDepartureTime(rs.getTime("departureTime"));
                journey.setArrivalTime(rs.getTime("arrivalTime"));
                journeys.add(journey);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return journeys;
    }

    @Override
    public List<Journey> searchByArrivalStation(RailwayStation arrivalStation) {
        List<Journey> journeys = new ArrayList<Journey>();
        try{
            String query = "SELECT * FROM Journeys WHERE arrivalStation = ?";
            PreparedStatement stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, arrivalStation.getId());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Journey journey = new Journey();
                journey.setId(rs.getInt("id"));
                journey.setTrainId(rs.getInt("train"));
                journey.setDepartureStationId(rs.getInt("departureStation"));
                journey.setArrivalStationId(rs.getInt("arrivalStation"));
                journey.setDepartureDate(rs.getDate("departureDate"));
                journey.setArrivalDate(rs.getDate("arrivalDate"));
                journey.setDepartureTime(rs.getTime("departureTime"));
                journey.setArrivalTime(rs.getTime("arrivalTime"));
                journeys.add(journey);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return journeys;
    }

    @Override
    public List<Journey> searchByDepartureDate(Date departureDate) {
        List<Journey> journeys = new ArrayList<Journey>();
        try{
            String query = "SELECT * FROM Journeys WHERE departureDate = ?";
            PreparedStatement stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, (java.sql.Date) departureDate); //CHECK WHAT'S UP WITH DATE?
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Journey journey = new Journey();
                journey.setId(rs.getInt("id"));
                journey.setTrainId(rs.getInt("train"));
                journey.setDepartureStationId(rs.getInt("departureStation"));
                journey.setArrivalStationId(rs.getInt("arrivalStation"));
                journey.setDepartureDate(rs.getDate("departureDate"));
                journey.setArrivalDate(rs.getDate("arrivalDate"));
                journey.setDepartureTime(rs.getTime("departureTime"));
                journey.setArrivalTime(rs.getTime("arrivalTime"));
                journeys.add(journey);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return journeys;
    }

    @Override
    public List<Journey> searchByArrivalDate(Date arrivalDate) {
        List<Journey> journeys = new ArrayList<Journey>();
        try{
            String query = "SELECT * FROM Journeys WHERE arrivalDate = ?";
            PreparedStatement stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, (java.sql.Date) arrivalDate); //CHECK WHAT'S UP WITH DATE?
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Journey journey = new Journey();
                journey.setId(rs.getInt("id"));
                journey.setTrainId(rs.getInt("train"));
                journey.setDepartureStationId(rs.getInt("departureStation"));
                journey.setArrivalStationId(rs.getInt("arrivalStation"));
                journey.setDepartureDate(rs.getDate("departureDate"));
                journey.setArrivalDate(rs.getDate("arrivalDate"));
                journey.setDepartureTime(rs.getTime("departureTime"));
                journey.setArrivalTime(rs.getTime("arrivalTime"));
                journeys.add(journey);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return journeys;
    }

    @Override
    public List<Journey> searchByDepartureTime(Time departureTime) {
        List<Journey> journeys = new ArrayList<Journey>();
        try{
            String query = "SELECT * FROM Journeys WHERE departureTime = ?";
            PreparedStatement stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setTime(1, departureTime);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Journey journey = new Journey();
                journey.setId(rs.getInt("id"));
                journey.setTrainId(rs.getInt("train"));
                journey.setDepartureStationId(rs.getInt("departureStation"));
                journey.setArrivalStationId(rs.getInt("arrivalStation"));
                journey.setDepartureDate(rs.getDate("departureDate"));
                journey.setArrivalDate(rs.getDate("arrivalDate"));
                journey.setDepartureTime(rs.getTime("departureTime"));
                journey.setArrivalTime(rs.getTime("arrivalTime"));
                journeys.add(journey);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return journeys;
    }

    @Override
    public List<Journey> searchByArrivalTime(Time arrivalTime) {
        List<Journey> journeys = new ArrayList<Journey>();
        try{
            String query = "SELECT * FROM Journeys WHERE arrivalTime = ?";
            PreparedStatement stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setTime(1, arrivalTime);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Journey journey = new Journey();
                journey.setId(rs.getInt("id"));
                journey.setTrainId(rs.getInt("train"));
                journey.setDepartureStationId(rs.getInt("departureStation"));
                journey.setArrivalStationId(rs.getInt("arrivalStation"));
                journey.setDepartureDate(rs.getDate("departureDate"));
                journey.setArrivalDate(rs.getDate("arrivalDate"));
                journey.setDepartureTime(rs.getTime("departureTime"));
                journey.setArrivalTime(rs.getTime("arrivalTime"));
                journeys.add(journey);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return journeys;
    }
}
