package main.classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Query {
    private final Database db;
    private final Connection conn;

    public static int userID;

    public Query(){
        this.db = new Database();
        this.conn = db.openConnection();
    }


    //CHECKS THE DATABASE FOR THE DATA GIVEN BY THE HOST AND RETURNS A BOOLEAN DEPENDING ON THE RESULT
    public Boolean verifyLogin(String username, String password){
        String sql = "SELECT cred_id FROM credentials WHERE username = ? AND password = ?";
        boolean userFound = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet resultSet = stmt.executeQuery();
            System.out.println(stmt);
            if (resultSet.next()){
                userFound = true;
            }
        }catch(SQLException e){
            e.printStackTrace();
            e.getCause();
        }
        return userFound;
    }


    //INSERTS ALL DATA FROM THE REGISTRATION FORM IN OUR DATABASE TABLES (users AND credentials)
    public void userRegistration(List<String> userData){
        //FIRSTLY CREATES A NEW ROW IN THE users TABLE AND UPDATES ALL COLUMNS
        String sql = "INSERT INTO users (firstname, lastname, email, nickname) VALUES (?,?,?,?)";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,userData.get(0));
            stmt.setString(2,userData.get(1));
            stmt.setString(3,userData.get(2));
            stmt.setString(4,userData.get(3));
            stmt.executeUpdate();
            System.out.println(stmt);

        }catch (SQLException e){
            e.printStackTrace();
            e.getCause();
        }

        //SECONDLY LOADS THE AUTO-INCREMENTED VALUE OF THE NEWLY FORMED users ROW TO BE USED AS A PRIMARY KEY IN OUR credentials TABLE ROW CREATED BELOW
        String sql2 = "SELECT user_id FROM users WHERE firstname = ? AND email = ?";
        int id=0;
        try{
            PreparedStatement stmt2 = conn.prepareStatement(sql2);
            stmt2.setString(1,userData.get(0));
            stmt2.setString(2,userData.get(2));
            ResultSet resultSet = stmt2.executeQuery();
            System.out.println(stmt2);

            while(resultSet.next()){
                id = resultSet.getInt(1);
                System.out.println(id);
            }



        }catch (SQLException e2){
            e2.printStackTrace();
            e2.getCause();
        }

        //FINALLY CREATES A NEW credentials ROW USING THE DATA OBTAINED BY THE ABOVE QUERIES
        String sql3 = "INSERT INTO credentials (username, password, cred_id) VALUES (?,?,?)";
        try{
            PreparedStatement stmt3 = conn.prepareStatement(sql3);
            stmt3.setString(1,userData.get(3));
            stmt3.setString(2,userData.get(4));
            stmt3.setString(3,String.valueOf(id));
            stmt3.executeUpdate();
            System.out.println(stmt3);


        }catch (SQLException e3){
            e3.printStackTrace();
            e3.getCause();
        }

    }


    //RETURNS THE USERS ID BASED ON THE LOGIN CREDENTIALS PROVIDED
    public int getUserID(String username, String password){
        int userID=0;
        String sql = "SELECT users.user_id FROM users JOIN credentials ON credentials.cred_id = users.user_id WHERE credentials.username = ? AND credentials.password = ?";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,username);
            stmt.setString(2,password);
            System.out.println(stmt);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()){
                userID = resultSet.getInt(1);
                //System.out.println(userID);
            }

        }catch(SQLException e){
            e.printStackTrace();
            e.getCause();
        }

        return userID;
    }


    //RETURNS THE USERS FIRSTNAME, LASTNAME AND NICKNAME FROM THE DATABASE
    public List<String> getUserData(int userID){
        List<String> userData = new ArrayList<>();
        String sql = "SELECT users.nickname, users.firstname, users.lastname FROM users WHERE users.user_id = ?";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,String.valueOf(userID));
            System.out.println(stmt);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                userData.add(resultSet.getNString(1));
                userData.add(resultSet.getNString(2));
                userData.add(resultSet.getNString(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
            e.getCause();
        }
        return userData;
    }



    public ObservableList<BuildGuide> getUserBuilds(int user_id){
        String sql = "SELECT build_name, build_title, build_link FROM user_build_guides WHERE build_user_id = ?";
        ObservableList<BuildGuide> buildGuides = FXCollections.observableArrayList();
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,String.valueOf(user_id));
            System.out.println(stmt);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                buildGuides.add(new BuildGuide(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),"Delete"));
            }
        }catch(SQLException e){
            e.printStackTrace();
            e.getCause();
        }


        return buildGuides;
    }

    public void deleteBuild(String name){
        String sql = "DELETE FROM user_build_guides WHERE user_build_guides.build_name = ?";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,name);
            System.out.println(stmt);
            stmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
            e.getCause();
        }

    }

    public void saveBuild(String name, String title, String link, int user_id){
        String sql = "INSERT INTO user_build_guides (build_name, build_title, build_link, build_user_id) VALUES (?,?,?,?)";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,name);
            stmt.setString(2,title);
            stmt.setString(3,link);
            stmt.setString(4,String.valueOf(user_id));
            System.out.println(stmt);
            stmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
            e.getCause();
        }
    }


}
