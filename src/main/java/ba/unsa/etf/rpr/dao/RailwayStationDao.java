package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.domain.RailwayStation;

import java.util.List;

/**
 * Dao interface for railway station domain bean
 * @author Hana Catic
 * */
public interface RailwayStationDao extends Dao<RailwayStation>{
    /**
     * Find a railway station by name
     * @param name of railway station to look for in database
     * @return railway station of given name
     * */
    RailwayStation searchByName(String name) throws RailwayException;
    /**
     * Find a railway station by city
     * @param city in which to look for railway stations
     * @return list of railway stations in a given city
     * */
    List<RailwayStation> searchByCity(String city) throws RailwayException;
    /**
     * Find a railway station by city
     * @param country in which to look for railway stations
     * @return list of railway stations in a given country
     * */
    List<RailwayStation> searchByCountry(String country) throws RailwayException;
}
