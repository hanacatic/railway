package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.domain.Journey;
import ba.unsa.etf.rpr.domain.RailwayStation;
import ba.unsa.etf.rpr.domain.Train;

import java.sql.Time;
import java.sql.Date;
import java.util.List;
/**
 * Dao interface for journey domain bean
 * @author Hana Catic
 * */
public interface JourneyDao extends Dao<Journey>{
    /**
     * Finds all journeys made by a given train
     * @param train which to look for
     * @return list of journeys
     * */
    List<Journey> searchByTrain(Train train) throws RailwayException;
    /**
     * Finds all journeys from a given railway station
     * @param departureStation station in which to look for departing trains
     * @return list of journeys
     * */
    List<Journey> searchByDepartureStation(RailwayStation departureStation) throws RailwayException;
    /**
     * Finds all journeys  a given railway station
     * @param arrivalStation station in which to look for arriving trains
     * @return list of journeys
     * */
    List<Journey> searchByArrivalStation(RailwayStation arrivalStation) throws RailwayException;
    /**
     * Finds all trains leaving on a given date, all journeys starting on a given date
     * @param departureDate date on which to look for departing trains, journeys starting
     * @return list of journeys
     * */
    List<Journey> searchByDepartureDate(Date departureDate) throws RailwayException;
    /**
     * Finds all trains arriving on a given date, all journeys ending on a given date
     * @param arrivalDate date on which to look for arriving trains, journeys ending
     * @return list of journeys
     * */
    List<Journey> searchByArrivalDate(Date arrivalDate) throws RailwayException;
    /**
     * Finds all trains leaving at a given time, all journeys starting at a given time
     * @param departureTime time when to look for departing trains, journeys starting
     * @return list of journeys
     * */
    List<Journey> searchByDepartureTime(Time departureTime) throws RailwayException;
    /**
     * Finds all trains arriving at a given time, all journeys ending at a given time
     * @param arrivalTime time when to look for arriving trains, journeys ending
     * @return list of journeys
     * */
    List<Journey> searchByArrivalTime(Time arrivalTime) throws RailwayException;
    List<Journey> search(RailwayStation departureStation, RailwayStation arrivalStation, Date departureDate, Date arrivalDate, Time time, boolean arrival);
}
