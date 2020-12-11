package main.classes;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class BuildGuide {
    private SimpleStringProperty name;
    private SimpleStringProperty title;
    private Hyperlink hyperlink;
    private Button editButton;


    public BuildGuide(String name, String title, String hyperlink ,String editType){
        this.name = new SimpleStringProperty(name);
        this.title =  new SimpleStringProperty(title);
        this.hyperlink = new Hyperlink(hyperlink);
        this.hyperlink.setOnAction(e->{
            openBrowser(hyperlink);
        });
        this.editButton = new Button(editType);
        this.editButton.setOnAction(e->{setEditButton(editType,hyperlink);});
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public Hyperlink getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(Hyperlink hyperlink) {
        this.hyperlink = hyperlink;
    }

    public Button getEditButton() { return editButton; }

    public void setEditButton(Button editButton) { this.editButton = editButton; }

    public void openBrowser(String URL){
        Application a = new Application() {
            @Override
            public void start(Stage stage){}
        };
        a.getHostServices().showDocument(URL);
    }

    public void setEditButton(String buttonType, String link){
        if(buttonType.equals("Delete")){
            Query q = new Query();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Are you sure?");
            alert.setHeaderText("Delete "+getName()+" ?");
            alert.setContentText("Confirm to delete Build : ");
            Optional<ButtonType> choice = alert.showAndWait();
            if ((choice.isPresent()) && (choice.get() == ButtonType.OK)) {

                q.deleteBuild(getName());

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Successfully deleted "+getName());
                alert.showAndWait();

            }

        }else if(buttonType.equals("Save")){
            Query q = new Query();

            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Saved");
            dialog.setHeaderText("Enter Build Name: ");
            Optional<String> result = dialog.showAndWait();
            String myName = null;
            if (result.isPresent()) {
                myName = result.get();
            }

            q.saveBuild(myName, getTitle(), link, Query.userID);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Successfully saved "+myName);
            alert.showAndWait();
        }

    }


}
