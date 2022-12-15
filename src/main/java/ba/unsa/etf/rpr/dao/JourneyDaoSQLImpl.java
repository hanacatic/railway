package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Journey;
import ba.unsa.etf.rpr.domain.RailwayStation;
import ba.unsa.etf.rpr.domain.Train;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class JourneyDaoSQLImpl implements JourneyDao{
    @Override
    public Journey add(Journey item) {
        return null;
    }

    @Override
    public Journey update(Journey item) {
        return null;
    }

    @Override
    public Journey getById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Journey> getAll() {
        return null;
    }

    @Override
    public List<Journey> searchByTrain(Train train) {
        return null;
    }

    @Override
    public List<Journey> searchByDepartureStation(RailwayStation departureStation) {
        return null;
    }

    @Override
    public List<Journey> searchByArrivalStation(RailwayStation arrivalStation) {
        return null;
    }

    @Override
    public List<Journey> searchByDepartureTime(Date departureDate) {
        return null;
    }

    @Override
    public List<Journey> searchByArrivalTime(Date arrivalDate) {
        return null;
    }

    @Override
    public List<Journey> searchByDepartureTime(Time departureTime) {
        return null;
    }

    @Override
    public List<Journey> searchByArrivalTime(Time arrivalTime) {
        return null;
    }
}
