module poeapp {

    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;
    requires java.sql;
    requires mysql.connector.java;
    requires org.jsoup;

    opens main;
    opens main.classes;
}