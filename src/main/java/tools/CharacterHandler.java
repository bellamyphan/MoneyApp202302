package tools;

public class CharacterHandler {
    public static boolean isDigit(char aChar) {
        return 48 <= aChar && aChar <= 57;
    }

    public static boolean isDot(char aChar) {
        return aChar == 46;
    }

    public static boolean isNegativeSign(char aChar) {
        return aChar == 45;
    }
}
