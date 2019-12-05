package core.operations;


import java.util.ArrayList;
import java.util.List;

public class StringOperations {

    public static String repeatString(String string, Integer times) {
        StringBuilder repeatedString = new StringBuilder();
        for (Integer time = 0; time < times; ++time) {
            repeatedString.append(string);
        }
        return repeatedString.toString();
    }

    public static String reverseString(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        return stringBuilder.reverse().toString();
    }

    public static String joinStrings(List<String> strings, String splitter) {
        StringBuilder joinedStrings = new StringBuilder();
        for (String string : strings) {
            if (string != null && !string.isEmpty()) {
                if (joinedStrings.length() > 0) {
                    joinedStrings.append(splitter);
                }
                joinedStrings.append(string);
            }
        }
        return joinedStrings.toString();
    }

    public static ArrayList<String> splitString(String string, String splitter) {
        ArrayList<String> splittedStrings = new ArrayList<>();
        int startIndex = 0;
        int endIndex = string.indexOf(splitter);
        while (endIndex != -1) {
            splittedStrings.add(string.substring(startIndex, endIndex));
            startIndex = endIndex + 1;
            endIndex = string.indexOf(splitter, endIndex + 1);
        }
        splittedStrings.add(string.substring(startIndex));
        return splittedStrings;
    }
}
