package com.mosquida.solutionengine;

import com.mosquida.solutionengine.models.TableBisectionModel;
import com.mosquida.solutionengine.models.TableFalsiModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;
import org.mariuszgromada.math.mxparser.*;

public class BracketMethod implements Initializable {
    @FXML
    private TextField formula_input;

    @FXML
    private TextField precision_input;

    @FXML
    private TextField decimal_input;

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


    @FXML
    private TableColumn<TableFalsiModel, String> falsi_id;

    @FXML
    private TableColumn<TableFalsiModel, String> falsi_xl;

    @FXML
    private TableColumn<TableFalsiModel, String> falsi_xm;

    @FXML
    private TableColumn<TableFalsiModel, String> falsi_xr;

    @FXML
    private TableColumn<TableFalsiModel, String> falsi_yl;

    @FXML
    private TableColumn<TableFalsiModel, String> falsi_ym;

    @FXML
    private TableColumn<TableFalsiModel, String> falsi_yr;

    @FXML
    private TableView<TableFalsiModel> table2;

    ObservableList<TableFalsiModel> falsiList = FXCollections.observableArrayList();

    ObservableList<TableBisectionModel> bisectionList = FXCollections.observableArrayList();

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

        falsi_id.setCellValueFactory(new PropertyValueFactory<TableFalsiModel, String>("falsi_id"));
        falsi_xl.setCellValueFactory(new PropertyValueFactory<TableFalsiModel, String>("falsi_xl"));
        falsi_xm.setCellValueFactory(new PropertyValueFactory<TableFalsiModel, String>("falsi_xm"));
        falsi_xr.setCellValueFactory(new PropertyValueFactory<TableFalsiModel, String>("falsi_xr"));
        falsi_yl.setCellValueFactory(new PropertyValueFactory<TableFalsiModel, String>("falsi_yl"));
        falsi_ym.setCellValueFactory(new PropertyValueFactory<TableFalsiModel, String>("falsi_ym"));
        falsi_yr.setCellValueFactory(new PropertyValueFactory<TableFalsiModel, String>("falsi_yr"));

        table2.setItems(falsiList);
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

    public static boolean countZerosInPrecision(BigDecimal number) {
        int targetPrecision = 4;
        String numberStr = number.toString();
        String decimalPart = numberStr.split("\\.")[1];

        int count = 0;
        for (char digit : decimalPart.toCharArray()) {
            if (digit == '0') {
                count++;
            } else {
                break;
            }
        }

        if (count + 1 == targetPrecision) {
            return false;
        }

        return true;
    }

    @FXML
    void onSolveClick() {
        solveBisection();
        solveFalsi();
    }

    void solveBisection() {
        Integer i = 1;

        // Reset Table
        table.getItems().clear();

        root_x_input.setText("");
        root_y_input.setText("");

        // Get Fields
        String formula_text = formula_input.getText();
        BigDecimal precision= new BigDecimal(precision_input.getText());
        String assumption_xl = assumption_xl_input.getText();
        String assumption_xr= assumption_xr_input.getText();
        int scale = Integer.parseInt(decimal_input.getText());
        if (scale < 4) {
            scale = 4;
            decimal_input.setText("4");
        }

        if (precision.compareTo(new BigDecimal(0.0001)) < 0) {
            precision_input.setText("0.0001");
            precision= new BigDecimal(precision_input.getText());
        }

        // Define Formula
        formula_text = "f(x) = " + formula_text;
        Function f= new Function(formula_text);

        // Evaluate xl
        String arg = "x = " + assumption_xl;
        Argument xl_arg = new Argument(arg);
        Expression e1 = new Expression("f(x)", f, xl_arg);
        BigDecimal yl = new BigDecimal(e1.calculate()).setScale(scale, RoundingMode.HALF_UP);
//        mXparser.consolePrintln(e1.getExpressionString() + "=" + e1.calculate());

        // Evaluate xr
        arg = "x = " + assumption_xr;
        Argument xr_arg = new Argument(arg);
        Expression e2 = new Expression("f(x)", f, xr_arg);
        BigDecimal yr = new BigDecimal(e2.calculate()).setScale(scale, RoundingMode.HALF_UP);;
//         mXparser.consolePrintln(e2.getExpressionString() + "=" + e2.calculate());

        //Compare yl and yr sign
        if (!validateAssumptions(yl, yr) ) {
            System.out.println("not valid");
        } else {
            System.out.println("valid");

            // Get xm - midpoint
            BigDecimal xl = new BigDecimal(assumption_xl);
            BigDecimal xr = new BigDecimal(assumption_xr);
            BigDecimal xm = xl.add(xr).divide(new BigDecimal("2"), scale, RoundingMode.HALF_UP);

            // Evaluate ym
            arg = "x = " + xm.toString();
            Argument xm_arg = new Argument(arg);
            Expression e3 = new Expression("f(x)", f, xm_arg);
            BigDecimal ym = new BigDecimal(e3.calculate()).setScale(scale, RoundingMode.HALF_UP);

            // Init First Row
            bisectionList.add(new TableBisectionModel(i.toString(), xl.toString(), xm.toString(), xr.toString(), yl.toString(),ym.toString(),yr.toString()));
            i++;

            // 2 to n iteration
            // TODO - MODIFY THE ITERATION STOP POINT C<0.001
            while (xr.subtract(xl).abs().compareTo(precision) > 0){
                // Move columns
                int ymSign = (int) getSign(ym);

                if (ymSign == getSign(yl)) {
                    yl = ym;
                    xl = xm;
                } else if (ymSign == getSign(yr)) {
                    yr = ym;
                    xr = xm;
                }

                xm = xl.add(xr).divide(BigDecimal.valueOf(2), scale, RoundingMode.HALF_UP);

                // Evaluate ym
                arg = "x = " + xm.toString();
                xm_arg = new Argument(arg);
                e3 = new Expression("f(x)", f, xm_arg);
                ym = new BigDecimal(e3.calculate()).setScale(scale, RoundingMode.HALF_UP);;

                bisectionList.add(new TableBisectionModel(i.toString(), xl.toString(), xm.toString(), xr.toString(), yl.toString(),ym.toString(),yr.toString()));

                i++;
            }

            // Set roots
            root_x_input.setText(xm.toString());
            root_y_input.setText("0");
        }
    }

    void solveFalsi() {
        Integer i = 1;

        // Reset Table
        table2.getItems().clear();

        // Get Fields
        String formula_text = formula_input.getText();
        BigDecimal precision= new BigDecimal(precision_input.getText());
        String assumption_xl = assumption_xl_input.getText();
        String assumption_xr= assumption_xr_input.getText();
        int scale = Integer.parseInt(decimal_input.getText());
        if (scale < 4) {
            scale = 4;
            decimal_input.setText("4");
        }

        if (precision.compareTo(new BigDecimal(0.0001)) < 0) {
            precision_input.setText("0.0001");
            precision= new BigDecimal(precision_input.getText());
        }

        // Define Formula
        formula_text = "f(x) = " + formula_text;
        Function f= new Function(formula_text);

        // Evaluate xl
        String arg = "x = " + assumption_xl;
        Argument xl_arg = new Argument(arg);
        Expression e1 = new Expression("f(x)", f, xl_arg);
        BigDecimal yl = new BigDecimal(e1.calculate()).setScale(scale, RoundingMode.HALF_UP);
//        mXparser.consolePrintln(e1.getExpressionString() + "=" + e1.calculate());
        System.out.println(yl);
        // Evaluate xr
        arg = "x = " + assumption_xr;
        Argument xr_arg = new Argument(arg);
        Expression e2 = new Expression("f(x)", f, xr_arg);
        BigDecimal yr = new BigDecimal(e2.calculate()).setScale(scale, RoundingMode.HALF_UP);;
//         mXparser.consolePrintln(e2.getExpressionString() + "=" + e2.calculate());
        System.out.println(yr);

        //Compare yl and yr sign
        if (!validateAssumptions(yl, yr) ) {
            System.out.println("not valid");
        } else {
            System.out.println("valid");

            // Get xm - midpoint
            BigDecimal xl = new BigDecimal(assumption_xl);
            BigDecimal xr = new BigDecimal(assumption_xr);
            BigDecimal numerator = xr.subtract(xl);
            BigDecimal denominator = yl.subtract(yr);
            BigDecimal fraction = numerator.multiply(yl).divide(denominator, RoundingMode.HALF_UP);
            BigDecimal xm = xl.add(fraction);
            xm = xm.setScale(scale, RoundingMode.HALF_UP);
            System.out.println(xm);

            // Evaluate ym
            arg = "x = " + xm.toString();
            Argument xm_arg = new Argument(arg);
            Expression e3 = new Expression("f(x)", f, xm_arg);
            BigDecimal ym = new BigDecimal(e3.calculate()).setScale(scale, RoundingMode.HALF_UP);
            System.out.println(ym);


            // Init First Row
            falsiList.add(new TableFalsiModel(i.toString(), xl.toString(), xm.toString(), xr.toString(), yl.toString(),ym.toString(),yr.toString()));
            i++;

            // 2 to n iteration
            // TODO - MODIFY THE ITERATION STOP POINT C<0.001

            while (countZerosInPrecision(ym)){
                System.out.println(ym.abs().compareTo(precision));
                System.out.println(ym.abs());
                System.out.println(precision);
                // Move columns
                int ymSign = (int) getSign(ym);

                if (ymSign == getSign(yl)) {
                    yl = ym;
                    xl = xm;
                } else if (ymSign == getSign(yr)) {
                    yr = ym;
                    xr = xm;
                }

                numerator = xr.subtract(xl);
                denominator = yl.subtract(yr);
                fraction = numerator.multiply(yl).divide(denominator, RoundingMode.HALF_UP);
                 xm = xl.add(fraction);
                xm = xm.setScale(scale, RoundingMode.HALF_UP);

                // Evaluate ym
                arg = "x = " + xm.toString();
                xm_arg = new Argument(arg);
                e3 = new Expression("f(x)", f, xm_arg);
                ym = new BigDecimal(e3.calculate()).setScale(scale, RoundingMode.HALF_UP);;

                falsiList.add(new TableFalsiModel(i.toString(), xl.toString(), xm.toString(), xr.toString(), yl.toString(),ym.toString(),yr.toString()));

                i++;
            }

        }
    }


}
