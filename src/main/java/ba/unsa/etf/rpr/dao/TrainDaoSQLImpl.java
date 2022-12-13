package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Train;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

public class TrainDaoSQLImpl implements TrainDao{
    private Connection connection;
    public TrainDaoSQLImpl(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql6583526", "sql6583526", "");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public Train add(Train item) {
        return null;
    }

    @Override
    public Train update(Train item) {
        return null;
    }

    @Override
    public Train getById(int id) {
        try{
            String query = "SELECT * FROM Trains WHERE id = ?";
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Train> getAll() {
        return null;
    }

    @Override
    public Train searchByName(String name) {
        return null;
    }
}
