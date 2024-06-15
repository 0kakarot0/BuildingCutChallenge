package services;

import utils.StringParser;

import java.util.*;
import java.util.stream.Collectors;

public class PrintCSVContent {
    private static Set<String> uniqueArtist = new HashSet<>();

    public static int printCommaSeparatedAccordingToCommand(String userCutCommand, String tabContentToFind, String limit, List<String[]> csvReader) {
        List<Integer> indexOfContentToPrint = StringParser.getListOfIntegerFromString(tabContentToFind);
        int limitOfRowsToPrint = StringParser.getIntegerFromString(limit);
        String initialWord = userCutCommand.split(" ")[0];
        if (userCutCommand.contains("uniq")) {
            initialWord = "unique";
        }
        return switch (initialWord) {
            case "cut" -> processCSVContent(csvReader, limitOfRowsToPrint, indexOfContentToPrint, false);
            case "tail" -> printTail(csvReader, limitOfRowsToPrint, indexOfContentToPrint);
            case "unique" -> processCSVContent(csvReader, limitOfRowsToPrint, indexOfContentToPrint, true);
            default -> 0;
        };
    }

    private static int printTail(List<String[]> csvReader, int limitOfRowsToPrint, List<Integer> indexOfContentToPrint) {
        int contentSize = csvReader.size();
        int listSize = 0;
        for (int i = contentSize - limitOfRowsToPrint; i < contentSize; i++) {
            String[] current = csvReader.get(i);
            List<String> getAllStrings = getAllTheListFromCSVContent(current);
            listSize = printColumns(getAllStrings, limitOfRowsToPrint, indexOfContentToPrint, false);
        }
        return listSize;
    }

    private static int processCSVContent(List<String[]> csvReader, int limitOfRowsToPrint, List<Integer> indexOfContentToPrint, boolean isUnique) {
        for (String[] current : csvReader) {
            List<String> getAllStrings = getAllTheListFromCSVContent(current);
            limitOfRowsToPrint = printColumns(getAllStrings, limitOfRowsToPrint, indexOfContentToPrint, isUnique);
        }
        return uniqueArtist.size();
    }

    private static int printColumns(List<String> getAllStrings, int limitOfRowsToPrint, List<Integer> indexOfContentToPrint, boolean isUnique) {
        if (limitOfRowsToPrint > 0) {
            if (indexOfContentToPrint.size() == 1) {
                System.out.println(getAllStrings.get(indexOfContentToPrint.get(0) - 1));
            } else if (indexOfContentToPrint.size() > 1) {
                String columnOneContent = getAllStrings.get(indexOfContentToPrint.get(0) - 1);
                String columnTwoContent = getAllStrings.get(indexOfContentToPrint.get(1) - 1);
                StringParser.printFormattedTable(columnOneContent, columnTwoContent, 45);
            }
            limitOfRowsToPrint--;
        } else if (isUnique) {
            String stringToPrint = getAllStrings.get(indexOfContentToPrint.get(0) - 1);
            if (!"Artist".equals(stringToPrint)) {
                uniqueArtist.add(stringToPrint);
            }
        }
        return limitOfRowsToPrint;
    }

    private static List<String> getAllTheListFromCSVContent(String[] current) {
        List<String> getAllStrings = new ArrayList<>();
        for (String currentString : current) {
            Collections.addAll(getAllStrings, currentString.split(","));
        }
        return getAllStrings;
    }
}
