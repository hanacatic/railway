package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Bussiness.JourneyManager;
import ba.unsa.etf.rpr.Bussiness.RailwayStationManager;
import ba.unsa.etf.rpr.Bussiness.TrainManager;
import ba.unsa.etf.rpr.Exceptions.RailwayException;
import ba.unsa.etf.rpr.domain.Journey;
import ba.unsa.etf.rpr.domain.RailwayStation;
import ba.unsa.etf.rpr.domain.Train;
import org.apache.commons.cli.*;
import net.bytebuddy.asm.Advice;
import org.apache.commons.cli.*;
import java.sql.Date;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Time;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;
/**
 * Command Line Interface - CLI
 * @author Hana Catic
 */
public class App {
    private static final Option addTrain = new Option("t", "add-train", false, "Adding new train to Railway database");
    private static final Option addRailwayStation = new Option("r", "add-station", false, "Adding new railway station to Railway database");
    private static final Option addJourney = new Option("j", "add-journey", false, "Adding new journey to Railway database");

    private static final Option getTrains = new Option("getT", "get-train", false, "Printing all trains from Railway database");
    private static final Option getRailwayStations = new Option("getR", "get-station", false, "Printing all railway stations from Railway database");
    private static final Option getJourneys = new Option("getJ", "get-journey", false, "Printing all journeys from Railway database");
    public static void printFormattedOptions(Options options){
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar quote-maker.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }
    public static Options addOptions(){
        Options options = new Options();
        options.addOption(addTrain);
        options.addOption(addRailwayStation);
        options.addOption(addJourney);
        options.addOption(getTrains);
        options.addOption(getRailwayStations);
        options.addOption(getJourneys);
        return options;
    }
    public static Train searchThroughTrains(List<Train> trains, String trainName){
        Train train = null;
        train = trains.stream().filter(t -> t.getName().toLowerCase().equals(trainName.toLowerCase())).findAny().get();
        return train;
    }
    public static RailwayStation searchThroughStations(List<RailwayStation> stations, String stationName){
        RailwayStation station = null;
        station = stations.stream().filter(s-> s.getName().toLowerCase().equals(stationName.toLowerCase())).findAny().get();
        return station;
    }
    public static void main( String[] args ) throws Exception {
        Options options = addOptions();
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine c = commandLineParser.parse(options, args);
        if(c.hasOption(addTrain.getOpt()) || c.hasOption(addTrain.getLongOpt())){
            try{
                TrainManager trainManager = new TrainManager();
                Train train = new Train();
                train.setName(c.getArgList().get(0));
                train.setDateBought(new Date(Integer.parseInt(c.getArgList().get(1))-1900, Integer.parseInt(c.getArgList().get(2))-1, Integer.parseInt(c.getArgList().get(3))));
                trainManager.add(train);
                System.out.println("Train has been added successfully.");
            }catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println("Try again!");
                System.exit(1);
            }

        }
        else if (c.hasOption(addRailwayStation.getOpt()) || c.hasOption(addRailwayStation.getLongOpt())){
            try{
                RailwayStationManager stationManager = new RailwayStationManager();
                RailwayStation station = new RailwayStation();
                station.setName(c.getArgList().get(0));
                station.setAddress(c.getArgList().get(1));
                station.setCity(c.getArgList().get(2));
                station.setCountry(c.getArgList().get(3));
                stationManager.add(station);
                System.out.println("Railway station has been added successfully.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Try again!");
                System.exit(1);
            }
        }
        else if (c.hasOption(addJourney.getLongOpt()) || c.hasOption(addJourney.getLongOpt())){
            TrainManager trainManager = new TrainManager();
            Train train = null;
            try {
                train = searchThroughTrains(trainManager.getAll(), c.getArgList().get(0));
            }catch(Exception e) {
                System.out.println("There is no train with this name in the list!");
                System.out.println("Try again!");
                System.exit(1);
            }
            RailwayStationManager stationManager = new RailwayStationManager();
            List<RailwayStation> stations = stationManager.getAll();
            RailwayStation departureStation = null;
            RailwayStation arrivalStation = null;
            try{
                departureStation = searchThroughStations(stations, c.getArgList().get(1));
                arrivalStation = searchThroughStations(stations, c.getArgList().get(2));
            }
            catch(Exception e){
                System.out.println("There is no railway station with this name in the list!");
                System.out.println("Try again!");
                System.exit(1);
            }
            JourneyManager journeyManager = new JourneyManager();
            Journey journey = new Journey();
            try{
                journey.setTrain(train);
                journey.setDepartureStation(departureStation);
                journey.setArrivalStation(arrivalStation);
                journey.setDepartureDate(new Date(Integer.parseInt(c.getArgList().get(3)) - 1900, Integer.parseInt(c.getArgList().get(4)) - 1, Integer.parseInt(c.getArgList().get(5))));
                journey.setDepartureTime(new Time(Integer.parseInt(c.getArgList().get(6)), Integer.parseInt(c.getArgList().get(7)), 0));
                journey.setArrivalDate(new Date(Integer.parseInt(c.getArgList().get(8)) - 1900, Integer.parseInt(c.getArgList().get(9)) - 1, Integer.parseInt(c.getArgList().get(10))));
                journey.setArrivalTime(new Time(Integer.parseInt(c.getArgList().get(11)), Integer.parseInt(c.getArgList().get(12)),0));
                journeyManager.add(journey);
                System.out.println("Journey has been successfully added!");
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println("Try again!");
                System.exit(1);
            }
        }
        else if (c.hasOption(getTrains.getOpt()) || c.hasOption(getTrains.getLongOpt())){
            TrainManager trainManager = new TrainManager();
            trainManager.getAll().forEach(train -> System.out.println(train.getName()));
        }
        else if (c.hasOption(getRailwayStations.getOpt()) || c.hasOption(getRailwayStations.getLongOpt())){
            RailwayStationManager stationManager = new RailwayStationManager();
            stationManager.getAll().forEach(station->{
                System.out.println(station.toString());
            });
        }
        else  if (c.hasOption(getJourneys.getOpt()) || c.hasOption(getJourneys.getLongOpt())){
            JourneyManager journeyManager = new JourneyManager();
            journeyManager.getAll().forEach(journey -> {
                System.out.println("Train: " + journey.getTrain().getName() + "    Departure: " + journey.getDepartureStation().toString() + " " + journey.getDepartureDate().toString() + " " + journey.getDepartureTime().toString() + "    Arrival: " + journey.getArrivalStation().toString() + " " + journey.getArrivalDate().toString() + " " + journey.getArrivalTime());
            });
        }
        else {
            printFormattedOptions(options);
            System.exit(-1);
        }
    }
}
