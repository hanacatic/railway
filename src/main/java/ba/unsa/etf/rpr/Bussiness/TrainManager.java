package ba.unsa.etf.rpr.Bussiness;

import ba.unsa.etf.rpr.Exceptions.RailwayException;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class TrainManager {
    public void validateTrainName(String name) throws RailwayException {
        if(name == null || name.length() > 60 || name.length() < 1){
            throw new RailwayException("Train name must be between 1 and 60 chars.");
        }
    }
    public void validateTrainDateBought(Date dateBought) throws RailwayException {
        if(dateBought == null && dateBought.toLocalDate().isAfter(LocalDate.now(ZoneId.systemDefault()))){
            throw new RailwayException("Date of train purchase can not be in the future.");
        }
    }
}
