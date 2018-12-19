package com.example.isumatsumi.barangapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SemuabarangItem {

	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("nama_barang")
	@Expose
	private String namaBarang;
	@SerializedName("jenis_barang")
	@Expose
	private String jenisBarang;
	@SerializedName("jumlah")
	@Expose
	private String jumlah;
	@SerializedName("kondisi")
	@Expose
	private String kondisi;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNamaBarang() {
		return namaBarang;
	}

	public void setNamaBarang(String namaBarang) {
		this.namaBarang = namaBarang;
	}

	public String getJenisBarang() {
		return jenisBarang;
	}

	public void setJenisBarang(String jenisBarang) {
		this.jenisBarang = jenisBarang;
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

	@Override
	public String toString() {
		return "SemuabarangItem{" +
				"id='" + id + '\'' +
				", namaBarang='" + namaBarang + '\'' +
				", jenisBarang='" + jenisBarang + '\'' +
				", jumlah='" + jumlah + '\'' +
				", kondisi='" + kondisi + '\'' +
				'}';
	}
}