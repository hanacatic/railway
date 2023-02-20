package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Bussiness.JourneyManager;
import ba.unsa.etf.rpr.Bussiness.TrainManager;
import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.TrainDaoSQLImpl;
import ba.unsa.etf.rpr.domain.RailwayStation;
import ba.unsa.etf.rpr.domain.Train;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
/**
 * Tests for TrainManager class
 * @author Hana Catic
 * */
public class TrainManagerTest {
    private TrainManager trainManager;
    private TrainDaoSQLImpl trainDaoSQLMock;
    private Train train;
    private List<Train> trains;

    @BeforeEach
    public void initializeObjects(){
        trainManager = Mockito.mock(TrainManager.class);
        train = new Train();
        train.setName("Gordon");
        train.setDateBought(new Date(12000000));
        train.setId(12);
        trainDaoSQLMock = Mockito.mock(TrainDaoSQLImpl.class);
        trains = new ArrayList<Train>();
        trains.add(train);
    }
    /**
     * Tests validation of train name
     * */
    @Test
    void validateTrainNameTest() throws RailwayException {
        String correctName = "Duck";
        try{
            Mockito.doCallRealMethod().when(trainManager).validateTrainName(correctName);
        }catch(RailwayException e){
            e.printStackTrace();
            Assertions.assertTrue(false);
        }

        String incorrectNameShort = "";
        Mockito.doCallRealMethod().when(trainManager).validateTrainName(incorrectNameShort);
        RailwayException eShort = Assertions.assertThrows(RailwayException.class, ()->{
            trainManager.validateTrainName(incorrectNameShort);
        }, "Train name must be between 1 and 60 chars.");
        Assertions.assertEquals(eShort.getMessage(), "Train name must be between 1 and 60 chars.");

        String incorrectNameLong = RandomStringUtils.randomAlphabetic(60);
        Mockito.doCallRealMethod().when(trainManager).validateTrainName(incorrectNameLong);
        RailwayException eLong = Assertions.assertThrows(RailwayException.class, ()->{
            trainManager.validateTrainName(incorrectNameShort);
        }, "Train name must be between 1 and 60 chars.");
        Assertions.assertEquals(eLong.getMessage(), "Train name must be between 1 and 60 chars.");
    }
    /**
     * Tests validation of date bought
     * */
    @Test
    void validateDateBought() throws RailwayException {
        Date validDate = new Date(2023, 2, 20);
        try{
            Mockito.doCallRealMethod().when(trainManager).validateTrainDateBought(validDate);
        } catch (RailwayException e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
        LocalDate temp = LocalDate.now(ZoneId.systemDefault()).plusDays(1);
        Date invalidDate = new Date(temp.getYear(), temp.getMonth().getValue(), temp.getDayOfMonth());
        Mockito.doCallRealMethod().when(trainManager).validateTrainDateBought(invalidDate);
        RailwayException e = Assertions.assertThrows(RailwayException.class, ()->{
            trainManager.validateTrainDateBought(invalidDate);
        }, "Date of train purchase cannot be in the future.");
        Assertions.assertEquals(e.getMessage(), "Date of train purchase cannot be in the future.");
    }
    /**
     * Tests adding a train
     * @throws RailwayException
     * */
    @Test
    void addNewTrainTest() throws RailwayException {
        Train newTrain = new Train("Emily", new Date(120000000));
        trainManager.add(newTrain);
        Assertions.assertTrue(true);
        Mockito.verify(trainManager).add(newTrain);
    }
    /**
     * Tests adding a train that has a set id
     * @throws RailwayException
     * */
    @Test
    void addTrainWithIdTest() throws RailwayException {
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::trainDao).thenReturn(trainDaoSQLMock);
        when(DaoFactory.trainDao().getAll()).thenReturn(trains);
        Mockito.doCallRealMethod().when(trainManager).add(train);

        Assertions.assertThrows(RailwayException.class, () -> {trainManager.add(train);});

        daoFactoryMockedStatic.verify(DaoFactory::trainDao);
        Mockito.verify(trainManager).add(train);
        daoFactoryMockedStatic.close();
    }
    /**
     * Tests adding a train that doesn't have a set id
     * @throws RailwayException
     * */
    @Test
    void addTrainNoIdTest() throws RailwayException {
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::trainDao).thenReturn(trainDaoSQLMock);
        when(DaoFactory.trainDao().getAll()).thenReturn(trains);
        Mockito.doCallRealMethod().when(trainManager).add(train);

        Train newTrain = new Train(train.getName(), train.getDateBought());
        Assertions.assertDoesNotThrow(()->{trainManager.add(newTrain);});

        daoFactoryMockedStatic.verify(DaoFactory::trainDao);
        Mockito.verify(trainManager).add(newTrain);
        daoFactoryMockedStatic.close();
    }
    /**
     * Tests updating a train
     * @throws RailwayException
     * */
    /*@Test
    void updateTrainTest() throws RailwayException {
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::trainDao).thenReturn(trainDaoSQLMock);
        when(DaoFactory.trainDao().getAll()).thenReturn(trains);
        when(trainManager.add(any(Train.class))).thenCallRealMethod();
        when(trainManager.update(any(Train.class))).thenCallRealMethod();

        Train newTrain = new Train(train.getName(), train.getDateBought());

        trainManager.add(newTrain);
        Assertions.assertNotNull(newTrain.getId());
        daoFactoryMockedStatic.verify(DaoFactory::trainDao);
        Mockito.verify(trainManager).add(newTrain);

        newTrain.setName("Ben");
        trainManager.update(newTrain);
        Assertions.assertEquals("Ben", newTrain.getName());

        daoFactoryMockedStatic.verify(DaoFactory::trainDao);
        Mockito.verify(trainManager).update(newTrain);
        daoFactoryMockedStatic.close();
    }*/
}
