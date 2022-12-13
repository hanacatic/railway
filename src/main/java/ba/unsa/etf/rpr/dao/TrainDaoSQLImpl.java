package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Train;

import java.sql.*;
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
            PreparedStatement stmt = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Train train = new Train();
                train.setId(id);
                train.setName(rs.getString(2));
                train.setDateBought(rs.getDate(3));
                return train;
            }
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
