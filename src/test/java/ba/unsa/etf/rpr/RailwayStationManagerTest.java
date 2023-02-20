package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Bussiness.RailwayStationManager;
import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.RailwayStationDaoSQLImpl;
import ba.unsa.etf.rpr.domain.RailwayStation;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
     * Tests validation of station name
     * @throws  RailwayException
     * */
    @Test
    void validateStationNameTest() throws RailwayException {
        String correctName = "Railway Station Sarajevo";
        try{
            Mockito.doCallRealMethod().when(stationManager).validateStationName(correctName);
        } catch (RailwayException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
        String incorrectNameShort = "";
        Mockito.doCallRealMethod().when(stationManager).validateStationName(incorrectNameShort);
        RailwayException eShort = Assertions.assertThrows(RailwayException.class, ()->{
            stationManager.validateStationName(incorrectNameShort);
        },"Station name must be between 1 and 255 chars.");
        Assertions.assertEquals(eShort.getMessage(), "Station name must be between 1 and 255 chars.");

        String incorrectNameLong = RandomStringUtils.randomAlphabetic(256);
        Mockito.doCallRealMethod().when(stationManager).validateStationName(incorrectNameLong);
        RailwayException eLong = Assertions.assertThrows(RailwayException.class, ()->{
            stationManager.validateStationName(incorrectNameLong);
        },"Station name must be between 1 and 255 chars.");
        Assertions.assertEquals(eLong.getMessage(), "Station name must be between 1 and 255 chars.");
    }
    /**
     * Tests validation of station address
     * @throws RailwayException
     * */
    @Test
    void validateStationAddressTest() throws RailwayException {
        String correctAddress = "Put zivota 2";
        try{
            Mockito.doCallRealMethod().when(stationManager).validateStationAddress(correctAddress);
        } catch (RailwayException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }

        String incorrectAddressShort = "";
        Mockito.doCallRealMethod().when(stationManager).validateStationAddress(incorrectAddressShort);
        RailwayException eShort = Assertions.assertThrows(RailwayException.class, ()->{
            stationManager.validateStationAddress(incorrectAddressShort);
        }, "Station address must be between 1 and 255 chars.");
        Assertions.assertEquals(eShort.getMessage(), "Station address must be between 1 and 255 chars.");

        String incorrectAddressLong = RandomStringUtils.randomAlphabetic(256);
        Mockito.doCallRealMethod().when(stationManager).validateStationAddress(incorrectAddressLong);
        RailwayException eLong = Assertions.assertThrows(RailwayException.class, ()->{
            stationManager.validateStationAddress(incorrectAddressLong);
        }, "Station address must be between 1 and 255 chars.");
        Assertions.assertEquals(eLong.getMessage(), "Station address must be between 1 and 255 chars.");
    }
    /**
     * Tests validation of station city
     * @throws RailwayException
     * */
    @Test
    void validateStationCityTest() throws RailwayException {
        String correctCity = "Sarajevo";
        try{
            Mockito.doCallRealMethod().when(stationManager).validateStationCity(correctCity);
        } catch (RailwayException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }

        String incorrectCityShort = "";
        Mockito.doCallRealMethod().when(stationManager).validateStationCity(incorrectCityShort);
        RailwayException eShort = Assertions.assertThrows(RailwayException.class, ()->{
           stationManager.validateStationCity(incorrectCityShort);
        }, "Station city name must be between 1 and 45 chars.");
        Assertions.assertEquals(eShort.getMessage(), "Station city name must be between 1 and 45 chars.");

        String incorrectCityLong = RandomStringUtils.randomAlphabetic(46);
        Mockito.doCallRealMethod().when(stationManager).validateStationCity(incorrectCityLong);
        RailwayException eLong = Assertions.assertThrows(RailwayException.class, ()->{
            stationManager.validateStationCity(incorrectCityLong);
        }, "Station city name must be between 1 and 45 chars.");
        Assertions.assertEquals(eLong.getMessage(), "Station city name must be between 1 and 45 chars.");
    }
    /**
     * Tests validation of station country
     * @throws RailwayException
     * */
    @Test
    void validateStationCountry() throws RailwayException {
        String correctCountry = "Bosnia and Herzegovina";
        try{
            Mockito.doCallRealMethod().when(stationManager).validateStationCountry(correctCountry);
        } catch (RailwayException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
        String incorrectCountryShort = "";
        Mockito.doCallRealMethod().when(stationManager).validateStationCountry(incorrectCountryShort);
        RailwayException eShort = Assertions.assertThrows(RailwayException.class, ()->{
            stationManager.validateStationCountry(incorrectCountryShort);
        },"Station country name must be between 1 and 255 chars.");
        Assertions.assertEquals(eShort.getMessage(), "Station country name must be between 1 and 255 chars.");

        String incorrectCountryLong = RandomStringUtils.randomAlphabetic(256);
        Mockito.doCallRealMethod().when(stationManager).validateStationCountry(incorrectCountryLong);
        RailwayException eLong = Assertions.assertThrows(RailwayException.class, ()->{
            stationManager.validateStationCountry(incorrectCountryLong);
        },"Station country name must be between 1 and 255 chars.");
        Assertions.assertEquals(eLong.getMessage(), "Station country name must be between 1 and 255 chars.");
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
    void addRailwayStationWithIdTest() throws RailwayException {
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
    /*@Test
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
    }*/
}
