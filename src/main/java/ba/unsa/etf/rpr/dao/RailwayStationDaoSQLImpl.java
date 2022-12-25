package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.RailwayStation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RailwayStationDaoSQLImpl extends AbstractDao<RailwayStation> implements RailwayStationDao{


    public RailwayStationDaoSQLImpl() {
        super("railwayStations");
    }

    @Override
    public RailwayStation row2object(ResultSet rs) {
        return null;
    }

    @Override
    public Map<String, Object> object2row(RailwayStation object) {
        return null;
    }

    @Override
    public RailwayStation searchByName(String name) {
        return null;
    }
}
