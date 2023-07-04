package com.mosquida.solutionengine.models;

public class TableSecantModel {
    private String secant_id;
    private String secant_xa;
    private String secant_xb;
    private String secant_xn;
    private String secant_ya;
    private String secant_yb;
    private String secant_yn;

    public TableSecantModel(String secant_id, String secant_xa, String secant_xb, String secant_xn, String secant_ya, String secant_yb, String secant_yn) {
        this.secant_id = secant_id;
        this.secant_xa = secant_xa;
        this.secant_xb = secant_xb;
        this.secant_xn = secant_xn;
        this.secant_ya = secant_ya;
        this.secant_yb = secant_yb;
        this.secant_yn = secant_yn;
    }


    public String getSecant_id() {
        return secant_id;
    }

    public String getSecant_xa() {
        return secant_xa;
    }

    public String getSecant_xb() {
        return secant_xb;
    }

    public String getSecant_xn() {
        return secant_xn;
    }

    public String getSecant_ya() {
        return secant_ya;
    }

    public String getSecant_yb() {
        return secant_yb;
    }

    public String getSecant_yn() {
        return secant_yn;
    }
}
