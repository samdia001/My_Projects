/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package cookbook;

import java.io.IOException;
import java.net.URL;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

//import javax.management.Query;

public class Cookbook extends Application {

    private final int WINDOW_WIDTH = 1170;
    private final int WINDOW_HEIGHT = 617;

    private final String APP_TITLE = "May The Dish Be With You";

    private final String LOGO_IMAGE_PATH = "src/main/resources/menuIcons/lets_dish.png";
    private final String DARTH_IMAGE_PATH = "src/main/resources/menuIcons/Dish_Vader.png";

    private final String APP_NAME = "Preparing Your Kitchen...";
    private final Font APP_NAME_FONT = new Font("Times New Roman", 28);

    private final int square_size = 40;
    private final int move_distance = 200;
    private final Duration move_duration = Duration.seconds(3);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Rectangle square = new Rectangle(square_size, square_size);
        square.setFill(Color.GREENYELLOW);

        Image darthImage = new Image("file:" + DARTH_IMAGE_PATH);

        TranslateTransition squTransition = new TranslateTransition(move_duration, square);
        squTransition.setFromX(-120);
        squTransition.setToX(move_distance);
        squTransition.setAutoReverse(true);
        squTransition.setCycleCount(Animation.INDEFINITE);
        squTransition.play();

        URL backgroundImageUrl = new URL(
                "https://cdn.discordapp.com/attachments/1110126286715756604/1110924197850534039/IMG_2556.png");

        Image backgroundImage = new Image(backgroundImageUrl.toString());
        ImageView backgroundImageView = new ImageView(backgroundImage);

        Image image = new Image("file:" + LOGO_IMAGE_PATH);
        ImageView logoImageView = new ImageView(image);

        // Fade in animation for the logo
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), logoImageView);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.setDelay(Duration.seconds(0.5));

        // rotate animation for the square
        RotateTransition rotate = new RotateTransition(Duration.seconds(2), square);
        rotate.setByAngle(360); // Rotate the square by 360 degrees
        rotate.setCycleCount(4);
        rotate.setAutoReverse(true);

        // Slide up animation for the app name text
        Text appNameText = new Text(APP_NAME);
        appNameText.setFont(APP_NAME_FONT);
        appNameText.setFill(Color.WHITESMOKE);

        // a vbox to hold the animation toghether
        VBox vbox = new VBox(20, logoImageView, appNameText, square);
        vbox.setAlignment(Pos.CENTER);

        vbox.setTranslateX(-300);
        vbox.setTranslateY(300);

        Group roots = new Group();
        roots.getChildren().add(vbox);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(vbox.translateYProperty(), -200)),
                new KeyFrame(Duration.seconds(1.5), new KeyValue(vbox.translateYProperty(), 2)));

        // this is for playing animations in sequence and login scene after
        Animation animation = new SequentialTransition(fadeTransition, timeline, rotate);
        animation.setDelay(Duration.seconds(0.5));
        animation.setOnFinished(event -> {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(pauseEvent -> {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/cookbook/LoginScreenScene.fxml"));

                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);

                    primaryStage.setTitle("Dish IT");
                    primaryStage.setWidth(660);

                    primaryStage.setHeight(540);
                    primaryStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            pause.play();
        });
        animation.play();

        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundImageView, vbox);

        // setting the scene to the stage
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.getIcons().add(darthImage);
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}