package com.example.onthi;

public class DocGia {
    int gioiTinh;
    String tenDG, maDG;
    boolean checkBox;

    public DocGia() {
    }

    public DocGia(int gioiTinh, String tenDG, String maDG) {
        this.gioiTinh = gioiTinh;
        this.tenDG = tenDG;
        this.maDG = maDG;
        this.checkBox = false;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getTenDG() {
        return tenDG;
    }

    public void setTenDG(String tenDG) {
        this.tenDG = tenDG;
    }

    public String getMaDG() {
        return maDG;
    }

    public void setMaDG(String maDG) {
        this.maDG = maDG;
    }

    public boolean isCheckBox() {
        return checkBox;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }
}
