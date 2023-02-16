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
