package com.mosquida.solutionengine.models;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class TableBisectionModel{

    private String bisection_id;
    private String bisection_xl;
    private String bisection_xm;
    private String bisection_xr;
    private String bisection_yl;
    private String bisection_ym;
    private String bisection_yr;


    public TableBisectionModel(String bisection_id, String bisection_xl, String bisection_xm, String bisection_xr, String bisection_yl, String bisection_ym, String bisection_yr) {
        this.bisection_id = bisection_id;
        this.bisection_xl = bisection_xl;
        this.bisection_xm = bisection_xm;
        this.bisection_xr = bisection_xr;
        this.bisection_yl = bisection_yl;
        this.bisection_ym = bisection_ym;
        this.bisection_yr = bisection_yr;
    }

    public String getBisection_id() {
        return bisection_id;
    }

    public String getBisection_xl() {
        return bisection_xl;
    }

    public String getBisection_xm() {
        return bisection_xm;
    }

    public String getBisection_xr() {
        return bisection_xr;
    }

    public String getBisection_yl() {
        return bisection_yl;
    }

    public String getBisection_ym() {
        return bisection_ym;
    }

    public String getBisection_yr() {
        return bisection_yr;
    }
}
