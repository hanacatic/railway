package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.RailwayStation;

/**
 * Dao interface for railway station domain bean
 * @author Hana Catic
 * */
public interface RailwayStationDao extends Dao{
    /**
     * Find a railway station by name
     * @param name of railway station to look for in database
     * @return railway stations of given name
     * */
    RailwayStation searchByName(String name);
}
