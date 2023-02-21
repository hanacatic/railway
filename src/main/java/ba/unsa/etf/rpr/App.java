package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.Bussiness.TrainManager;
import org.apache.commons.cli.*;
import net.bytebuddy.asm.Advice;
import org.apache.commons.cli.*;
import java.sql.Date;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;
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
    public static void main( String[] args ) throws Exception {
        Options options = addOptions();
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine c = commandLineParser.parse(options, args);
        if(c.hasOption(addTrain.getOpt()) || c.hasOption(addTrain.getLongOpt())){
            System.out.println("Train");
        }
        else if (c.hasOption(getTrains.getOpt()) || c.hasOption(getTrains.getLongOpt())){
            TrainManager trainManager = new TrainManager();
            trainManager.getAll().forEach(train -> System.out.println(train.getName()));
        }
        else {
            printFormattedOptions(options);
            System.exit(-1);
        }
    }
}
