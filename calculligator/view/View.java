package calculligator.view;

import java.util.Arrays;
import java.util.stream.Collectors;

import calculligator.control.Controller;
import calculligator.model.Model;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class View extends Application {

	private Controller controller;

	private GridPane keyBoard;
	private BorderPane root;
	private Button[][] keys;
	private FlowPane display;
	private Label resultArea;

	public static void main(String[] args) { 
		launch(args);
	}
	
	@Override
	public void init() throws Exception {
		
		controller = new Controller();
		controller.runController();
		controller.setView(this);
		controller.setModel(new Model(controller));
		
		root = new BorderPane();

		keyBoard = new GridPane();
		keyBoard.setVgap(0);
		keyBoard.setHgap(0);

		keys = initKeys();

		display = new FlowPane(Orientation.HORIZONTAL);
		display.setHgap(ConstView.DISPLAY_HGAP);
		display.setPadding(ConstView.DISPLAY_INSETS);
		display.setPrefSize(ConstView.DISPLAY_WIDTH, ConstView.DISPLAY_HEIGHT);
		final BackgroundImage backgroundImage = new BackgroundImage(
				new Image(ConstView.DISPLAY_IMAGE), BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		final Background background = new Background(backgroundImage);
		display.setBackground(background);

		resultArea = new Label();
		resultArea.setPrefSize(ConstView.RESULT_WIDTH, ConstView.RESULT_HEIGHT);
		resultArea.setStyle("-fx-border-color: " + ConstView.BORDER_COLOR + ";");
		resultArea.setText(" ");
		resultArea.setFont(ConstView.RESULT_FONT);
		resultArea.setAlignment(Pos.CENTER);
	}

	@Override
	public void start(Stage stage) throws Exception {

		keyBoard.getChildren().addAll(Arrays.stream(keys).flatMap(Arrays::stream).collect(Collectors.toList()));
		display.getChildren().add(resultArea);

		root.setCenter(keyBoard);
		root.setTop(display);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle(ConstView.APP_TITLE);
		stage.centerOnScreen();
		stage.alwaysOnTopProperty();
		stage.setWidth(ConstView.APP_WIDTH);
		stage.setHeight(ConstView.APP_HEIGHT);
		stage.show();
	}

	@Override
	public void stop() throws Exception {
	}

	private Button[][] initKeys() {

		final BackgroundImage backgroundImage = new BackgroundImage(new Image(ConstView.KEY_IMAGE),
				BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		final Background background = new Background(backgroundImage);

		Button[][] keys = new Button[ConstView.KEYBOARD_ROWS][ConstView.KEYBOARD_COLS];
		for (int r = 0; r != ConstView.KEYBOARD_ROWS; r++) {
			for (int c = 0; c != ConstView.KEYBOARD_COLS; c++) {
				keys[r][c] = new Button(ConstView.KEYBOARD_LABELS[r][c]);
				keys[r][c].setAlignment(Pos.CENTER);
				keys[r][c].setBackground(background);
				keys[r][c].setStyle("-fx-border-color: " + ConstView.BORDER_COLOR + ";");
				if (keys[r][c].getText() == "CE")
					keys[r][c].setTextFill(Color.rgb(255, 73, 92));
				keys[r][c].setFont(ConstView.KEY_FONT);
				keys[r][c].setPrefWidth(ConstView.KEY_SIZE);
				keys[r][c].setPrefHeight(ConstView.KEY_SIZE);
				keys[r][c].setOnMouseEntered(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						((Button) event.getSource()).setBackground(ConstView.KEY_BACKGROUND);
					};
				});
				keys[r][c].setOnMouseExited(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						((Button) event.getSource()).setBackground(background);
					};
				});
				keys[r][c].setOnMouseClicked(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						controller.consume(((Button) event.getSource()).getText());
					};
				});
				
				GridPane.setConstraints(keys[r][c], c, r);
			}
		}
		return keys;
	}
	
	public void printToResult(String result){
		resultArea.setText(result);
	}
}
