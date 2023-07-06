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
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;
import org.mariuszgromada.math.mxparser.mXparser;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        // Reset Table
        table.getItems().clear();

        Integer i = 1;
        String formula_text = formula_input.getText();
        BigDecimal x_old = new BigDecimal(xOld.getText());
        BigDecimal xn;

        BigDecimal x_old_previous = new BigDecimal(Double.MAX_VALUE);
        BigDecimal xn_previous = new BigDecimal(Double.MAX_VALUE);

        // Define Formula
        formula_text = "f(x) = " + formula_text;
        Function f= new Function(formula_text);

        // TODO - Add stopping point at 0.0001, look at bracket method
        while(i < 100) {
            Argument x_arg = new Argument("x = " + x_old.toString());
            Expression func = new Expression("f(x)", f, x_arg);

            String der_func = "der(" + formula_input.getText() + ", x)";
            Expression derivative = new Expression(der_func, x_arg);

            double f_x_old = func.calculate();
            double f_prime_x_old = derivative.calculate();

            BigDecimal yo = BigDecimal.valueOf(f_x_old).setScale(4, RoundingMode.HALF_UP);

            if (f_prime_x_old == 0) {
                System.out.println("Division by zero occurred. Exiting the iteration.");
                break;
            }

            // Calculate xn
            double fractionValue = f_prime_x_old != 0 ? yo.divide(BigDecimal.valueOf(f_prime_x_old), 4, RoundingMode.HALF_UP).doubleValue() : 0.0;
            BigDecimal fraction = BigDecimal.valueOf(fractionValue);
            xn = x_old.subtract(fraction);


            Argument xn_arg = new Argument("x = " + xn.toString());
            Expression func2 = new Expression("f(x)", f, xn_arg);
            BigDecimal yn = new BigDecimal(func2.calculate()).setScale(4, RoundingMode.HALF_UP);;

            if (xn.equals(xn_previous) && x_old.equals(x_old_previous)) {
                break;
            }


            NRList.add(new TableNRModel(i.toString(), xn.toString(),  x_old.toString(), yn.toString(), yo.toString()));


            x_old_previous = x_old;
            xn_previous = xn;

            x_old = xn;
            i++;

        }

        root_x_input.setText(x_old.toString());
    }
}
