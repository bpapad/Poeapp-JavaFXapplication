package main;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import main.classes.Query;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterController implements Initializable{

    @FXML
    private ImageView registerImageView;
    @FXML
    private Button cancelRegistrationButton;
    @FXML
    private Button registrationButton;
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private TextField emailTextField;
    @FXML
    private Label confirmEmailLabel;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField usernameTextField;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File brandingFile = new File("images/windows.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        registerImageView.setImage(brandingImage);
    }


    //CLOSES THE REGISTRATION SCENE AND RELAUNCHES THE LOGIN FXML FILE
    public void cancelRegistrationButton(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("views/login.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setResizable(false);
            primaryStage.setTitle("PoE App");
            File logoFile = new File("images/border_icon.jpg");
            Image icon = new Image(logoFile.toURI().toString());
            primaryStage.getIcons().add(icon);
            primaryStage.setScene(new Scene(root, 520, 400));
            primaryStage.show();

            Stage stage = (Stage) cancelRegistrationButton.getScene().getWindow();
            stage.close();


        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


    //IF ALL FIELDS ARE FILLED ACCORDINGLY IT BEGINS THE REGISTRATION
    public void registrationButtonAction(ActionEvent event){
        if (fieldsCompletedCorrectly() && emailIsCorrect() && passwordsMatch())
            registerUser();

    }


    //CHECKS IF THE FIELD CONTENTS ARE FILLED AS NEEDED
    public boolean fieldsCompletedCorrectly(){
        registrationMessageLabel.setText("");
        if (firstnameTextField.getText().isEmpty() || lastnameTextField.getText().isEmpty() || usernameTextField.getText().isEmpty() || setPasswordField.getText().isEmpty() || confirmPasswordField.getText().isEmpty()){
            registrationMessageLabel.setText("All Fields must be filled!");
            return false;
        }else return true;
    }

    //CHECKS IF THE EMAIL PROVIDED IS CORRECT
    public boolean emailIsCorrect(){
        confirmEmailLabel.setText("");
        if(!emailTextField.getText().contains("@") || !emailTextField.getText().contains(".")){
            confirmEmailLabel.setText("Invalid Email!");
            return false;
        }else return true;
    }

    // CHECKS IF BOTH PASSWORD FIELDS ARE IDENTICAL
    public boolean passwordsMatch(){
        confirmPasswordLabel.setText("");
        if(!setPasswordField.getText().equals(confirmPasswordField.getText())){
            confirmPasswordLabel.setText("Password doesn't match!");
            return false;
        }else return true;
    }


    //DISABLES SCENE FIELDS, PAUSES FOR 1 SECOND, CLOSES THE STAGE AND LOADS login.fxml
    public void sceneDisablePauseAndLoad(){
        registrationButton.setDisable(true);
        cancelRegistrationButton.setDisable(true);
        confirmPasswordField.setDisable(true);
        setPasswordField.setDisable(true);
        emailTextField.setDisable(true);
        usernameTextField.setDisable(true);
        lastnameTextField.setDisable(true);
        firstnameTextField.setDisable(true);

        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event->{
            try {
                Parent root = FXMLLoader.load(getClass().getResource("views/login.fxml"));
                Stage primaryStage = new Stage();
                primaryStage.setResizable(false);
                primaryStage.setTitle("PoE App");
                File logoFile = new File("images/border_icon.jpg");
                Image icon = new Image(logoFile.toURI().toString());
                primaryStage.getIcons().add(icon);
                primaryStage.setScene(new Scene(root, 520, 400));
                primaryStage.show();

                Stage stage = (Stage) registrationMessageLabel.getScene().getWindow();
                stage.close();


            }catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }
        });
        pause.play();
    }


    //THIS METHOD GATHERS AND STORES THE DATA PROVIDED IN A LIST AND PROCEEDS TO ADD THE NEW USER IN THE DATABASE
    public void registerUser(){
        List<String> userData = new ArrayList<>();
        userData.add(firstnameTextField.getText());
        userData.add(lastnameTextField.getText());
        userData.add(emailTextField.getText());
        userData.add(usernameTextField.getText());
        userData.add(setPasswordField.getText());

        Query q = new Query();
        q.userRegistration(userData);

        registrationMessageLabel.setText("User Registered Successfully!");
        sceneDisablePauseAndLoad();
    }




}
