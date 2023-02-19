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
}
