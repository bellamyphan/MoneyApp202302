package tools;

public class CharacterHandler {

    private final char aChar;

    public CharacterHandler(char aChar) {
        this.aChar = aChar;
    }

    public boolean isDigit() {
        return 48 <= aChar && aChar <= 57;
    }

    public boolean isDot() {
        return aChar == 46;
    }
}
