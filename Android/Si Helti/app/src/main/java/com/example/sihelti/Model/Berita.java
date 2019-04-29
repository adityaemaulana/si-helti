package com.example.sihelti.Model;

public class Berita {
    String judul, tanggal, deskripsi, urlImg;

    public Berita(String judul, String tanggal, String deskripsi, String urlImg) {
        this.judul = judul;
        this.tanggal = tanggal;
        this.deskripsi = deskripsi;
        this.urlImg = urlImg;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }
}
