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
    protected void onIEEEConvertClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IEEE754-view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Single and Double Precision IEEE754 Conversion");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    protected void onBracketMethodClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bracket-method-view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Bisection and False Method Solver");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    protected void onNewtonRaphsonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newton-raphson-method-view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Newton Raphson Method Solver");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    protected void onSecantClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("secant-method-view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Secant Method Solver");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}