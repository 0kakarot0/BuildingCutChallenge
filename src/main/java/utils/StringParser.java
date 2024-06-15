package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParser {
    public static int getIntegerFromString(String tabContentToFind) {
        String regex = "[\\D]";
        return Integer.parseInt(tabContentToFind.replaceAll(regex, ""));
    }

    public static List<Integer> getListOfIntegerFromString(String columnToPrint) {
        List<Integer> columnIntValues = new ArrayList<>();

        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(columnToPrint);
        while (matcher.find()) {
            columnIntValues.add(Integer.parseInt(matcher.group()));
        }
        if (columnIntValues.isEmpty()) {
            throw new NumberFormatException("No Number found in the string" + columnToPrint);
        }
        return columnIntValues;
    }

    public static void printFormattedTable(String column1, String column2, int spaceBetweenColumns) {
        String concatedString = "| %-" + spaceBetweenColumns + "s | %-" + spaceBetweenColumns + "s |%n";
        System.out.printf(concatedString, column1, column2);
    }
}
