/*
Please use the following SQL to set up the test Incidents table:

create table if not exists incidents(
    ID int,
    SUBMITTER_ID varchar(255),
    DATE_OCCURRED date,
    TIME_OCCURRED time,
    DATETIME_REPORTED datetime,
    INCIDENT_ADDRESS_LINE_1 varchar(255),
    INCIDENT_ADDRESS_LINE_2 varchar(255),
    INCIDENT_CITY varchar(255),
    INCIDENT_STATE varchar(255),
    INCIDENT_ZIP varchar(255),
    INCIDENT_JURISDICTION varchar(255),
    LOST_OR_STOLEN varchar(255),
    EVIDENCE json,
    ADDL_DESCRIPTION varchar(1000)
);

 */

package com.example._218scenebuilder;

import javafx.beans.property.DoubleProperty;
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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.scene.layout.AnchorPane;

public class HelloController {
    @FXML
    public ScrollBar bar;
    @FXML
    public Label welcomeText;
    @FXML
    public TextField textField_first_middle;
    @FXML
    public TextField textField_last_name;
    @FXML
    public DatePicker datePicker_DOB;
    @FXML
    public TextField textField_address_line1;
    @FXML
    public TextField textField_address_line2;
    @FXML
    public TextField textField_city;
    @FXML
    public TextField textField_state;
    @FXML
    public TextField textField_zipcode;
    @FXML
    public TextField textField_email;
    @FXML
    public DatePicker datePicker_date_missing;
    @FXML
    public TextField textField_incident_time;
    @FXML
    public RadioButton radioButton_lost;
    @FXML
    public RadioButton radioButton_stolen;
    @FXML
    public TextField textField_incident_address_line1;
    @FXML
    public TextField textField_incident_address_line2;
    @FXML
    public TextField textField_incident_city;
    @FXML
    public TextField textField_incident_state;
    @FXML
    public TextField textField_incident_zipcode;
    @FXML
    public TextArea textArea_incident_addl_description;
    @FXML
    public AnchorPane sectionToScroll;

    public void initialize() {
        bar.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> value, Number oldValue, Number newValue) {
                //welcomeText.setLayoutY(newValue.doubleValue());



                // Invert the value property so when you scroll down the content moves up:
                //bar.valueProperty().setValue((Integer.parseInt(bar.valueProperty().toString()) * -1))

                DoubleProperty barproperty = bar.valueProperty();
                String currentValueStr = barproperty.toString();
                int currentValue = Integer.parseInt(currentValueStr);
                barproperty.setValue(-1 * currentValue);

                sectionToScroll.translateYProperty().bind(barproperty);
            }
        });
    }



    String[] fieldValues = new String[14];
    @FXML
    public void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    public void onSubmitClick() {

        LocalDateTime datetime = LocalDateTime.now();

        fieldValues[0] = null; // ID
        fieldValues[1] = textField_email.getText(); // SUBMITTER_ID (this will be the email address)
        fieldValues[2] = datePicker_date_missing.getValue().toString(); // DATE_OCCURRED
        fieldValues[3] = textField_incident_time.getText(); // TIME_OCCURRED
        fieldValues[4] = datetime.toString(); // DATETIME_REPORTED
        fieldValues[5] = textField_incident_address_line1.getText(); // INCIDENT_ADDRESS_LINE_1
        fieldValues[6] = textField_incident_address_line2.getText(); // INCIDENT_ADDRESS_LINE_2
        fieldValues[7] = textField_incident_city.getText(); // INCIDENT_CITY
        fieldValues[8] = textField_incident_state.getText(); // INCIDENT_STATE
        fieldValues[9] = textField_incident_zipcode.getText(); // INCIDENT_ZIP;
        fieldValues[10] = null; // INCIDENT_JURISDICTION-not in use yet

        if (radioButton_lost.isSelected()) {
            fieldValues[11] = radioButton_lost.getText(); // LOST
        } else if (radioButton_stolen.isSelected()) {
            fieldValues[11] = radioButton_stolen.getText(); // STOLEN
        } else {
            fieldValues[11] = null;
        }

        fieldValues[12] = null; // EVIDENCE-not in use yet
        fieldValues[13] = textArea_incident_addl_description.getText(); // ADDL_DESCRIPTION;

        updateIncidentsTable(fieldValues);
    }

    public List[] updateIncidentsTable (String[]inputParam) {

        String url = "jdbc:mysql://localhost:3306/testdb";
        String username = "root";
        String password = "Code";

        //String url = "jdbc:mysql://localhost:3306/reportmissingguns_patmlocaltestdb_v1";
        //String username = "root";
        //String password = " ";

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
            query.append("null, "); // This line can be removed once we figure out the ID auto-incrementing
            for (int i = 1; i <= 13; i++) {
                if (i >= 1 && i != 10 && i != 12) { // 10 and 13 are just excluded for now because they're nulls - thus not strings
                    query.append("\"" + inputParam[i] + "\"");
                } else {
                    query.append(inputParam[i]);
                }
                if (i <= 12) {
                    query.append(", ");
                }
            }
            query.append(");");

            System.out.println(query.toString());

            Statement stmnt = conn.createStatement();
            int rowCount = stmnt.executeUpdate(query.toString()); // rowCount is just the result.  The important part is the executeUpdate.

            List<Integer> ids = new ArrayList<>();
            List<String> submitterIds = new ArrayList<>();
            List<String> datesOccurred = new ArrayList<>();
            List<String> timesOccurred = new ArrayList<>();
            List<String> dateTimesReported = new ArrayList<>();
            List<String> incidentAddrLine1s = new ArrayList<>();
            List<String> incidentAddrLine2s = new ArrayList<>();
            List<String> incidentCities = new ArrayList<>();
            List<String> incidentStates = new ArrayList<>();
            List<String> incidentZipCodes = new ArrayList<>();
            List<String> incidentJurisdictions = new ArrayList<>();
            List<String> lostOrStolenList = new ArrayList<>();
            List<String> evidenceList = new ArrayList<>();
            List<String> additionalDescriptions = new ArrayList<>();

            List[] allTogether = new List[14];

            allTogether[0] = ids;
            allTogether[1] = submitterIds;
            allTogether[2] = datesOccurred;
            allTogether[3] = timesOccurred;
            allTogether[4] = dateTimesReported;
            allTogether[5] = incidentAddrLine1s;
            allTogether[6] = incidentAddrLine2s;
            allTogether[7] = incidentCities;
            allTogether[8] = incidentStates;
            allTogether[9] = incidentZipCodes;
            allTogether[10] = incidentJurisdictions;
            allTogether[11] = lostOrStolenList;
            allTogether[12] = evidenceList;
            allTogether[13] = additionalDescriptions;

            return allTogether;


        } catch (SQLException e) {
            System.out.println("Error - exception");
            System.out.println(e.toString());
        }
        return null;
    }
}

