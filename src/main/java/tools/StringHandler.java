package tools;

public class StringHandler {
    public static String getNumberString(String string) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (new CharacterHandler(string.charAt(i)).isDigit() || new CharacterHandler(string.charAt(i)).isDot()) {
                result.append(string.charAt(i));
            }
        }
        return result.toString();
    }
}
