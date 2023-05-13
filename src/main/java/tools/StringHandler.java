package tools;

public class StringHandler {
    public static String getNumberString(String string) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (CharacterHandler.isDigit(string.charAt(i)) || CharacterHandler.isDot(string.charAt(i))
                    || CharacterHandler.isNegativeSign(string.charAt(i))) {
                result.append(string.charAt(i));
            }
        }
        return result.toString();
    }

    public static boolean containsDigitDotNegativeSign(String string) {
        for (int i = 0; i < string.length(); i++) {
            char aChar = string.charAt(i);
            if (!CharacterHandler.isDigit(aChar) && !CharacterHandler.isDot(aChar)
                    && !CharacterHandler.isNegativeSign(aChar)) {
                return false;
            }
        }
        return true;
    }
}
