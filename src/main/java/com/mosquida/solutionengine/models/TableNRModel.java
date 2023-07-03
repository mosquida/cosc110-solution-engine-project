package com.mosquida.solutionengine.models;

public class TableNRModel {

    private String nr_id;
    private String nr_xn;
    private String nr_xo;
    private String nr_yn;
    private String nr_yo;


    public TableNRModel(String nr_id, String nr_xn, String nr_xo, String nr_yn, String nr_yo) {
        this.nr_id = nr_id;
        this.nr_xn = nr_xn;
        this.nr_xo = nr_xo;
        this.nr_yn = nr_yn;
        this.nr_yo = nr_yo;
    }

    public String getNr_id() {
        return nr_id;
    }

    public String getNr_xn() {
        return nr_xn;
    }

    public String getNr_xo() {
        return nr_xo;
    }

    public String getNr_yn() {
        return nr_yn;
    }

    public String getNr_yo() {
        return nr_yo;
    }
}
