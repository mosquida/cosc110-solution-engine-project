package com.mosquida.solutionengine;

import com.mosquida.solutionengine.models.JacobiModel;
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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;

public class JacobiMethod implements Initializable {

    @FXML
    private TextField a1_input;

    @FXML
    private TextField a2_input;

    @FXML
    private TextField a3_input;

    @FXML
    private TextField b1_input;

    @FXML
    private TextField b2_input;

    @FXML
    private TextField b3_input;

    @FXML
    private TextField c1_input;

    @FXML
    private TextField c2_input;

    @FXML
    private TextField c3_input;

    @FXML
    private TextField d1_input;

    @FXML
    private TextField d2_input;

    @FXML
    private TextField d3_input;


    @FXML
    private TextField root_x_input;

    @FXML
    private TextField root_y_input;

    @FXML
    private TextField root_z_input;

    @FXML
    private TableColumn<JacobiModel, String> jacobi_id;

    @FXML
    private TableColumn<JacobiModel, String> jacobi_x;

    @FXML
    private TableColumn<JacobiModel, String> jacobi_y;

    @FXML
    private TableColumn<JacobiModel, String> jacobi_z;

    ObservableList<JacobiModel> jacobiList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Map Table Column to Model Properties
        jacobi_id.setCellValueFactory(new PropertyValueFactory<JacobiModel, String>("jacobi_id"));
        jacobi_x.setCellValueFactory(new PropertyValueFactory<JacobiModel, String>("jacobi_x"));
        jacobi_y.setCellValueFactory(new PropertyValueFactory<JacobiModel, String>("jacobi_y"));
        jacobi_z.setCellValueFactory(new PropertyValueFactory<JacobiModel, String>("jacobi_z"));


        table.setItems(jacobiList);
    }
    
    @FXML
    private TableView<JacobiModel> table;
    
    
    
    
    @FXML
    void onSolveClick() {
        // Reset Table
        table.getItems().clear();

        Integer i = 0;

        // TODO - CHANGE THE SIGN
        String a1 = a1_input.getText(); ///
        String b1 = reverseSign(new BigDecimal(b1_input.getText()));
        String c1 = reverseSign(new BigDecimal(c1_input.getText()));
        String d1 = d1_input.getText(); ///

        System.out.println(c1_input.getText());
        System.out.println(reverseSign(new BigDecimal(c1_input.getText())));
        System.out.println(String.format("%s, %s, %s, %s",  a1, b1, c1, d1));

        String a2 = reverseSign(new BigDecimal(a2_input.getText()));
        String b2 = b2_input.getText(); ///
        String c2 = reverseSign(new BigDecimal(c2_input.getText()));
        String d2 = d2_input.getText(); ///
        System.out.println(String.format("%s, %s, %s, %s",  a2, b2, c2, d2));


        String a3 = reverseSign(new BigDecimal(a3_input.getText()));
        String b3 = reverseSign(new BigDecimal(b3_input.getText()));
        String c3 = c3_input.getText(); ///
        String d3 = d3_input.getText(); ///
        System.out.println(String.format("%s, %s, %s, %s",  a3, b3, c3, d3));


        BigDecimal x = new BigDecimal(0);
        BigDecimal y = new BigDecimal(0);
        BigDecimal z = new BigDecimal(0);

        // TODO - add stopping point
        while (i < 10) {

            String r1_formula = String.format("( (%s) +  (%s)y +  (%s)z)/ (%s)",d1, b1, c1, a1 );
            String r2_formula = String.format("( (%s) +  (%s)x +  (%s)z)/ (%s)",d2, a2, c2, b2 );
            String r3_formula = String.format("( (%s) +  (%s)x +  (%s)y)/ (%s)",d3, a3, b3, c3 );


            Argument r1b_arg = new Argument("y = " + y.toString());
            Argument r1c_arg = new Argument("z = " + z.toString());
            Expression expression1 = new Expression(r1_formula, r1b_arg, r1c_arg);
            BigDecimal x_new  = BigDecimal.valueOf( expression1.calculate()).setScale(4, RoundingMode.HALF_UP);

            Argument r2a_arg = new Argument("x = " + x.toString());
            Argument r2c_arg = new Argument("z = " + z.toString());
            Expression expression2 = new Expression(r2_formula, r2a_arg, r2c_arg);
            BigDecimal y_new  = BigDecimal.valueOf( expression2.calculate()).setScale(4, RoundingMode.HALF_UP);

            Argument r3a_arg = new Argument("x = " + x.toString());
            Argument r3b_arg = new Argument("y = " + y.toString());
            Expression expression3 = new Expression(r3_formula, r3a_arg, r3b_arg);
            BigDecimal z_new  = BigDecimal.valueOf( expression3.calculate()).setScale(4, RoundingMode.HALF_UP);

//        mXparser.consolePrintln("Res 1: " + expression1.getExpressionString() + " = " + expression1.calculate());
//        mXparser.consolePrintln("Res 2: " + expression2.getExpressionString() + " = " + expression2.calculate());
//        mXparser.consolePrintln("Res 3: " + expression3.getExpressionString() + " = " + expression3.calculate());
            System.out.println(String.format("%d, %s, %s, %s", i, x, y, z ));
            jacobiList.add(new JacobiModel(i.toString(), x.toString(), y.toString(), z.toString()));
            x = x_new;
            y = y_new;
            z = z_new;
            i++;
        }

        this.root_x_input.setText(x.toString());
        this.root_y_input.setText(y.toString());
        this.root_z_input.setText(z.toString());
    }

    public String reverseSign(BigDecimal number) {
        BigDecimal reversed = number.negate(); // Negate the number to reverse the sign
        String result = reversed.toString(); // Convert to string
        return result;

    }
}