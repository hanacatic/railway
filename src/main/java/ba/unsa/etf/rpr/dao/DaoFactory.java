package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.RailwayStation;

public class DaoFactory {
    private static final RailwayStationDao railwayStationDao = new RailwayStationDaoSQLImpl();
    private static final TrainDao trainDao = new TrainDaoSQLImpl();
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
