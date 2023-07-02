package com.mosquida.solutionengine;

import com.mosquida.solutionengine.models.TableBisectionModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import org.mariuszgromada.math.mxparser.*;

public class BracketMethod implements Initializable {
    @FXML
    private TextField formula_input;

    @FXML
    private TextField precision_input;

    @FXML
    private TextField root_x_input;

    @FXML
    private TextField root_y_input;

    @FXML
    private TextField assumption_xl_input;

    @FXML
    private TextField assumption_xr_input;

    @FXML
    private TableColumn<TableBisectionModel, String> bisection_id;

    @FXML
    private TableColumn<TableBisectionModel, String> bisection_xl;

    @FXML
    private TableColumn<TableBisectionModel, String> bisection_xm;

    @FXML
    private TableColumn<TableBisectionModel, String> bisection_xr;

    @FXML
    private TableColumn<TableBisectionModel, String> bisection_yl;

    @FXML
    private TableColumn<TableBisectionModel, String> bisection_ym;

    @FXML
    private TableColumn<TableBisectionModel, String> bisection_yr;

    @FXML
    private TableView<TableBisectionModel> table;

    ObservableList<TableBisectionModel> bisectionList = FXCollections.observableArrayList(
            new TableBisectionModel("1", "1", "2", "34", "3","3","3"),
            new TableBisectionModel("1", "1", "2", "34", "3","3","3")
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Map Table Column to Model Properties
        bisection_id.setCellValueFactory(new PropertyValueFactory<TableBisectionModel, String>("bisection_id"));
        bisection_xl.setCellValueFactory(new PropertyValueFactory<TableBisectionModel, String>("bisection_xl"));
        bisection_xm.setCellValueFactory(new PropertyValueFactory<TableBisectionModel, String>("bisection_xm"));
        bisection_xr.setCellValueFactory(new PropertyValueFactory<TableBisectionModel, String>("bisection_xr"));
        bisection_yl.setCellValueFactory(new PropertyValueFactory<TableBisectionModel, String>("bisection_yl"));
        bisection_ym.setCellValueFactory(new PropertyValueFactory<TableBisectionModel, String>("bisection_ym"));
        bisection_yr.setCellValueFactory(new PropertyValueFactory<TableBisectionModel, String>("bisection_yr"));

        table.setItems(bisectionList);
    }

    public static int getSign(BigDecimal number) {
        return number.compareTo(BigDecimal.ZERO) < 0 ? -1 : 1;
    }

    public static boolean validateAssumptions(BigDecimal yl, BigDecimal yr) {
        int ylSign = getSign(yl);
        int yrSign = getSign(yr);

        // yields same signs
        if (ylSign == yrSign) {
            return false;
        }

        // yields diff sign
        return true;
    }

    @FXML
    void onSolveClick() {
        // Sample Add new data to table
//        bisectionList.add(new TableBisectionModel("2", "1", "2", "34", "3","3","3"));
        String formula_text = formula_input.getText();
        BigDecimal precision= new BigDecimal(precision_input.getText());
        String assumption_xl = assumption_xl_input.getText();
        String assumption_xr= assumption_xr_input.getText();


        // Evaluate if assumptions are valid
        // Define Formula
        formula_text = "f(x) = " + formula_text;
        Function f= new Function(formula_text);

        // Evaluate xl
        String arg = "x = " + assumption_xl;
        Argument xl = new Argument(arg);
        Expression e1 = new Expression("f(x)", f, xl);
        BigDecimal yl = new BigDecimal(e1.calculate());
        //mXparser.consolePrintln(e1.getExpressionString() + "=" + e1.calculate());

        // Evaluate xr
        arg = "x = " + assumption_xr;
        Argument xr = new Argument(arg);
        Expression e2 = new Expression("f(x)", f, xr);
        BigDecimal yr = new BigDecimal(e2.calculate());
        // mXparser.consolePrintln(e2.getExpressionString() + "=" + e2.calculate());

        //Compare yl and yr sign
        if (!validateAssumptions(yl, yr) ) {
            System.out.println("not valid");
        } else {
            System.out.println("valid");
        }

    }





}
