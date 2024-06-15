package utils;

import java.io.*;
import java.util.ArrayList;

public class MyFileReader {

    public static ArrayList<String[]> readFileLineByLineAndReturnArrayList(File fileToRead, String splitRegex) throws IOException {
        ArrayList<String[]> arrayList = new ArrayList<>();
        String currentLine = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToRead))) {
            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] lineItem = currentLine.split(splitRegex);
                arrayList.add(lineItem);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        return arrayList;
    }
    
}
