package utils;

import model.CommandDetails;

import java.util.HashMap;
import java.util.Map;

public class ParseCommands {
    public static Map<String, String> parseCutCommand(String userCommand) {
        CommandDetails commandDetails = parseAllCutCommandField(userCommand);
        Map<String, String> commandDetailsMap = new HashMap<>();
        if (commandDetails != null) {
            commandDetailsMap.put("columnsToPrint",
                    commandDetails.getFields());
            commandDetailsMap.put("delimiter", commandDetails.getDelimiter());
            commandDetailsMap.put("filename", commandDetails.getFileName());
            commandDetailsMap.put("printlimiter", commandDetails.getPrintLimiter());
        }
        return commandDetailsMap;
    }

    private static CommandDetails parseAllCutCommandField(String userCommand) {
        String fieldToPrint = null;
        String delimiter = "\t";
        String fileName = null;
        String printLimiter = null;

        // Check if there is print limiter command included
        String initialWord = userCommand.substring(0, userCommand.indexOf(' '));

        if (initialWord.equals("cut")) {
            if (userCommand.contains("heads")) {
                String[] parts = userCommand.split("\\| head ");
                if (parts.length == 2) {
                    userCommand = parts[0].trim();
                    printLimiter = parts[1].trim();
                }
            } else if (userCommand.contains("uniq")) {
                printLimiter = "0";
                String[] parts = userCommand.split(" ");
                if (parts.length > 2) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < parts.length; i++) {
                        String abc = parts[i].trim();
                        if (abc.equals("|") || abc.equals("uniq") || abc.equals("wc") || abc.equals("-l")) {
                            continue;
                        } else {
                            if (abc.contains("|")) {
                                abc = abc.substring(0, abc.length() - 1);
                            }
                            stringBuilder.append(abc).append(" ");
                        }
                    }
                    userCommand = stringBuilder.toString();
                }
            }

        } else if (initialWord.equals("tail")) {
            if (userCommand.endsWith("-")) {
                int userCommandLength = userCommand.length();
                userCommand = userCommand.substring(0, userCommandLength - 2);
            }
            String[] parts = userCommand.split(" ");
            if (parts.length > 2) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < parts.length; i++) {
                    String abc = parts[i].trim();
                    if (abc.equals("|") || abc.equals("cut")) {
                        continue;
                    } else if (i == 1) {
                        printLimiter = parts[i].trim();
                    } else {
                        if (abc.contains("|")) {
                            abc = abc.substring(0, abc.length() - 1);
                        }
                        stringBuilder.append(abc).append(" ");
                    }
                }
                userCommand = stringBuilder.toString();
            }
        }


        // Split the command into parts while preserving quoted strings and handling different delimiters
        String[] commandParts = userCommand.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        boolean nextIsField = false;
        boolean nextIsDelimiter = false;


        if (commandParts.length > 1 && initialWord.equals(commandParts[0])) {
            for (int i = 1; i < commandParts.length; i++) {
                String part = commandParts[i];
                if (part.startsWith("-f")) {
                    if (part.length() > 2) {
                        fieldToPrint = part.substring(2).replace("\"", "");
                    } else {
                        nextIsField = true;
                    }
                } else if (part.startsWith("-d")) {
                    if (part.length() > 2) {
                        delimiter = part.substring(2);
                    } else {
                        nextIsDelimiter = true;
                    }
                } else {
                    if (nextIsField) {
                        fieldToPrint = part.replace("\"", "");
                        nextIsField = false;
                    } else if (nextIsDelimiter) {
                        delimiter = part;
                        nextIsDelimiter = false;
                    } else {
                        fileName = part;
                    }
                }
            }
        }
        if (fieldToPrint != null && fileName != null) {
            return new CommandDetails(fieldToPrint, delimiter, fileName, printLimiter);
        } else {
            return null;
        }
    }
}
