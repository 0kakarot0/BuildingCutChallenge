package services;

import utils.StringParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrintTabContent {
    public static void printTabContentAccordingToCommand(String tabContentToFind, ArrayList<String[]> tsvReader) {
        List<Integer> indexOfContentToPrint = StringParser.getListOfIntegerFromString(tabContentToFind);
        boolean printStarted = false;
        for (String[] current : tsvReader) {
            List<String> abc = Arrays.stream(current).toList();

            if (indexOfContentToPrint.size() == 1) {
                int indexToPrint = indexOfContentToPrint.get(0);
                if (abc.get(indexToPrint).contains("f")) {
                    String columnIndexToPrint = abc.get(indexToPrint-1);
                    System.out.println(columnIndexToPrint);
                    printStarted = true;
                } else if (printStarted){
                    String columnIndexToPrint = abc.get(indexToPrint-1);
                    System.out.println(columnIndexToPrint);
                } else {
                    System.out.println(tabContentToFind + "not found");
                }
            } else if (indexOfContentToPrint.size() < 3) {
                if (abc.contains("f0")) {
                    String columnOneIndexToPrint = abc.get(indexOfContentToPrint.get(0) - 1);
                    String columnTwoIndexToPrint = abc.get(indexOfContentToPrint.get(1) - 1);
                    System.out.println("Output in a Formatted Table:");
                    System.out.println("----------------------------");
                    StringParser.printFormattedTable("Tab One", "Tab Two", 10);
                    StringParser.printFormattedTable(columnOneIndexToPrint, columnTwoIndexToPrint, 10);
                    printStarted = true;
                } else if (printStarted) {
                    String columnOneIndexToPrint = abc.get(indexOfContentToPrint.get(0) - 1);
                    String columnTwoIndexToPrint = abc.get(indexOfContentToPrint.get(1) - 1);
                    StringParser.printFormattedTable(columnOneIndexToPrint, columnTwoIndexToPrint, 10);
                } else {
                    System.out.println(tabContentToFind + "not found");
                }
            }
        }
    }

}
