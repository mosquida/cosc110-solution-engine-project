package com.mosquida.solutionengine;

import com.mosquida.solutionengine.models.TableNRModel;
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

public class NewtonRaphsonMethod implements Initializable {
    @FXML
    private TextField formula_input;

    @FXML
    private TableColumn<TableNRModel, String> nr_id;

    @FXML
    private TableColumn<TableNRModel, String> nr_xn;

    @FXML
    private TableColumn<TableNRModel, String> nr_xo;

    @FXML
    private TableColumn<TableNRModel, String> nr_yn;

    @FXML
    private TableColumn<TableNRModel, String> nr_yo;

    @FXML
    private TextField root_x_input;

    @FXML
    private TextField xOld;

    @FXML
    private TableView<TableNRModel> table;

    ObservableList<TableNRModel> NRList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Map Table Column to Model Properties
        nr_id.setCellValueFactory(new PropertyValueFactory<TableNRModel, String>("nr_id"));
        nr_xn.setCellValueFactory(new PropertyValueFactory<TableNRModel, String>("nr_xn"));
        nr_xo.setCellValueFactory(new PropertyValueFactory<TableNRModel, String>("nr_xo"));
        nr_yn.setCellValueFactory(new PropertyValueFactory<TableNRModel, String>("nr_yn"));
        nr_yo.setCellValueFactory(new PropertyValueFactory<TableNRModel, String>("nr_yo"));
        
        table.setItems(NRList);
    }
    
    @FXML
    void onSolveClick() {
        NRList.add(new TableNRModel("1", "2", "2", "3", "4"));

    }
}
