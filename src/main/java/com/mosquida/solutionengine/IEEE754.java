package com.mosquida.solutionengine;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class IEEE754 {
    @FXML
    private TextField sign32;

    @FXML
    private TextField exponent32;

    @FXML
    private TextField mantissa32;

    @FXML
    private TextField sign64;

    @FXML
    private TextField exponent64;

    @FXML
    private TextField mantissa64;

    @FXML
    private TextField decimalInput;

    @FXML
    protected void onSolveClick() {
        String userInput = decimalInput.getText();

        // 32 bit
        float num = Float.parseFloat(userInput);
        sign32.setText(String.valueOf(getSign(num)));
        exponent32.setText( getExponent32(num));
        mantissa32.setText(getMantissa32(num));

        // 64 bit
        double doubleNum = Double.parseDouble(userInput);
        sign64.setText(String.valueOf(getSign(doubleNum)));
        exponent64.setText(getExponent64(doubleNum));
        mantissa64.setText(getMantissa64(doubleNum));
    }

    public static int getSign(double num) {
        return num >= 0 ? 0 : 1;
    }

    public String getExponent64(double num) {
        long bits = Double.doubleToRawLongBits(num);
        long exponentBits = (bits >> 52) & 0x7FF;
        return String.format("%11s", Long.toBinaryString(exponentBits)).replace(' ', '0');
    }

    public  String getMantissa64(double num) {
        long bits = Double.doubleToRawLongBits(num);
        long mantissaBits = bits & 0xFFFFFFFFFFFFFL;
        return String.format("%52s", Long.toBinaryString(mantissaBits)).replace(' ', '0');
    }

    public String getExponent32(float num) {
        int bits = Float.floatToRawIntBits(num);
        int exponentBits = (bits >> 23) & 0xFF;
        return String.format("%8s", Integer.toBinaryString(exponentBits)).replace(' ', '0');
    }

    public String getMantissa32(float num) {
        int bits = Float.floatToRawIntBits(num);
        int mantissaBits = bits & 0x7FFFFF;
        return String.format("%23s", Integer.toBinaryString(mantissaBits)).replace(' ', '0');
    }

    // Numerics only allowed in text-field
    public void initialize() {
        decimalInput.textProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue.matches("\\d*")) return;
//            decimalInput.setText(newValue.replaceAll("[^\\d]", ""));
            // Allow digits and hyphen only
            if (newValue.matches("^-?\\d*")) {
                // Allow optional "-" sign at the beginning and digits
                return;
            } else if (newValue.matches("^-\\d+")) {
                // Allow "-" sign at the beginning followed by digits
                decimalInput.setText(newValue);
            } else {
                // Remove any "-" sign after the first digit or "-"
                decimalInput.setText(oldValue);
            }
        });
    }
}
