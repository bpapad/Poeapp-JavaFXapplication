package main;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import main.classes.Query;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView logoImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField enterPasswordField;


    /*JDK 11 has trouble showing images due to JavaFX being an external library.
      The following Override method loads the images accordingly*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File brandingFile = new File("images/scion.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File logoFile = new File("images/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);


    }

    //CHECKS THE DATABASE WITH THE DATA GIVEN AND LOADS THE USER PROFILE STAGE IF THE DATA GIVEN ARE CORRECT
    public void loginButtonAction(ActionEvent event){
        Query q = new Query();
        if(!usernameTextField.getText().isBlank() && !enterPasswordField.getText().isBlank()){
            if(q.verifyLogin(usernameTextField.getText(),enterPasswordField.getText())){
                loginMessageLabel.setText("Successful Login");
                Query.userID = q.getUserID(usernameTextField.getText(),enterPasswordField.getText());
                openUserProfile();
            }else{
                loginMessageLabel.setText("Invalid Login");
            }
        }else{
            loginMessageLabel.setText("Please enter username and password");
        }
    }


    //CLOSES CURRENT STAGE, EXITING THE APPLICATION AS WELL
    public void cancelButtonAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


    //LOADS THE REGISTRATION STAGE AND CLOSES THE CURRENT LOGIN FORM
    public void  openRegistrationButtonAction(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("views/register.fxml"));
            Stage registerStage = new Stage();
            registerStage.setResizable(false);
            registerStage.setTitle("PoE App");
            File logoFile = new File("images/border_icon.jpg");
            Image icon = new Image(logoFile.toURI().toString());
            registerStage.getIcons().add(icon);
            registerStage.setScene(new Scene(root, 520, 560));
            registerStage.show();

            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


    //THIS METHOD LOADS THE USER PROFILE STAGE WHEN CALLED
    public void openUserProfile(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("views/user_profile.fxml"));
            Stage registerStage = new Stage();
            registerStage.setResizable(false);
            registerStage.setTitle("PoE App");
            File logoFile = new File("images/border_icon.jpg");
            Image icon = new Image(logoFile.toURI().toString());
            registerStage.getIcons().add(icon);
            registerStage.setScene(new Scene(root, 800, 500));
            registerStage.show();

            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }



    
}
