package tools;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class PrimaryScreen {
    private static final Screen primaryScreen = Screen.getPrimary();
    private static final Rectangle2D bounds = primaryScreen.getVisualBounds();
    public static final double primaryScreenWidth = bounds.getWidth();
    public static final double primaryScreenHeight = bounds.getHeight();
}
