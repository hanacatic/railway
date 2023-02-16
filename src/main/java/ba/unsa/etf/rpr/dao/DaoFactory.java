package ba.unsa.etf.rpr.dao;
/**
 * Factory method for singleton implementation of DAOs
 * @author Hana Catic
 * */
public class DaoFactory {
    private static final RailwayStationDao railwayStationDao = RailwayStationDaoSQLImpl.getInstance();
    private static final TrainDao trainDao = TrainDaoSQLImpl.getInstance();
    private static final JourneyDao journeyDao = JourneyDaoSQLImpl.getInstance();

    private DaoFactory(){}
    /**
     * Get instance of RailwayStationDao
     * @return railwayStationDaoSQLImpl instance
     * */
    public static RailwayStationDao railwayStationDao(){
        return railwayStationDao;
    }
    /**
     * Get instance of TrainDao
     * @return TrainDaoSQLImpl instance
     * */
    public static TrainDao trainDao(){
        return trainDao;
    }
    /**
     * Get instance of JourneyDao
     * @return JourneyDaoSQLImpl instance
     * */
    public static JourneyDao journeyDao(){
        return journeyDao;
    }
}
