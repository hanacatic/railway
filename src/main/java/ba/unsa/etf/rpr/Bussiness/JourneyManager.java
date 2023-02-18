package ba.unsa.etf.rpr.Bussiness;

import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.JourneyDao;
import ba.unsa.etf.rpr.domain.Journey;
import ba.unsa.etf.rpr.domain.RailwayStation;

import java.sql.Time;
import java.util.Date;
import java.util.List;
/**
 * Business Logic Layer for management of Journeys
 * @author Hana Catic
 * */
public class JourneyManager {
    /**
     * Validates departure and arrival stations of a journey
     * //@param journey - whose stations need to be validated
     * @throws RailwayException - train cannot leave and arrive at the same station
     * */
    public void validateStations(RailwayStation departureStation, RailwayStation arrivalStation) throws RailwayException {
        if(departureStation.equals(arrivalStation)){
            throw new RailwayException("Train cannot leave and arrive at the same station.");
        }
    }
    /**
     * Validates departure and arrival dates of a journey
     * //@param journey - whose dates need to be validated
     * @throws RailwayException - train cannot arrive before it leaves
     * */
    public void validateDates(java.sql.Date departureDate, java.sql.Date arrivalDate) throws RailwayException {
        if(departureDate.toLocalDate().isAfter(arrivalDate.toLocalDate())){
            throw new RailwayException("Date of the departure must be before the date of arrival or the same.");
        }
    }
    /**
     * Validates departure and arrival times of a journey
     * @param journey - whose times need to be validated
     * @throws RailwayException - train cannot arrive before it leaves
     * */
    public void validateTime(Journey journey) throws RailwayException {
        if(journey.getDepartureDate().equals(journey.getArrivalDate()) && journey.getDepartureTime().after(journey.getArrivalTime())){
            throw new RailwayException("Train must depart before it arrives.");
        }
    }
    /**
     * Gets all journeys from database
     * @throws RailwayException
     * */
    public List<Journey> getAll() throws RailwayException {
        return DaoFactory.journeyDao().getAll();
    }
    /**
     * Deletes an entry from journeys table in database
     * @param id - of journey to be deleted
     * @throws RailwayException
     * */
    public void delete(int id) throws RailwayException {
        DaoFactory.journeyDao().delete(id);
    }
    /**
     * Adds an entry to journeys table in database
     * @param journey - to be added
     * @throws RailwayException
     * */
    public Journey add(Journey journey) throws RailwayException {
        validateStations(journey.getDepartureStation(), journey.getArrivalStation());
        validateDates(journey.getDepartureDate(), journey.getArrivalDate());
        validateTime(journey);
        return DaoFactory.journeyDao().add(journey);
    }
    /**
     * Updates an entry in journeys table in database
     * @param journey - to be updated
     * @throws RailwayException
     * */
    public void update(Journey journey) throws RailwayException {
        validateStations(journey.getDepartureStation(), journey.getArrivalStation());
        validateDates(journey.getDepartureDate(), journey.getArrivalDate());
        validateTime(journey);
        DaoFactory.journeyDao().update(journey);
    }
    /**
     * Gets an entry from journeys table in database
     * @param id - of entry in database
     * @throws RailwayException
     * */
    public Journey getById(int id) throws RailwayException {
        return DaoFactory.journeyDao().getById(id);
    }
    public List<Journey> search(RailwayStation departureStation, RailwayStation arrivalStation, Date date, Time time, boolean arrival) throws RailwayException {
        validateStations(departureStation, arrivalStation);
        return DaoFactory.journeyDao().search(departureStation, arrivalStation, (java.sql.Date) date, time, arrival);
    }
}
