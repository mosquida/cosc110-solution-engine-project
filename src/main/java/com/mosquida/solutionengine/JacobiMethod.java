package com.mosquida.solutionengine;

import javafx.fxml.FXML;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;
import org.mariuszgromada.math.mxparser.mXparser;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class JacobiMethod {


    @FXML
    void onSolveClick() {
        Integer i = 0;

        // TODO - CHANGE THE SIGN
        String a1 = "20"; ///
        String b1 = "-1";
        String c1 = "2";
        String d1 = "17";

        String a2 = "-3";
        String b2 = "20"; ///
        String c2 = "1";
        String d2 = "-18";

        String a3 = "-2";
        String b3 = "3";
        String c3 = "20"; ///
        String d3 = "25";

        BigDecimal x = new BigDecimal(0);
        BigDecimal y = new BigDecimal(0);
        BigDecimal z = new BigDecimal(0);

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

            x = x_new;
            y = y_new;
            z = z_new;
            i++;
        }



    }

}
