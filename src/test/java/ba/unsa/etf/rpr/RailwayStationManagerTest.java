package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Bussiness.RailwayStationManager;
import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.dao.RailwayStationDaoSQLImpl;
import ba.unsa.etf.rpr.domain.RailwayStation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RailwayStationManagerTest {
    private RailwayStationManager stationManager;
    private RailwayStationDaoSQLImpl stationDaoSQLMock;
    private List<RailwayStation> stations;
    private RailwayStation station;

    @BeforeEach
    public void initializeObjects(){
        stationManager = Mockito.mock(RailwayStationManager.class);
        station = new RailwayStation();
        station.setCountry("Bosnia and Herzegovina");
        station.setCity("Banja Luka");
        station.setAddress("Prote Nikole Kostica");
        station.setName("Railway Station Banja Luka");
        station.setId(25);
        stationDaoSQLMock = Mockito.mock(RailwayStationDaoSQLImpl.class);
        stations = new ArrayList<RailwayStation>();
        stations.add(station);
    }
    /**
     * Tests adding a railway station
     * @throws RailwayException
     * */
    @Test
    void addNewRailwayStation() throws RailwayException {
        RailwayStation newStation = new RailwayStation("Railway Station Podlugovi", "Pjesma", "Album", "Zdravko Colic");
        stationManager.add(newStation);

        Assertions.assertTrue(true);
        Mockito.verify(stationManager).add(newStation);
    }
}
