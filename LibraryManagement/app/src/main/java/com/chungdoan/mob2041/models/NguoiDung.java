package com.chungdoan.mob2041.models;

public class NguoiDung {
    private int mand;
    private String tennd;
    private String diachi;
    private String sdt;
    private String tendangnhap;
    private String matkhau;
    private int role;

    // Constructor
    public NguoiDung(int mand, String tennd, String diachi, String sdt, String tendangnhap, String matkhau, int role) {
        this.mand = mand;
        this.tennd = tennd;
        this.diachi = diachi;
        this.sdt = sdt;
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
        this.role = role;
    }

    // Getters and Setters
    public int getMand() {
        return mand;
    }

    public void setMand(int mand) {
        this.mand = mand;
    }

    public String getTennd() {
        return tennd;
    }

    public void setTennd(String tennd) {
        this.tennd = tennd;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
