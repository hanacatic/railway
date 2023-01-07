package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.domain.Journey;
import ba.unsa.etf.rpr.domain.RailwayStation;
import ba.unsa.etf.rpr.domain.Train;

import java.sql.*;
import java.sql.Date;
import java.util.*;


public class JourneyDaoSQLImpl extends AbstractDao<Journey> implements JourneyDao{
    private static JourneyDaoSQLImpl instance = null;
    private JourneyDaoSQLImpl(){
        super("journeys");
    }
    public static JourneyDaoSQLImpl getInstance(){
        if(instance == null){
            instance = new JourneyDaoSQLImpl();
        }
        return instance;
    }

    public static void removeInstance(){
        instance = null;
    }
    @Override
    public Journey row2object(ResultSet rs) throws RailwayException {
        try{
            Journey journey = new Journey();
            journey.setId(rs.getInt("id"));
            journey.setDepartureDate(rs.getDate("departureDate"));
            journey.setArrivalDate(rs.getDate("arrivalDate"));
            journey.setDepartureTime(rs.getTime("departureTime"));
            journey.setArrivalTime(rs.getTime("arrivalTime"));
            journey.setTrain(DaoFactory.trainDao().getById(rs.getInt("trainId")));
            journey.setDepartureStation(DaoFactory.railwayStationDao().getById(rs.getInt("departureStationId")));
            journey.setArrivalStation(DaoFactory.railwayStationDao().getById(rs.getInt("arrivalStationId")));
            return journey;

        }catch(Exception e) {
            throw new RailwayException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Journey object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("trainId", object.getTrain().getId());
        row.put("departureStationId", object.getDepartureStation().getId());
        row.put("arrivalStationId", object.getArrivalStation().getId());
        row.put("departureDate", object.getDepartureDate());
        row.put("arrivalDate", object.getArrivalDate());
        row.put("departureTime", object.getDepartureTime());
        row.put("arrivalTime", object.getArrivalTime());
        return row;
    }

    @Override
    public List<Journey> searchByTrain(Train train) throws RailwayException {
        return executeQuery("SELECT * FROM Journeys WHERE train = ?", new Object[]{train.getId()});
    }

    @Override
    public List<Journey> searchByDepartureStation(RailwayStation departureStation) throws RailwayException {
        return executeQuery("SELECT * FROM Journeys WHERE departureStation = ?", new Object[]{departureStation.getId()});
    }

    @Override
    public List<Journey> searchByArrivalStation(RailwayStation arrivalStation) throws RailwayException {
        return executeQuery("SELECT * FROM Journeys WHERE arrivalStation = ?", new Object[]{arrivalStation.getId()});
    }

    @Override
    public List<Journey> searchByDepartureDate(Date departureDate) throws RailwayException {
        return executeQuery("SELECT * FROM Journeys WHERE departureDate = ?", new Object[]{departureDate});
    }

    @Override
    public List<Journey> searchByArrivalDate(Date arrivalDate) throws RailwayException {
        return executeQuery("SELECT * FROM Journeys WHERE arrivalDate = ?", new Object[]{arrivalDate});
    }

    @Override
    public List<Journey> searchByDepartureTime(Time departureTime) throws RailwayException {
        return executeQuery("SELECT * FROM Journeys WHERE departureTime = ?", new Object[]{departureTime});
    }

    @Override
    public List<Journey> searchByArrivalTime(Time arrivalTime) throws RailwayException {
        return executeQuery("SELECT * FROM Journeys WHERE arrivalTime = ?", new Object[]{arrivalTime});
    }
}
