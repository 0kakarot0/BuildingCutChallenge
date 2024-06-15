package commandline.cut_tool_command_line;

import services.PrintCSVContent;
import services.PrintTabContent;
import utils.MyFileReader;
import utils.ParseCommands;
import utils.UserInput;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CutToolChallenge {
    private static String filePathForCommaSeprated = "src/main/resources/challenge-cut/fourchords.csv";
    private static String filePathForTabSeprated = "src/main/resources/challenge-cut/sample.tsv";
    private static String filePath = "src/main/resources/challenge-cut/";


    public static void main(String[] args) throws IOException {
        String columnsToPrintKey = "columnsToPrint";
        String delimiterKey = "delimiter";
        String filenameKey = "filename";
        String printlimiterKey = "printlimiter";

        String userCutCommand = UserInput.getUserCommandInput();
        Map<String, String> parsedCommands = ParseCommands.parseCutCommand(userCutCommand);
        String commandIdentifier = parsedCommands.get(columnsToPrintKey);
        String delimiter = parsedCommands.get(delimiterKey);
        String fileName = parsedCommands.get(filenameKey);
        String limiter = parsedCommands.get(printlimiterKey);

        printContentAccordingToCutCommand(userCutCommand, commandIdentifier, delimiter, fileName, limiter);

    }


    private static void printContentAccordingToCutCommand(String userCutCommand, String commandIdentifier, String delimiter, String fileName, String limiter) throws IOException {
        File myFile = new File(filePath + fileName);
        printFileColumnContent(userCutCommand, commandIdentifier, myFile, limiter);

   /*     if (userCutCommand.contains("-d") && userCutCommand.contains(delimiter)) {
            printFileColumnContent(userCutCommand, commandIdentifier, myFile, limiter);
        } else if (userCutCommand.contains("-f") && userCutCommand.contains(delimiter)) {
            printFileColumnContent(userCutCommand, commandIdentifier, myFile, limiter);
        } else {
            if (userCutCommand.contains(".csv"))
                System.out.println("printing default tab content as no delimiter For CSV was provided");
            printFileColumnContent(userCutCommand, commandIdentifier, myFile, limiter);
        }*/

    }


    private static void printFileColumnContent(String userCutCommand, String tabContentToFind, File fileToRead, String limit) throws IOException {

        if (fileToRead.getName().contains("tsv")) {
            String regexForTSV = "\t";
            ArrayList<String[]> tsvReader = MyFileReader.readFileLineByLineAndReturnArrayList(fileToRead, regexForTSV);
            PrintTabContent.printTabContentAccordingToCommand(tabContentToFind, tsvReader);
        }

        if (fileToRead.getName().contains("csv")) {
            String regexForCSV = "\n";
            ArrayList<String[]> csvReader = MyFileReader.readFileLineByLineAndReturnArrayList(fileToRead, regexForCSV);
            PrintCSVContent.printCommaSeparatedAccordingToCommand(userCutCommand, tabContentToFind, limit, csvReader);
        }


    }
}
