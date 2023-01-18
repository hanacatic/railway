package ba.unsa.etf.rpr.Bussiness;

import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Journey;

import java.util.List;

public class JourneyManager {

    public List<Journey> getAll() throws RailwayException {
        return DaoFactory.journeyDao().getAll();
    }

    public void delete(int id) throws RailwayException {
        DaoFactory.journeyDao().delete(id);
    }

    public Journey add(Journey journey) throws RailwayException {
        return DaoFactory.journeyDao().add(journey);
    }

    public void update(Journey journey) throws RailwayException {
        DaoFactory.journeyDao().update(journey);
    }
}
