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
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private TextField xa_input;

    @FXML
    private TextField xb_input;

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

        Integer i = 1;
        String formula_text = formula_input.getText();
        System.out.println(xa_input.getText());
        BigDecimal xa = new BigDecimal(xa_input.getText());
        BigDecimal xb = new BigDecimal(xb_input.getText());

        BigDecimal xn = null;
        BigDecimal ya;
        BigDecimal yb;
        BigDecimal yn;

        // Define Formula
        formula_text = "f(x) = " + formula_text;
        Function f= new Function(formula_text);

        // TODO - Add stopping point at 0.0001, look at bracket method
        while(i < 100) {
            System.out.println("a" + xa);
            System.out.println("b" + xb);
            Argument a_arg = new Argument("x = " + xa.toString());
            Expression func1 = new Expression("f(x)", f, a_arg);

            Argument b_arg = new Argument("x = " + xb.toString());
            Expression func2 = new Expression("f(x)", f, b_arg);

            double f_a = func1.calculate();
            double f_b = func2.calculate();

            BigDecimal numerator = new BigDecimal(f_a).multiply(xa.subtract(xb));
            BigDecimal denominator = new BigDecimal(f_a).subtract(new BigDecimal(f_b));
            try {
                xn = xa.subtract(numerator.divide(denominator, 4, RoundingMode.HALF_UP));
                System.out.println("n" + xn);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }

            // ya, yb, yn
            Argument ya_arg = new Argument("x = " + xa.toString());
            Expression func3 = new Expression("f(x)", f, ya_arg);

            Argument yb_arg = new Argument("x = " + xb.toString());
            Expression func4 = new Expression("f(x)", f, yb_arg);

            Argument yn_arg = new Argument("x = " + xn.toString());
            Expression func5 = new Expression("f(x)", f, yn_arg);

            double f_ya = func3.calculate();
            double f_yb = func4.calculate();
            double f_yn = func5.calculate();

            ya = new BigDecimal(f_ya).setScale(4, RoundingMode.HALF_UP);
            yb = new BigDecimal(f_yb).setScale(4, RoundingMode.HALF_UP);
            yn = new BigDecimal(f_yn).setScale(4, RoundingMode.HALF_UP);

            SecantList.add(new TableSecantModel(i.toString(),xa.toString(),xb.toString(),xn.toString(), ya.toString(), yb.toString(), yn.toString()));
            xa = xb;
            xb = xn;


            i++;
        }

        assert xn != null;
        root_x_input.setText(xn.toString());
    }

}
