package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.RailwayStation;
import ba.unsa.etf.rpr.domain.Train;

public class DaoFactory {
    private static final RailwayStationDao railwayStationDao = RailwayStationDaoSQLImpl.getInstance();
    private static final TrainDao trainDao = TrainDaoSQLImpl.getInstance();
    private static final JourneyDao journeyDao = JourneyDaoSQLImpl.getInstance();

    private DaoFactory(){}

    public static RailwayStationDao railwayStationDao(){
        return railwayStationDao;
    }

    public static TrainDao trainDao(){
        return trainDao;
    }

    public static JourneyDao journeyDao(){
        return journeyDao;
    }
}
