package com.doanchung.model;

import java.io.Serializable;

public class NhanSu implements Serializable {
    private String maNhanVien;
    private String tenNhanVien;
    private String tenPhongBan;

    public NhanSu(String maNhanVien, String tenNhanVien, String tenPhongBan){
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.tenPhongBan = tenPhongBan;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getTenPhongBan() {
        return tenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }
}
