package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Train;
/**
 * Dao interface for train domain bean
 * @author Hana Catic
 * */
public interface TrainDao extends Dao{
    /**
     * Find a train by name
     * @param name of train to look for in database
     * @return train of given name
     * */
    Train searchByName(String name);
}
