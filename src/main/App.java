package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("views/login.fxml"));
        primaryStage.setResizable(false);
        primaryStage.setTitle("PoE App");
        File logoFile = new File("images/border_icon.jpg");
        Image icon = new Image(logoFile.toURI().toString());
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(root, 520, 400));
        primaryStage.show();
    }

}
