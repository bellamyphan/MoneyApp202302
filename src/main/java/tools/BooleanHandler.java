package tools;

public class BooleanHandler {
    public static boolean getBooleanValueFromString(String string) {
        return string.compareToIgnoreCase("y") == 0 || string.compareToIgnoreCase("yes") == 0;
    }
}
