package com.example.isumatsumi.barangapp.model;

/**
 * Created by Isumatsumi on 20/12/2018.
 */

public class Data {
    private String nama_barang,jenis_barang,jumlah,kondisi;

    public Data()
    {

    }

    public Data(String nama_barang, String jenis_barang, String jumlah, String kondisi) {
        this.nama_barang = nama_barang;
        this.jenis_barang = jenis_barang;
        this.jumlah = jumlah;
        this.kondisi = kondisi;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getJenis_barang() {
        return jenis_barang;
    }

    public void setJenis_barang(String jenis_barang) {
        this.jenis_barang = jenis_barang;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }
}
