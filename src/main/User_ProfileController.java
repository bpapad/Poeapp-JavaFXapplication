package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.classes.*;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class User_ProfileController implements Initializable{

    @FXML
    private Button signOutButton;
    @FXML
    private ImageView profileImageView;
    @FXML
    private ImageView exaltedImageView;
    @FXML
    private ImageView chaosImageView;
    @FXML
    private Label userFullNameLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private WebView leagueVideoWebView;
    @FXML
    private ImageView classImageView;
    @FXML
    private ImageView ascImageView;
    @FXML
    private ComboBox<String> classBox;
    @FXML
    private ComboBox<String> ascBox;
    @FXML
    private TableView<BuildGuide> userBuildsTable;
    @FXML
    private TableColumn<BuildGuide,String> userBuildColName;
    @FXML
    private TableColumn<BuildGuide,String> userBuildColTitle;
    @FXML
    private TableColumn<BuildGuide, Hyperlink> userBuildColLink;
    @FXML
    private TableColumn<BuildGuide, Button> userBuildColDelete;
    @FXML
    private TableView<BuildGuide> forumBuildsTable;
    @FXML
    private TableColumn<BuildGuide,String> forumBuildColName;
    @FXML
    private TableColumn<BuildGuide,String> forumBuildColTitle;
    @FXML
    private TableColumn<BuildGuide,Hyperlink> forumBuildColLink;
    @FXML
    private TableColumn<BuildGuide,Button> forumBuildColSave;



    final String[] classNames = {"Duelist","Marauder","Ranger","Scion","Shadow","Templar","Witch"};
    final String[] DuelistAscs = {"Slayer","Gladiator","Champion"};
    final String[] MarauderAscs = {"Juggernaught","Berserker","Chieftain"};
    final String[] RangerAscs = {"Deadeye","Raider","Pathfinder"};
    final String ScionAsc = "Ascendant";
    final String[] ShadowAscs = {"Assassin","Saboteur","Trickster"};
    final String[] TemplarAscs = {"Inquisitor","Hierophant","Guardian"};
    final String[] WitchAscs = {"Occultist","Necromancer","Elementalist"};



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        profileImageView.setImage(getImageDisplay("images/scion2.png"));
        exaltedImageView.setImage(getImageDisplay("images/exaltedorb.png"));
        chaosImageView.setImage(getImageDisplay("images/chaosorb.png"));
        classImageView.setImage(getImageDisplay("images/logo.png"));
        ascImageView.setImage(getImageDisplay("images/logo.png"));

        setUserData();
        setVideoDisplayTab();
        setUserBuildsTable();
        setForumGuidesTab();






    }


    //RELOADS THE login.fxml WHEN THE BUTTON IS PRESSED
    public void signOutButtonAction(ActionEvent event){
        try {
            Query.userID=0;
            Parent root = FXMLLoader.load(getClass().getResource("views/login.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setResizable(false);
            primaryStage.setTitle("PoE App");
            File logoFile = new File("images/border_icon.jpg");
            Image icon = new Image(logoFile.toURI().toString());
            primaryStage.getIcons().add(icon);
            primaryStage.setScene(new Scene(root, 520, 400));
            primaryStage.show();

            Stage stage = (Stage) signOutButton.getScene().getWindow();
            stage.close();


        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


    //READIES THE IMAGES TO BE DISPLAYED, BYPASSING THE PROBLEMS ENCOUTERED BY JDK11 AND JAVAFX
    public Image getImageDisplay(String path){
        File brandingFile = new File(path);
        return new Image(brandingFile.toURI().toString());
    }



    //FILLS THE LABELS WITH THE USER DATA RETURNED FROM THE QUERY ACCORDINGLY
    public void setUserData(){
        Query q = new Query();
        List<String> userData = q.getUserData(Query.userID);

        usernameLabel.setText(userData.get(0));
        userFullNameLabel.setText(userData.get(1)+" "+ userData.get(2));
    }

    public void setVideoDisplayTab(){
        try {
            WebUtility webUtility = new WebUtility();
            leagueVideoWebView.getEngine().load(webUtility.currentLeagueVideo().toString());
        }catch(Exception e){
            System.out.println("No Internet Connection Available");
        }
    }


    public void setUserBuildsTable(){
        Query q = new Query();
        //SETUP OF COLUMNS IN THE TABLE
        userBuildColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        userBuildColTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        userBuildColLink.setCellValueFactory(new PropertyValueFactory<>("hyperlink"));
        userBuildColLink.setCellFactory(new HyperlinkCell());
        userBuildColDelete.setCellValueFactory(new PropertyValueFactory<>("editButton"));


        userBuildsTable.setItems(q.getUserBuilds(Query.userID));
        userBuildsTable.setOnMouseClicked(e-> userBuildsTable.setItems(q.getUserBuilds(Query.userID)));
    }


    public void setForumGuidesTab(){
        ObservableList<String> options = FXCollections.observableArrayList(classNames);
        classBox.setItems(options);
        ascBox.setDisable(true);
    }



    public void setAscBoxAndImages(ActionEvent event){
        ascBox.setDisable(false);
        if (classBox.getValue().equals("Duelist")){
            classImageView.setImage(getImageDisplay("images/Duelist.png"));
            ObservableList<String> options = FXCollections.observableArrayList(DuelistAscs);
            ascBox.setItems(options);
        }
        if (classBox.getValue().equals("Marauder")){
            classImageView.setImage(getImageDisplay("images/Marauder.png"));
            ObservableList<String> options = FXCollections.observableArrayList(MarauderAscs);
            ascBox.setItems(options);
        }
        if (classBox.getValue().equals("Ranger")){
            classImageView.setImage(getImageDisplay("images/Ranger.png"));
            ObservableList<String> options = FXCollections.observableArrayList(RangerAscs);
            ascBox.setItems(options);
        }
        if (classBox.getValue().equals("Shadow")){
            classImageView.setImage(getImageDisplay("images/Shadow.png"));
            ObservableList<String> options = FXCollections.observableArrayList(ShadowAscs);
            ascBox.setItems(options);
        }
        if (classBox.getValue().equals("Templar")){
            classImageView.setImage(getImageDisplay("images/Templar.png"));
            ObservableList<String> options = FXCollections.observableArrayList(TemplarAscs);
            ascBox.setItems(options);
        }
        if (classBox.getValue().equals("Witch")){
            classImageView.setImage(getImageDisplay("images/Witch.png"));
            ObservableList<String> options = FXCollections.observableArrayList(WitchAscs);
            ascBox.setItems(options);
        }
        if (classBox.getValue().equals("Scion")){
            classImageView.setImage(getImageDisplay("images/Scion.png"));
            ObservableList<String> options = FXCollections.observableArrayList(ScionAsc);
            ascBox.setItems(options);
        }
        ascBox.getSelectionModel().selectFirst();
    }



    public void setAscImageView(ActionEvent event){
        System.out.println(ascBox.getValue());
        if(ascBox.getValue()!=null){

            switch (ascBox.getValue()){
                //DUELIST ASCENDANCIES
                case "Slayer":
                    ascImageView.setImage(getImageDisplay("images/Slayer.png"));
                    break;
                case "Gladiator":
                    ascImageView.setImage(getImageDisplay("images/Gladiator.png"));
                    break;
                case "Champion":
                    ascImageView.setImage(getImageDisplay("images/Champion.png"));
                    break;
                //MARAUDER ASCENDANCIES
                case "Juggernaught":
                    ascImageView.setImage(getImageDisplay("images/Juggernaught.png"));
                    break;
                case "Berserker":
                    ascImageView.setImage(getImageDisplay("images/Berserker.png"));
                    break;
                case "Chieftain":
                    ascImageView.setImage(getImageDisplay("images/Chieftain.png"));
                    break;
                //RANGER ASCENDANCIES
                case "Deadeye":
                    ascImageView.setImage(getImageDisplay("images/Deadeye.png"));
                    break;
                case "Raider":
                    ascImageView.setImage(getImageDisplay("images/Raider.png"));
                    break;
                case "Pathfinder":
                    ascImageView.setImage(getImageDisplay("images/Pathfinder.png"));
                    break;
                //SHADOW ASCENDANCIES
                case "Assassin":
                    ascImageView.setImage(getImageDisplay("images/Assassin.png"));
                    break;
                case "Saboteur":
                    ascImageView.setImage(getImageDisplay("images/Saboteur.png"));
                    break;
                case "Trickster":
                    ascImageView.setImage(getImageDisplay("images/Trickster.png"));
                    break;
                //TEMPLAR ASCENDANCIES
                case "Inquisitor":
                    ascImageView.setImage(getImageDisplay("images/Inquisitor.png"));
                    break;
                case "Hierophant":
                    ascImageView.setImage(getImageDisplay("images/Hierophant.png"));
                    break;
                case "Guardian":
                    ascImageView.setImage(getImageDisplay("images/Guardian.png"));
                    break;
                //WITCH ASCENDANCIES
                case "Occultist":
                    ascImageView.setImage(getImageDisplay("images/Occultist.png"));
                    break;
                case "Necromancer":
                    ascImageView.setImage(getImageDisplay("images/Necromancer.png"));
                    break;
                case "Elementalist":
                    ascImageView.setImage(getImageDisplay("images/Elementalist.png"));
                    break;
                //SCION ASCENDANCY
                case "Ascendant":
                    ascImageView.setImage(getImageDisplay("images/Ascendant.png"));
                    ascImageView.setFitHeight(200);
                    break;
                default:
                    ascImageView.setImage(getImageDisplay("images/logo.png"));
                    break;
            }
        }
    }


    public void goButtonAction(ActionEvent event){
        if (classBox.getValue()!=null && ascBox.getValue()!=null){
            WebUtility web = new WebUtility();
            try {
                URL forumLink = new URL(web.forumLink(ascBox.getValue()));
                List<String> titles = web.TitleReader(forumLink);
                List<String> views = web.ViewsReader(forumLink);
                List<String> links = web.BuildLink(forumLink);
                List<Integer> viewsConverter = web.ViewBoardConverter(views);
                web.BuildShorter(titles,views,links,viewsConverter);

                setForumGuidesTable(titles,views,links);

            }catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }

        }
    }


    public void setForumGuidesTable(List<String> titles, List<String> views, List<String> links){
        forumBuildColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        forumBuildColTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        forumBuildColLink.setCellValueFactory(new PropertyValueFactory<>("hyperlink"));
        forumBuildColLink.setCellFactory(new HyperlinkCell());
        forumBuildColSave.setCellValueFactory(new PropertyValueFactory<>("editButton"));


        ObservableList<BuildGuide> forumGuides = FXCollections.observableArrayList();
        for(int i=0; i<10;i++){
            forumGuides.add(new BuildGuide(views.get(i),titles.get(i),links.get(i),"Save"));
        }

        forumBuildsTable.setItems(forumGuides);

    }

    public void refreshUserBuildsTable(){
        Query q = new Query();
        userBuildsTable.setItems(q.getUserBuilds(Query.userID));
    }






}
