package com.example.reportmissingguns;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


    public class WriteIncidentsHelper_v1 {
        public List[] updateIncidentsTable(String[] inputParam) {

            String url = "jdbc:mysql://localhost:3306/testdb";
            String username = "root";
            String password = "Code";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Class not found");
            }

            try (
                    Connection conn = DriverManager.getConnection(url, username, password);
            ) {
                StringBuilder query = new StringBuilder();
                query.append("insert into incidents values(");
                for(int i = 0; i < 16; i++) {
                    if(i >= 2 && i != 14) {
                        query.append("\"" + inputParam[i] + "\"");
                    } else {
                        query.append(inputParam[i]);
                    }
                    if(i < 15) {
                        query.append(", ");
                    }
                }
                query.append(");");

                // insert into incidents values(4, 456, "2022-03-13", "10:10:00.0000000", "2022-03-13", "10:11:00.0000000", "345", "6D", "S McIntyre St", "Lakewood", "CO", "80401", "Lakewood PD", "Stolen", null, "TestDescription");

                System.out.println(query.toString());


                Statement stmnt = conn.createStatement();
                int rowCount = stmnt.executeUpdate(query.toString());

                List<Integer> ids = new ArrayList<>();
                List<String> submitterIds = new ArrayList<>();
                List<String> datesOccurred = new ArrayList<>();
                List<String> timesOccurred = new ArrayList<>();
                List<String> datesReported = new ArrayList<>();
                List<String> timesReported = new ArrayList<>();
                List<String> incidentHouseNumbers = new ArrayList<>();
                List<String> incidentUnitNumbers = new ArrayList<>();
                List<String> incidentStreets = new ArrayList<>();
                List<String> incidentCities = new ArrayList<>();
                List<String> incidentStates = new ArrayList<>();
                List<String> incidentZipCodes = new ArrayList<>();
                List<String> incidentJurisdictions = new ArrayList<>();
                List<String> lostOrStolenList = new ArrayList<>();
                List<String> evidenceList = new ArrayList<>();
                List<String> additionalDescriptions = new ArrayList<>();



                List[] allTogether = new List[16];



                return allTogether;


            } catch (SQLException e) {
                System.out.println("Error - exception");
                System.out.println(e.toString());
            }
            return null;
        }
    }
}
