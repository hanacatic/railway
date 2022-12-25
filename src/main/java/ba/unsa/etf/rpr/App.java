package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.RailwayStationDaoSQLImpl;
import ba.unsa.etf.rpr.domain.RailwayStation;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        RailwayStation station = new RailwayStation();
        station.setName("Željeznička stanica Sarajevo");
        RailwayStationDaoSQLImpl dao = new RailwayStationDaoSQLImpl();
        try{
            dao.add(station);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(station.getId());
    }
}
