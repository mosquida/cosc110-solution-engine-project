package com.mosquida.solutionengine.models;

public class JacobiModel {
    private String jacobi_id;
    private String jacobi_x;
    private String jacobi_y;
    private String jacobi_z;

    public JacobiModel(String jacobi_id, String jacobi_x, String jacobi_y, String jacobi_z) {
        this.jacobi_id = jacobi_id;
        this.jacobi_x = jacobi_x;
        this.jacobi_y = jacobi_y;
        this.jacobi_z = jacobi_z;
    }

    public String getJacobi_id() {
        return jacobi_id;
    }

    public String getJacobi_x() {
        return jacobi_x;
    }

    public String getJacobi_y() {
        return jacobi_y;
    }

    public String getJacobi_z() {
        return jacobi_z;
    }
}
