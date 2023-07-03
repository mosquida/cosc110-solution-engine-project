package com.mosquida.solutionengine.models;

public class TableFalsiModel {

    private String falsi_id;
    private String falsi_xl;
    private String falsi_xm;
    private String falsi_xr;
    private String falsi_yl;
    private String falsi_ym;
    private String falsi_yr;


    public TableFalsiModel(String falsi_id, String falsi_xl, String falsi_xm, String falsi_xr, String falsi_yl, String falsi_ym, String falsi_yr) {
        this.falsi_id = falsi_id;
        this.falsi_xl = falsi_xl;
        this.falsi_xm = falsi_xm;
        this.falsi_xr = falsi_xr;
        this.falsi_yl = falsi_yl;
        this.falsi_ym = falsi_ym;
        this.falsi_yr = falsi_yr;
    }

    public String getFalsi_id() {
        return falsi_id;
    }

    public String getFalsi_xl() {
        return falsi_xl;
    }

    public String getFalsi_xm() {
        return falsi_xm;
    }

    public String getFalsi_xr() {
        return falsi_xr;
    }

    public String getFalsi_yl() {
        return falsi_yl;
    }

    public String getFalsi_ym() {
        return falsi_ym;
    }

    public String getFalsi_yr() {
        return falsi_yr;
    }
}
