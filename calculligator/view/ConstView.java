package calculligator.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ConstView {
	private static final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();

	public static final String APP_TITLE = "Calculligator";
	public static final Double APP_WIDTH = SCREEN.getWidth() * 0.15;
	public static final Double APP_HEIGHT = APP_WIDTH * 1.618;

	public static final String[][] KEYBOARD_LABELS = { { "BIN", "OCT", "DEC", "CE" }, { "7", "8", "9", "+" },
			{ "4", "5", "6", "-" }, { "1", "2", "3", "x" }, { "0", ".", "=", "/" } };
	public static final Integer KEYBOARD_ROWS = 5;
	public static final Integer KEYBOARD_COLS = 4;
	public static final Color KEY_COLOR = Color.rgb(61, 220, 151);
	public static final CornerRadii KEY_CORNERS = new CornerRadii(0);
	public static final Background KEY_BACKGROUND = new Background(
			new BackgroundFill(KEY_COLOR, KEY_CORNERS, Insets.EMPTY));
	public static final String KEY_IMAGE = "/res/pattern.jpg";
	public static final String BORDER_COLOR = "rgb(28, 28, 28)";
	public static final Font KEY_FONT = Font.font("DejaVu Sans Mono", 22);
	public static final Font RESULT_FONT = Font.font("DejaVu Sans Mono", 24);

	public static final Double KEY_SIZE = APP_WIDTH / KEYBOARD_COLS;

	public static final Double DISPLAY_HGAP = 10d;
	public static final Insets DISPLAY_INSETS = new Insets(10);
	public static final Double DISPLAY_WIDTH = APP_WIDTH;
	public static final Double DISPLAY_HEIGHT = APP_HEIGHT - KEY_SIZE * KEYBOARD_ROWS;
	public static final String DISPLAY_IMAGE = "/res/alligator.jpg";

	public static final Double RESULT_WIDTH = DISPLAY_WIDTH-20;
	public static final Double RESULT_HEIGHT = DISPLAY_HEIGHT;
}
