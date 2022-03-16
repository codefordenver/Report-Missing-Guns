package com.example.reportmissingguns;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.*;
import java.lang.StringBuilder;
import java.util.Calendar;

import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField textField_first_middle;
    @FXML
    private TextField textField_last_name;
    @FXML
    private DatePicker datePicker_DOB;
    @FXML
    private TextField textField_address_line1;
    @FXML
    private TextField textField_address_line2;
    @FXML
    private TextField textField_city;
    @FXML
    private TextField textField_state;
    @FXML
    private TextField textField_zipcode;
    @FXML
    private  TextField textField_email;
    @FXML
    private  DatePicker dataPicker_date_missing;
    @FXML
    private TextField textField_incident_time;
    @FXML
    private RadioButton radioButton_lost;
    @FXML
    private RadioButton radioButton_stolen;
    @FXML
    private TextField textField_incident_address_line1;
    @FXML
    private TextField textField_incident_address_line2;
    @FXML
    private TextField textField_incident_city;
    @FXML
    private TextField textField_incident_state;
    @FXML
    private TextField textField_incident_zipcode;
    @FXML
    private TextArea textArea_incident_addl_description;

    String[] fieldValues = new String[16];
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    public void onSubmitClick() {
//        long millis=System.currentTimeMillis();
//        java.util.Date date=new java.util.Date(millis);
//        System.out.println(date);
        LocalDateTime date = LocalDateTime.now();


        fieldValues[0] = null; // ID
        fieldValues[1] = textField_email.getText(); // SUBMITTER_ID (this will be the email address)
        fieldValues[2] = dataPicker_date_missing.getValue().toString(); // DATE_OCCURRED
        fieldValues[3] = textField_incident_time.getText(); // TIME_OCCURRED
        fieldValues[4] = date.toString(); // DATE_REPORTED
        fieldValues[5] = null; // TIME_REPORTED-old not in use
        fieldValues[6] = textField_incident_address_line1.getText(); // INCIDENT_ADDRESS_LINE_1
        fieldValues[7] = textField_incident_address_line2.getText(); // INCIDENT_ADDRESS_LINE_2
        fieldValues[8] = textField_incident_city.getText(); // INCIDENT_CITY
        fieldValues[9] = textField_incident_state.getText(); // INCIDENT_STATE
        fieldValues[10] = textField_incident_zipcode.getText(); // INCIDENT_ZIP;
        fieldValues[11] = radioButton_lost.getText(); // LOST
        fieldValues[12] = radioButton_stolen.getText(); // STOLEN
        fieldValues[13] = null; // EVIDENCE-not in use yet
        fieldValues[14] = textArea_incident_addl_description.getText(); // ADDL_DESCRIPTION;

        updateIncidentsTable(fieldValues);
    }


            public List[] updateIncidentsTable (String[]inputParam){

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
                    System.out.println(conn.isValid(0));
                    StringBuilder query = new StringBuilder();
                    query.append("insert into incidents values(");
                    query.append("`id` int(11) NOT NULL AUTO_INCREMENT,")
                    for (int i = 1; i < 16; i++) {
                        if (i >= 2 && i != 14) {
                            query.append("\"" + inputParam[i] + "\"");
                        } else {
                            query.append(inputParam[i]);
                        }
                        if (i < 15) {
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

