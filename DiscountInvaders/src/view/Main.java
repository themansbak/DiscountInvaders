package view;

import java.util.ArrayList;

import controller.AsteroidAnimation;
import controller.ShipKeyHandler;
import controller.UFOAnimation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Ship;
import model.SmallUFO;
import model.Asteroid;
import model.LargeUFO;

public class Main extends Application{
	public int numUFO;
	public int numAsteroids;
	public Label shipHealth; 
	private Stage myStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		myStage = primaryStage;
		Scene startScene = new Scene(startScreen(), 400, 400);
		primaryStage.setScene(startScene);
		primaryStage.show();
	}
	/**
	 * start screen with instructions
	 * @return
	 */
	public VBox startScreen() {
		VBox start = new VBox();
		resource.Constants.intro.setFont(Font.font("Arial", FontWeight.BOLD, 43));
		resource.Constants.intro.setTextFill(Color.WHITE);
		VBox instructionsBox = new VBox();
		resource.Constants.i0.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		resource.Constants.i6.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		resource.Constants.i0.setTextFill(Color.WHITE);
		resource.Constants.i1.setTextFill(Color.WHITE);
		resource.Constants.i2.setTextFill(Color.WHITE);
		resource.Constants.i3.setTextFill(Color.WHITE);
		resource.Constants.i4.setTextFill(Color.WHITE);
		resource.Constants.i5.setTextFill(Color.WHITE);
		resource.Constants.i6.setTextFill(Color.WHITE);
		instructionsBox.getChildren().addAll(resource.Constants.i0, resource.Constants.i1, resource.Constants.i2, 
				resource.Constants.i3, resource.Constants.i6,
				resource.Constants.i4, resource.Constants.i5);
		instructionsBox.setAlignment(Pos.CENTER);
		ToggleGroup mode = new ToggleGroup();
		RadioButton easyMode = new RadioButton("Easy Mode");
		RadioButton hardMode = new RadioButton("Hard Mode");
		easyMode.setTextFill(Color.WHITE);
		hardMode.setTextFill(Color.WHITE);
		easyMode.setToggleGroup(mode);
		hardMode.setToggleGroup(mode);
		Button playGame = new Button("Play game!");
		playGame.setOnAction(e -> {
			if (easyMode.isSelected()) {
				numUFO = 2;
				numAsteroids = 3;
				myStage.setScene(new Scene(gameScreen(), 1000, 750));	
			} else if (hardMode.isSelected()) {
				numUFO = 12;
				numAsteroids = 35;
				myStage.setScene(new Scene(gameScreen(), 1000, 750));	
			}		
		});
		start.setStyle("-fx-background-color: #000");
		start.setAlignment(Pos.CENTER);
		start.setSpacing(10);
		
		start.getChildren().addAll(resource.Constants.intro, instructionsBox, easyMode, hardMode, playGame);
		
		return start;
	}
	/**
	 * Game view with all characters
	 * @return
	 */
	public Pane gameScreen() {
		Pane pane = new Pane();
		shipHealth = new Label("Health: 100%");
		shipHealth.setTextFill(Color.WHITE);
		shipHealth.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		Ship ship = new Ship(500, 500);
		ArrayList<Asteroid> a = new ArrayList<Asteroid>(numAsteroids);
		for (int i = 0; i < numAsteroids; i++) {
			Asteroid _a = new Asteroid(0, 0);
			AsteroidAnimation aa = new AsteroidAnimation(_a, ship, pane, shipHealth);
			_a.setAnimation(aa);
			a.add(_a);
			aa.start();
		}
		ArrayList<LargeUFO> lu = new ArrayList<LargeUFO>(numUFO);
		ArrayList<SmallUFO> su = new ArrayList<SmallUFO>(numUFO);
		for (int i = 0; i < numUFO; i++) {
			LargeUFO l = new LargeUFO(i*75, 0);
			lu.add(l);
			SmallUFO s = new SmallUFO(i*75, 100);
			su.add(s);
			UFOAnimation ua = new UFOAnimation(pane, l, ship, shipHealth);
			UFOAnimation sa = new UFOAnimation(pane, s, ship, shipHealth);
			l.setAnimation(ua);
			s.setAnimation(sa);
			ua.start();
			sa.start();
		}
		pane.setStyle("-fx-background-color: #000");
		pane.getChildren().addAll(a);
		pane.getChildren().addAll(su);
		pane.getChildren().addAll(lu);
		pane.getChildren().addAll(ship, shipHealth);
		
		ship.addEventHandler(KeyEvent.KEY_PRESSED, new ShipKeyHandler(pane, ship, lu, su, a));
		ship.setFocusTraversable(true);
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (lu.isEmpty() && su.isEmpty()) {
					this.stop();
					endGame(lu, su);
					Scene victoryScene = new Scene(victoryScene(), 300, 300);
					myStage.setScene(victoryScene);
				}
				if (ship.getHealth() <= 0) {
					this.stop();
					endGame(lu, su);
					Scene lostScene = new Scene(lostScene(), 300, 300);
					myStage.setScene(lostScene);
				}
			}
		}.start();
		
		return pane;
	}
	/**
	 * stops ufo animation timers
	 * @param lu
	 * @param su
	 */
	public void endGame(ArrayList<LargeUFO> lu, ArrayList<SmallUFO> su) {
		for (LargeUFO u : lu) u.ua.stop();
		for (SmallUFO u : su) u.ua.stop();
		
	}
	/**
	 * losing scene
	 * @return
	 */
	public VBox lostScene() {
		VBox lost = new VBox();
		Label loserText = new Label("You lost!");
		loserText.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		loserText.setTextFill(Color.WHITE);
		Button playAgain = new Button("Play Again?");
		playAgain.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		playAgain.setOnAction(e -> {
			myStage.setScene(new Scene(startScreen(), 400, 400));	
		});
		Button exit = new Button("Exit");
		exit.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		exit.setOnAction(e -> {
			System.exit(0);
		});
		lost.setAlignment(Pos.CENTER);
		lost.setStyle("-fx-background-color: #000");
		lost.setSpacing(20);
		lost.getChildren().addAll(loserText, playAgain, exit);
		return lost;
	}
	/**
	 * victory scene
	 * @return
	 */
	public VBox victoryScene() {
		VBox victory = new VBox();
		Label victoryText = new Label("YOU WON!!");
		victoryText.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		victoryText.setTextFill(Color.WHITE);
		Button playAgain = new Button("Play Again?");
		playAgain.setOnAction(e -> {
			myStage.setScene(new Scene(startScreen(), 400, 400));		
		});
		Button exit = new Button("Exit");
		exit.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		exit.setOnAction(e -> {
			System.exit(0);
		});
		playAgain.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
		victory.setStyle("-fx-background-color: #000");
		victory.setAlignment(Pos.CENTER);		
		victory.setSpacing(20);
		victory.getChildren().addAll(victoryText, playAgain, exit);
		return victory;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
