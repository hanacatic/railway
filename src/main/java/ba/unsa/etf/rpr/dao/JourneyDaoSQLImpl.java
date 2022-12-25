package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.domain.Journey;
import ba.unsa.etf.rpr.domain.RailwayStation;
import ba.unsa.etf.rpr.domain.Train;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class JourneyDaoSQLImpl extends AbstractDao<Journey> implements JourneyDao{

    public JourneyDaoSQLImpl(){
        super("journeys");
    }

    @Override
    public Journey row2object(ResultSet rs) throws RailwayException {
        return null;
    }

    @Override
    public Map<String, Object> object2row(Journey object) {
        return null;
    }

    @Override
    public List<Journey> searchByTrain(Train train) {
        List<Journey> journeys = new ArrayList<Journey>();
        try{
            String query = "SELECT * FROM Journeys WHERE train = ?";
            PreparedStatement stmt = this.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
            PreparedStatement stmt = this.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
            PreparedStatement stmt = this.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
            PreparedStatement stmt = this.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
            PreparedStatement stmt = this.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
            PreparedStatement stmt = this.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
            PreparedStatement stmt = this.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
