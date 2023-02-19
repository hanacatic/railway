package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Bussiness.RailwayStationManager;
import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.RailwayStationDaoSQLImpl;
import ba.unsa.etf.rpr.domain.RailwayStation;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

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
    void addNewRailwayStationTest() throws RailwayException {
        RailwayStation newStation = new RailwayStation("Railway Station Podlugovi", "Pjesma", "Album", "Zdravko Colic");
        stationManager.add(newStation);

        Assertions.assertTrue(true);
        Mockito.verify(stationManager).add(newStation);
    }
    /**
     * Tests adding a railway station that has set id
     * */
    @Test
    void add() throws RailwayException {
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::railwayStationDao).thenReturn(stationDaoSQLMock);
        when(DaoFactory.railwayStationDao().getAll()).thenReturn(stations);
        Mockito.doCallRealMethod().when(stationManager).add(station);

        Assertions.assertThrows(RailwayException.class, ()->{stationManager.add(station);});

        daoFactoryMockedStatic.verify(DaoFactory::railwayStationDao);
        Mockito.verify(stationManager).add(station);
        daoFactoryMockedStatic.close();
    }
    /**
     * Tests updating a Railway Station
     *
     * */
    @Test
    void updateStationTest() throws RailwayException {
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::railwayStationDao).thenReturn(stationDaoSQLMock);
        when(DaoFactory.railwayStationDao().getAll()).thenReturn(stations);
        Mockito.doCallRealMethod().when(stationManager).add(station);
        Mockito.doCallRealMethod().when(stationManager).update(station);

        RailwayStation newStation = new RailwayStation(station.getName(), station.getAddress(), station.getCity(), station.getCountry());
        stationManager.add(newStation);
        daoFactoryMockedStatic.verify(DaoFactory::railwayStationDao);
        Mockito.verify(stationManager).add(newStation);

        Assertions.assertNotNull(newStation.getId());

        newStation.setCountry("Francuska");
        stationManager.update(newStation);
        Assertions.assertEquals("Francuska", newStation.getCountry());

        daoFactoryMockedStatic.verify(DaoFactory::railwayStationDao);
        Mockito.verify(stationManager).update(newStation);
        daoFactoryMockedStatic.close();
    }
}
