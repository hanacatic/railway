package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.RailwayStationDaoSQLImpl;
import ba.unsa.etf.rpr.domain.RailwayStation;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args )
    {
        RailwayStation station = new RailwayStation();
        station.setName("Željeznička stanica Mostar");
        RailwayStationDaoSQLImpl dao = new RailwayStationDaoSQLImpl();
        dao.add(station);
        System.out.println(station.getId());
    }
}
