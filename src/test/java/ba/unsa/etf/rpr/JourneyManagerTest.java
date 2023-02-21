package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Bussiness.JourneyManager;
import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.JourneyDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Journey;
import ba.unsa.etf.rpr.domain.RailwayStation;
import ba.unsa.etf.rpr.domain.Train;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * Tests for JourneyManager class
 * @author Hana Catic
 * */
public class JourneyManagerTest {
    private JourneyManager journeyManager;
    private JourneyDaoSQLImpl journeyDaoSQLMock;
    private Journey journey;
    private List<Journey> journeys;
    private Train train;
    private RailwayStation station1;
    private RailwayStation station2;

    @BeforeEach
    public void initializeObjects(){
        journeyManager = Mockito.mock(JourneyManager.class);
        journey = new Journey();
        train = new Train();
        train.setName("Gordon");
        train.setDateBought(new Date(12000000));
        train.setId(12);
        station1 = new RailwayStation();
        station1.setCountry("Bosnia and Herzegovina");
        station1.setCity("Banja Luka");
        station1.setAddress("Prote Nikole Kostica");
        station1.setName("Railway Station Banja Luka");
        station1.setId(25);
        station2 = new RailwayStation();
        station2.setCountry("Bosnia and Herzegovina");
        station2.setCity("Sarajevo");
        station2.setAddress("Put života 2");
        station2.setName("Railway Station Sarajevo");
        station2.setId(20);
        journey.setTrain(train);
        journey.setDepartureStation(station1);
        journey.setArrivalStation(station2);
        journey.setArrivalDate(new Date(2023, 3, 18));
        journey.setDepartureDate(new Date(2023, 3, 18));
        journey.setDepartureTime(new Time(15000000));
        journey.setArrivalTime(new Time(15600000));
        journey.setId(1);
        journeyDaoSQLMock = Mockito.mock(JourneyDaoSQLImpl.class);
        journeys = new ArrayList<Journey>();
        journeys.add(journey);
    }
    /**
     * Testing validation of stations
     * @throws RailwayException
     * */
    @Test
    void validateStationsTest() throws RailwayException {
        try{
            Mockito.doCallRealMethod().when(journeyManager).validateStations(station1, station2);
        } catch (RailwayException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
        Mockito.doCallRealMethod().when(journeyManager).validateStations(station1, station1);
        RailwayException e = Assertions.assertThrows(RailwayException.class, ()->{
            journeyManager.validateStations(station1, station1);
        }, "Train cannot leave and arrive at the same station.");
        Assertions.assertEquals(e.getMessage(), "Train cannot leave and arrive at the same station.");
    }
    /**
     * Tests validation of dates
     * @throws RailwayException
     * */
    @Test
    void validateDatesTest() throws RailwayException {
        Date date1 = new Date(2023, 2, 23);
        Date date2 = new Date(2023, 2, 24);
        try{
            Mockito.doCallRealMethod().when(journeyManager).validateDates(date1, date2);
        } catch (RailwayException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
        Mockito.doCallRealMethod().when(journeyManager).validateDates(date2, date1);
        RailwayException e = Assertions.assertThrows(RailwayException.class, ()->{
           journeyManager.validateDates(date2, date1);
        }, "Date of the departure must be before the date of arrival or the same.");
        Assertions.assertEquals(e.getMessage(), "Date of the departure must be before the date of arrival or the same.");
    }
    /**
     * Tests adding a journey
     * @throws RailwayException
     * */
    @Test
    void addNewJourneyTest() throws RailwayException {
        Journey newJourney = new Journey(train, station2, station1, new Date(2023, 2, 28), new Date(2023,2,28), new Time(1900000), new Time(2300000));
        journeyManager.add(newJourney);

        Assertions.assertTrue(true);
        Mockito.verify(journeyManager).add(newJourney);
    }
    /**
     * Tests adding a journey that has a set id
     * @throws RailwayException
     * */
    @Test
    void addJourneyWithIdTest() throws RailwayException {
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::journeyDao).thenReturn(journeyDaoSQLMock);
        when(DaoFactory.journeyDao().getAll()).thenReturn(journeys);
        Mockito.doCallRealMethod().when(journeyManager).add(journey);

        Assertions.assertThrows(RailwayException.class, ()->{journeyManager.add(journey);});

        daoFactoryMockedStatic.verify(DaoFactory::journeyDao);
        Mockito.verify(journeyManager).add(journey);
        daoFactoryMockedStatic.close();
    }
    /**
     * Tests updating a journey
     * @throws RailwayException
     * */
    /*@Test
    void updateJourneyTest() throws RailwayException {
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::journeyDao).thenReturn(journeyDaoSQLMock);
        when(DaoFactory.journeyDao().getAll()).thenReturn(journeys);
        Mockito.doCallRealMethod().when(journeyManager).add(journey);
        Mockito.doCallRealMethod().when(journeyManager).update(journey);

        Journey newJourney = new Journey();
        newJourney.setTrain(journey.getTrain());
        newJourney.setDepartureStation(journey.getDepartureStation());
        newJourney.setArrivalStation(journey.getArrivalStation());
        newJourney.setDepartureDate(journey.getDepartureDate());
        newJourney.setArrivalDate(journey.getArrivalDate());
        newJourney.setDepartureTime(journey.getDepartureTime());
        newJourney.setArrivalTime(journey.getArrivalTime());
        journeyManager.add(newJourney);
        daoFactoryMockedStatic.verify(DaoFactory::journeyDao);
        Mockito.verify(journeyManager).add(newJourney);

        Assertions.assertNotNull(journey.getId());

        newJourney.setArrivalTime(new Time(newJourney.getArrivalTime().getTime()+100000));
        journeyManager.update(newJourney);
        Assertions.assertNotEquals(newJourney.getArrivalTime(), journey.getArrivalTime());

        daoFactoryMockedStatic.verify(DaoFactory::journeyDao);
        Mockito.verify(journeyManager).update(newJourney);
        daoFactoryMockedStatic.close();
    }*/
}
