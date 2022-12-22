package ba.unsa.etf.rpr.Exceptions;

public class RailwayException extends Exception{
    public RailwayException(String msg, Exception reason){
        super(msg, reason);
    }
    public RailwayException(String msg){
        super(msg);
    }
}
