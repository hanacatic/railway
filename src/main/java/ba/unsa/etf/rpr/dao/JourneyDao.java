package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Journey;
import ba.unsa.etf.rpr.domain.RailwayStation;
import ba.unsa.etf.rpr.domain.Train;

import java.sql.Time;
import java.util.List;

public interface JourneyDao extends Dao{
    List<Journey> searchByTrain(Train train);
    List<Journey> searchByDepartureStation(RailwayStation departureStation);
    List<Journey> searchByArrivalStation(RailwayStation arrivalStation);
    List<Journey> searchByDepartureTime(Time departureTime);
    List<Journey> searchByArrivalTime(Time arrivalTime);
}
