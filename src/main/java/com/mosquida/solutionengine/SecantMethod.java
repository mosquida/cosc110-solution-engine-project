package com.mosquida.solutionengine;

import com.mosquida.solutionengine.models.TableNRModel;
import com.mosquida.solutionengine.models.TableSecantModel;
import com.mosquida.solutionengine.models.TableSecantModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class SecantMethod implements Initializable {

    @FXML
    private TextField formula_input;

    @FXML
    private TextField root_x_input;

    @FXML
    private TableColumn<TableSecantModel, String> secant_id;

    @FXML
    private TableColumn<TableSecantModel, String> secant_xa;

    @FXML
    private TableColumn<TableSecantModel, String> secant_xb;

    @FXML
    private TableColumn<TableSecantModel, String> secant_xn;

    @FXML
    private TableColumn<TableSecantModel, String> secant_ya;

    @FXML
    private TableColumn<TableSecantModel, String> secant_yb;

    @FXML
    private TableColumn<TableSecantModel, String> secant_yn;

    @FXML
    private TableView<TableSecantModel> table;

    @FXML
    private TextField xa;

    @FXML
    private TextField xb;

    ObservableList<TableSecantModel> SecantList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Map Table Column to Model Properties
        secant_id.setCellValueFactory(new PropertyValueFactory<TableSecantModel, String>("secant_id"));
        secant_xa.setCellValueFactory(new PropertyValueFactory<TableSecantModel, String>("secant_xa"));
        secant_xb.setCellValueFactory(new PropertyValueFactory<TableSecantModel, String>("secant_xb"));
        secant_xn.setCellValueFactory(new PropertyValueFactory<TableSecantModel, String>("secant_xn"));
        secant_ya.setCellValueFactory(new PropertyValueFactory<TableSecantModel, String>("secant_ya"));
        secant_yb.setCellValueFactory(new PropertyValueFactory<TableSecantModel, String>("secant_yb"));
        secant_yn.setCellValueFactory(new PropertyValueFactory<TableSecantModel, String>("secant_yn"));

        table.setItems(SecantList);
    }

    @FXML
    void onSolveClick() {
        SecantList.add(new TableSecantModel("1","1","1","1","1","1","1"));
    }
}
