package com.mosquida.solutionengine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.mariuszgromada.math.mxparser.*;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        boolean isCallSuccessful = License.iConfirmNonCommercialUse("Carl Justine Mosquida");
        /* Checking use type confirmation message */
        String message = License.getUseTypeConfirmationMessage();

        mXparser.consolePrintln("message = " + message);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image(String.valueOf(HelloApplication.class.getResource("logo.png"))));
        stage.setTitle("Solution Engine Project");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}