package ba.unsa.etf.rpr.Bussiness;

import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Journey;

import java.util.List;

public class JourneyManager {
    public void validateStations(Journey journey) throws RailwayException {
        if(journey.getDepartureStation().equals(journey.getArrivalStation())){
            throw new RailwayException("Train cannot leave and arrive at the same station.");
        }
    }
    public void validateDate(Journey journey) throws RailwayException {
        if(journey.getDepartureDate().toLocalDate().isAfter(journey.getArrivalDate().toLocalDate())){
            throw new RailwayException("Date of the departure must be before the date of arrival or the same.");
        }
    }

    public void validateTime(Journey journey) throws RailwayException {
        if(journey.getDepartureDate().equals(journey.getArrivalDate()) && journey.getDepartureTime().after(journey.getArrivalTime())){
            throw new RailwayException("Train must depart before it arrives.");
        }
    }

    public List<Journey> getAll() throws RailwayException {
        return DaoFactory.journeyDao().getAll();
    }

    public void delete(int id) throws RailwayException {
        DaoFactory.journeyDao().delete(id);
    }

    public Journey add(Journey journey) throws RailwayException {
        validateStations(journey);
        validateDate(journey);
        validateTime(journey);
        return DaoFactory.journeyDao().add(journey);
    }

    public void update(Journey journey) throws RailwayException {
        validateStations(journey);
        validateDate(journey);
        validateTime(journey);
        DaoFactory.journeyDao().update(journey);
    }

    public Journey getById(int id) throws RailwayException {
        return DaoFactory.journeyDao().getById(id);
    }
}
