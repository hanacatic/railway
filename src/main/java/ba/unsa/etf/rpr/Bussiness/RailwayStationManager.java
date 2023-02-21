package ba.unsa.etf.rpr.Bussiness;

import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.RailwayStation;

import java.util.List;
/**
 * Business Logic Layer for management of RailwayStations
 * @author Hana Catic
 * */
public class RailwayStationManager {
    /**
     * Validates railway station name
     * @param name of railway station
     * @throws RailwayException - station name must be between 1 and 255 chars
     * */
    public void validateStationName(String name) throws RailwayException {
        if(name == null || name.length() < 1 || name.length() > 255){
            throw new RailwayException("Station name must be between 1 and 255 chars.");
        }
    }
    /**
     * Validates railway station address
     * @param address of railway station
     * @throws RailwayException - station address must be between 1 and 255 chars
     * */
    public void validateStationAddress(String address) throws RailwayException {
        if(address == null || address.length() < 1 || address.length() > 255){
            throw new RailwayException("Station address must be between 1 and 255 chars.");
        }
    }
    /**
     * Validates railway station city
     * @param city in which the railway station is
     * @throws RailwayException - city must be between 1 and 45 chars
     * */
    public void validateStationCity(String city) throws RailwayException {
        if(city == null || city.length() < 1 || city.length() > 45){
            throw new RailwayException("Station city name must be between 1 and 45 chars.");
        }
    }
    /**
     * Validates railway station country
     * @param country in which the railway station is
     * @throws RailwayException - country must be between 1 and 255 chars
     * */
    public void validateStationCountry(String country) throws RailwayException {
        if(country == null || country.length() < 1 || country.length() > 255){
            throw new RailwayException("Station country name must be between 1 and 255 chars.");
        }
    }
    /**
     * Adds and entry to railwayStations table in database
     * @param station to be added
     * @throws RailwayException
     * */
    public RailwayStation add(RailwayStation station) throws RailwayException {
        if(station.getId() != 0){
            throw new RailwayException("Station with Id cannot be added. Id is autogenerated.");
        }
        validateStationName(station.getName());
        validateStationAddress(station.getAddress());
        validateStationCity(station.getCity());
        validateStationCountry(station.getCountry());
        try{
            return DaoFactory.railwayStationDao().add(station);
        }catch(RailwayException e){
            if(e.getMessage().contains("UNIQUE")){
                throw new RailwayException("Station with the same name exists.");
            }
            throw e;
        }
    }
    /**
     * Deletes an entry from railwayStations table in database
     * @param stationId of railway station to be deleted
     * @throws  RailwayException
     * */
    public void delete(int stationId) throws RailwayException {
        try{
            DaoFactory.railwayStationDao().delete(stationId);
        } catch (RailwayException e) {
            if(e.getMessage().contains("FOREIGN KEY")){
                throw new RailwayException("Cannot delete a station related to journeys. Delete all journeys related to a station before deleting a station.");
            }
            throw e;
        }
    }
    /**
     * Updates an entry in railwayStations table in database
     * @param station to be updated
     * @throws RailwayException
     * */
    public RailwayStation update(RailwayStation station) throws RailwayException {
        validateStationName(station.getName());
        validateStationAddress(station.getAddress());
        validateStationCity(station.getCity());
        validateStationCountry(station.getCountry());
        return DaoFactory.railwayStationDao().update(station);
    }
    /**
     * Gets all railwayStations from database
     * @throws RailwayException
     * */
    public List<RailwayStation> getAll() throws RailwayException {
        return DaoFactory.railwayStationDao().getAll();
    }
    /**
     * Gets an entry from railwayStations table in database
     * @param id of entry in database
     * @throws RailwayException
     * */
    public RailwayStation getById(int id) throws RailwayException {
        return DaoFactory.railwayStationDao().getById(id);
    }
}
