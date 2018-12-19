package com.example.isumatsumi.barangapp.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseBarang {

	@SerializedName("semuabarang")
	private List<SemuabarangItem> semuabarang;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	public void setSemuabarang(List<SemuabarangItem> semuabarang){
		this.semuabarang = semuabarang;
	}

	public List<SemuabarangItem> getSemuabarang(){
		return semuabarang;
	}

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"ResponseBarang{" +
			"semuabarang = '" + semuabarang + '\'' +
			",error = '" + error + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}