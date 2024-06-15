package services;

import utils.StringParser;

import java.util.*;
import java.util.stream.Collectors;

public class PrintCSVContent {
    private static Set<String> uniqueArtist = new HashSet<>();


    public static int printCommaSeparatedAccordingToCommand(String userCutCommand, String tabContentToFind, String limit, ArrayList<String[]> csvReader) {
        List<Integer> indexOfContentToPrint = StringParser.getListOfIntegerFromString(tabContentToFind);
        int limitOfRowsToPrint = StringParser.getIntegerFromString(limit);
        String initialWord = userCutCommand.substring(0, userCutCommand.indexOf(' '));
        if (userCutCommand.contains("uniq")) {
            initialWord = "unique";
        }
        return switch (initialWord) {
            case "cut" -> printInCaseOfCutCommand(csvReader, limitOfRowsToPrint, indexOfContentToPrint);
            case "tail" -> printInCaseOfTailCommand(csvReader, limitOfRowsToPrint, indexOfContentToPrint);
            case "unique" -> printInCaseOfUniqueCommand(csvReader, limitOfRowsToPrint, indexOfContentToPrint);
            default -> 0;
        };
    }

    private static int printInCaseOfTailCommand(ArrayList<String[]> csvReader, int limitOfRowsToPrint, List<Integer> indexOfContentToPrint) {
        int contentSize = csvReader.size();
        int listSize = 0;
        for (int i = contentSize - limitOfRowsToPrint; i < contentSize; i++) {
            String[] current = csvReader.get(i);
            ArrayList<String> getAllStrings = getAllTheListFromCSVContent(current);
            listSize = printColumnsIfCutCommandAtStart(limitOfRowsToPrint, indexOfContentToPrint, getAllStrings);
        }
        return listSize;
    }

    private static int printInCaseOfCutCommand(ArrayList<String[]> csvReader, int limitOfRowsToPrint, List<Integer> indexOfContentToPrint) {

        for (int i = 0; i < csvReader.size(); i++) {
            String[] current = csvReader.get(i);
            List<String> getAllStrings = getAllTheListFromCSVContent(current);
            printColumnsIfCutCommandAtStart(limitOfRowsToPrint, indexOfContentToPrint, getAllStrings);
        }
        return uniqueArtist.size();
    }

    private static int printInCaseOfUniqueCommand(ArrayList<String[]> csvReader, int limitOfRowsToPrint, List<Integer> indexOfContentToPrint) {

        for (int i = 0; i < csvReader.size(); i++) {
            String[] current = csvReader.get(i);
            List<String> uniqueList = getAllTheListFromCSVContent(current);
            printColumnsIfCutCommandAtStart(limitOfRowsToPrint, indexOfContentToPrint, uniqueList);
        }
        return uniqueArtist.size();
    }


    private static int printColumnsIfCutCommandAtStart(int limitOfRowsToPrint, List<Integer> indexOfContentToPrint, List<String> getAllStrings) {

        if (limitOfRowsToPrint != 0 && indexOfContentToPrint.size() == 1) {
            System.out.println(getAllStrings.get(indexOfContentToPrint.get(0) - 1));
            limitOfRowsToPrint--;
        } else if (limitOfRowsToPrint != 0 && indexOfContentToPrint.size() > 1) {
            String columnOneContent = getAllStrings.get(indexOfContentToPrint.get(0) - 1);
            String columnTwoContent = getAllStrings.get(indexOfContentToPrint.get(1) - 1);

            StringParser.printFormattedTable(columnOneContent, columnTwoContent, 45);
            limitOfRowsToPrint--;
        } else if (limitOfRowsToPrint == 0) {
            String stringToPrint = getAllStrings.get(indexOfContentToPrint.get(0) - 1);
            if (!stringToPrint.equals("Artist") ) {
                uniqueArtist.add(stringToPrint);
            }

        }
        return limitOfRowsToPrint;
    }



    private static ArrayList<String> getAllTheListFromCSVContent(String[] current) {
        ArrayList<String> getAllStrings = new ArrayList<>();
        for (String currentString : current) {
            String[] strings = currentString.split(",");
            getAllStrings.addAll(List.of(strings));
        }
        return getAllStrings;
    }

}
