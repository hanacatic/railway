package ba.unsa.etf.rpr.Bussiness;

import ba.unsa.etf.rpr.Exceptions.RailwayException;

public class RailwayStationManager {
    public void validateStationName(String name) throws RailwayException {
        if(name == null || name.length() < 1 || name.length() > 255){
            throw new RailwayException("Station name must be between 1 and 255 chars.");
        }
    }

    public void validateStationAddress(String address) throws RailwayException {
        if(address == null || address.length() < 1 || address.length() > 255){
            throw new RailwayException("Station adress must be between 1 and 255 chars.");
        }
    }

}
