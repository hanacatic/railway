package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Bussiness.TrainManager;
import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.TrainDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Train;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

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
     * Tests adding a train
     * @throws RailwayException
     * */
    @Test
    void addNewTrain() throws RailwayException {
        Train newTrain = new Train("Emily", new Date(120000000));
        trainManager.add(newTrain);
        Assertions.assertTrue(true);
        Mockito.verify(trainManager).add(newTrain);
    }
    /**
     * Tests adding a train that has set id
     * @throws RailwayException
     * */
    @Test
    void add() throws RailwayException {
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);
        daoFactoryMockedStatic.when(DaoFactory::trainDao).thenReturn(trainDaoSQLMock);
        when(DaoFactory.trainDao().getAll()).thenReturn(trains);
        Mockito.doCallRealMethod().when(trainManager).add(train);
        Assertions.assertThrows(RailwayException.class, () -> {trainManager.add(train);});
        daoFactoryMockedStatic.verify(DaoFactory::trainDao);
        Mockito.verify(trainManager).add(train);
        daoFactoryMockedStatic.close();
    }

}