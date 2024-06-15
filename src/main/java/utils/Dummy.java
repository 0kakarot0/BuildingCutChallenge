package utils;

import model.CommandDetails;

import java.util.Scanner;

public class Dummy {
//    public static void main(String[] args) {
////        String myString = "cut -f1,2 sample.tsv";
////        String newString = myString.replaceAll("cut -","");
////         newString = newString.replaceAll(" sample.tsv","");
////
////        System.out.println(newString);
//
//    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Enter the command:");
//        String command = scanner.nextLine();
//
//        CommandDetails parsedCommand = parseCutCommand(command);
//
//        if (parsedCommand != null) {
////            System.out.println("Command: " + command);
//            System.out.println("Fields: " + parsedCommand.getFields());
//            System.out.println("Delimiter: " + parsedCommand.getDelimiter());
//            System.out.println("File Name: " + parsedCommand.getFileName());
//            if (parsedCommand.getPrintLimiter() != null) {
//                System.out.println("Print Limiter Command: " + parsedCommand.getPrintLimiter());
//            }
//        } else {
//            System.out.println("Command could not be parsed");
//        }
//
//        scanner.close();
//    }
//
//    public static CommandDetails parseCutCommand(String command) {
//        String fields = null;
//        String delimiter = "\t";  // Default delimiter is tab
//        String fileName = null;
//        String printLimiter = null;
//
//        // Check if there's a head command
//        String[] parts = command.split("\\| head ");
//        if (parts.length == 2) {
//            command = parts[0].trim();
//            printLimiter = parts[1].trim();
//        }
//
//        // Split the cut command into parts
//        String[] commandParts = command.split("\\s+");
//
//        if (commandParts.length > 1 && "cut".equals(commandParts[0])) {
//            for (int i = 1; i < commandParts.length; i++) {
//                if (commandParts[i].startsWith("-f")) {
//                    fields = commandParts[i].substring(2);
//                } else if (commandParts[i].startsWith("-d")) {
//                    delimiter = commandParts[i].substring(2);
//                } else {
//                    fileName = commandParts[i];
//                }
//            }
//        }
//
//        if (fields != null && fileName != null) {
//            return new CommandDetails(fields, delimiter, fileName, printLimiter);
//        } else {
//            return null;
//        }
//    }




        // Function to remove the first and
        // the last character of a string
        public static String removeFirstandLast(String str)
        {

            // Removing first and last character
            // of a string using substring() method
            str = str.substring(0,str.length() - 1);

            // Return the modified string
            return str;
        }

        // Driver Code
        public static void main(String args[])
        {
            // Given String str
            String str = "tail -n5 fourchords.csv| cut -d, -f\"1 2\" -";

            // Print the modified string
            System.out.print(removeFirstandLast(str));
        }

}
