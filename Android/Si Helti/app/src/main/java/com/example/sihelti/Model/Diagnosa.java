package com.example.sihelti.Model;

public class Diagnosa {
    String tanggal, judul, deskripsi, gejala;
    double deceaseWeight;

    public Diagnosa(String tanggal, String judul, String deskripsi, double deceaseWeight, String gejala) {
        this.tanggal = tanggal;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.deceaseWeight = deceaseWeight;
        this.gejala = gejala;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public double getDeceaseWeight() {
        return deceaseWeight;
    }

    public void setDeceaseWeight(double deceaseWeight) {
        this.deceaseWeight = deceaseWeight;
    }

    public String getGejala() {
        return gejala;
    }

    public void setGejala(String gejala) {
        this.gejala = gejala;
    }
}
