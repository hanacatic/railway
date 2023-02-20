package ba.unsa.etf.rpr;

import org.apache.commons.cli.*;
/**
 * Hello world!
 *
 */
public class App {
    private static final Option addTrain = new Option("t", "add-train", false, "Adding new train to Railway database");
    private static final Option addRailwayStation = new Option("r", "add-station", false, "Adding new railway station to Railway database");
    private static final Option addJourney = new Option("j", "add-journey", false, "Adding new journey to Railway database");

    private static final Option getTrains = new Option("getT", "get-train", false, "Printing all trains from Railway database");
    private static final Option getRailwayStations = new Option("getR", "get-station", false, "Printing all railway stations from Railway database");
    private static final Option getJourneys = new Option("getJ", "get-journey", false, "Printing all journey from Railway database");

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
    public static void main( String[] args ) throws Exception {
        Options options = addOptions();
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine c = commandLineParser.parse(options, args);

    }
}
