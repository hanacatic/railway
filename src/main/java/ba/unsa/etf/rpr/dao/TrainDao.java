package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Train;

public interface TrainDao extends Dao{
    Train searchByName(String name);
}
