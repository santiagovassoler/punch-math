
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Template extends Application {

	Scene scene;
	TabPane root;
	Tab tab1, tab2;
	FlowPane flowPane;
	Pane topPane, centerPane;
	Factory f = new Factory();
	Label questionLabel, resultLabel, playerName, scoreLabel;
	Button checkButton;
	TextField textField,userTextField;
	BorderPane border;
	String answer;
	HBox bottomBox;
	Bounds objA;
	Bounds objB;
	int level=1;
	int score=0;
	Scene managerScene, gameoverScene, menuScene;
	GridPane grid;
	Label scenetitle;
	String exp;
	AudioClip menu_theme, punch_sound, punch_face, game_theme, gameoverSound, right_answer;


	AnimationTimer timer = new AnimationTimer() {
		@Override
		public void handle(long now) {

			for(Node a: centerPane.getChildren())
			{	
				Product prod = (Product)a;
				objB = prod.localToScene(prod.getBoundsInLocal());
				PlayerProduct pr = (PlayerProduct)centerPane.getChildren().get(0);

				if(objB !=null && pr.intersects(objB)){
					if(!(prod instanceof PlayerProduct))
						if(prod.isVisible()==true && objB.getMinX() < 360.00){
							gameover();
						}
				}				
			}
		}
	};

	Timeline addEnemy = new Timeline(new KeyFrame(Duration.seconds(5),new EventHandler<ActionEvent>(){

		@Override
		public void handle(ActionEvent event) {
			centerPane.getChildren().add((f.createProduct("Enemy",800,0)));
			;
		}}
			));

	EventHandler<KeyEvent> kHandler  = new EventHandler<KeyEvent>(){

		@Override
		public void handle(KeyEvent event) {
			PlayerProduct p = (PlayerProduct)centerPane.getChildren().get(0);
			for(Node a: centerPane.getChildren())
			{
				Product z = (Product)a;
				if(event.getCode()==KeyCode.P){
					punch_sound = new AudioClip(Template.class.getResource("resources/punch.mp3").toExternalForm());
					punch_sound.play();
					p.punch();
				}
				if(objB !=null && p.intersects(objB)==true){					   
					if(!(z instanceof PlayerProduct)){
						punch_face = new AudioClip(Template.class.getResource("resources/nice.mp3").toExternalForm());
						punch_face.play();
						z.setVisible(false);
					}
				}
			}
		}
	};

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {
		String css = Template.class.getResource("/resources/app.css").toExternalForm();
		root = new TabPane();
		scene = new Scene(root, 800, 600);
		scene.getStylesheets().clear();
		scene.getStylesheets().add(css);
		stage.setScene(scene);

		tab1 = new Tab();
		tab1.setText("First Tab");
		tab1.setClosable(false);
		root.getTabs().add(tab1);

		tab2 = new Tab();
		tab2.setText("Second Tab");
		tab2.setClosable(false);
		root.getTabs().add(tab2);
		stage.show();

		GridPane howToPlay = new GridPane();
		howToPlay.setAlignment(Pos.CENTER);
		howToPlay.setHgap(10);
		howToPlay.setVgap(10);
		howToPlay.setPadding(new Insets(25, 25, 25, 25));
		howToPlay.setStyle("-fx-background-image: url('resources/bg-01.jpg')");
		tab2.setContent(howToPlay);

		//menu
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		grid.setStyle("-fx-background-image: url('resources/bg-01.jpg')");
		menu_theme = new AudioClip(Template.class.getResource("resources/menu_theme.mp3").toExternalForm());
		menu_theme.play();
		tab1.setContent(grid);
		scenetitle = new Label("Welcome");
		scenetitle.getStyleClass().add("title");
		grid.add(scenetitle, 0, 0, 2, 1);
		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);
		userName.getStyleClass().add("txt");
		userTextField = new TextField();
		grid.add(userTextField, 1, 1);
		userTextField.getStyleClass().add("txtfield");
		Label pw = new Label("Level:");
		grid.add(pw, 0, 2);
		pw.getStyleClass().add("txt");

		final ComboBox<String> comboBox = new ComboBox<String>();
		comboBox.getItems().addAll(
				"easy",
				"hard",
				"very hard" 
				);   
		comboBox.getStyleClass().add("txtfield");
		comboBox.setValue("Level");
		grid.add(comboBox, 1, 2);
		Button btn = new Button("play");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);
		
		questionLabel = new Label();
		questionLabel.setPrefSize(800, 100);
		questionLabel.setMaxWidth(Double.MAX_VALUE);
		questionLabel.setAlignment(Pos.TOP_CENTER);//Pos.CENTER);
		questionLabel.getStyleClass().add("questionLabel");

		playerName = new Label();
		playerName.setPrefSize(700, 100);
		playerName.setMaxWidth(Double.MAX_VALUE);
		playerName.setAlignment(Pos.TOP_LEFT);
		playerName.getStyleClass().add("playerLabel");

		scoreLabel = new Label();
		scoreLabel.setPrefSize(700, 100);
		scoreLabel.setMaxWidth(Double.MAX_VALUE);
		scoreLabel.setAlignment(Pos.TOP_RIGHT);
		scoreLabel.getStyleClass().add("playerLabel");
		
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if(comboBox.getValue().equals("easy"))
					level = 1;
				if(comboBox.getValue().equals("hard"))
					level = 2;
				if(comboBox.getValue().equals("very hard"))
					level = 3;
				generateExpressionWithLevel(level);
				String name = userTextField.getText();
				playerName.setText("Player: "+ name);
				stage.setScene(scene);
				menu_theme.stop();
				startGame();
			}
		});
	}

	public void startGame()
	{
		game_theme = new AudioClip(Template.class.getResource("resources/game_theme.mp3").toExternalForm());
		game_theme.play();
		//TOP PANE
		topPane = new Pane();
		topPane.setPrefSize(800,100);
		//CENTER PANE
		centerPane = new Pane();
		centerPane.setPrefSize(800,500);
		scoreLabel.setText("Score: "+score);
		topPane.getChildren().addAll(playerName, questionLabel, scoreLabel);
		bottomBox = new HBox();
		//text field
		textField = new TextField();
		textField.setPrefSize(120, 50);
		//button
		checkButton = new Button();
		checkButton.setText("CHECK!");
		checkButton.setPrefSize(120, 50);
		checkButton.getStyleClass().add("button");
		bottomBox.setAlignment(Pos.CENTER);
		bottomBox.getChildren().addAll(textField, checkButton);
		//BORDER PANE
		border = new BorderPane();
		border.setStyle("-fx-background-image: url('resources/bg-01.jpg');");
		border.setTop(topPane);
		border.setCenter(centerPane);
		border.setBottom(bottomBox);

		centerPane.getChildren().add(f.createProduct("Player",20,0));
		tab1.setContent(border);
		timer.start();
		addEnemy.setCycleCount(Animation.INDEFINITE);
		addEnemy.play();
		scene.setOnKeyPressed(kHandler);
		
		checkButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				sendFunction(level);
			}
		});
		textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode() == KeyCode.ENTER)  {
					sendFunction(level);
				}
			}
		});
	}

	public void gameover()
	{
		punch_sound.stop();
		punch_face.stop();
		addEnemy.stop();
		score=0;
		game_theme.stop();
		gameoverSound = new AudioClip(Template.class.getResource("resources/gameover.mp3").toExternalForm());
		gameoverSound.play();
		menu_theme.play();
		timer.stop();
		scenetitle.setText("GAME OVER" + "\n" + "TRY AGAIN?");
		tab1.setContent(grid);
	}

	public void sendFunction(int level) {
		String text = this.textField.getText();
		if(text.equals(answer)){
			generateExpressionWithLevel(level);
			right_answer = new AudioClip(Template.class.getResource("resources/right_answer.mp3").toExternalForm());
			right_answer.play();
			score+=10;
			scoreLabel.setText("Score: " + score);
		}
		this.textField.setText("");
	}

	public void generateExpressionWithLevel(int level)
	{	
		exp = expression(level);
		questionLabel.setText(exp);
		Double d = evaluate(exp);

		if(d.isNaN() || d.isInfinite()){
			generateExpressionWithLevel(level);
		}
		else{
			answer = d.longValue() == d ? "" + d.longValue() : "" + String.format( "%.2f", d);
		}
	}

	public String expression(int level)
	{
		RandomExpressionGenerator expression = new RandomExpressionGenerator();
		return expression.expression(level); 
	}

	public double evaluate(String e)
	{
		EvaluateExpression ev = new EvaluateExpression();
		return ev.evaluate(e);
	}
}
