package core.operations;

public class CharOperations {

    public static boolean isDigit(Character character) {
        return character >= '0' && character <= '9';
    }

    public static boolean isEnglishSymbol(Character character) {
        return character >= 'a' && character <= 'z'
                || character >= 'A' && character <= 'Z';
    }
}
