package utils;

import java.util.Scanner;

public class UserInput {
    private static final String ASK_USER_QUESTION = "Enter your cut command";

    public static String getUserCommandInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(ASK_USER_QUESTION);
        String userCommand = scanner.nextLine();
        scanner.close();
        return userCommand;
    }
}
