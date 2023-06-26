package com.mosquida.solutionengine;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("single-precision.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Single Precision IEEE754 ");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}