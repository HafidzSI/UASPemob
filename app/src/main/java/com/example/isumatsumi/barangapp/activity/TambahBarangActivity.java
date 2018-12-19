package com.example.isumatsumi.barangapp.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.isumatsumi.barangapp.R;
import com.example.isumatsumi.barangapp.util.api.BaseApiService;
import com.example.isumatsumi.barangapp.util.api.UtilsApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahBarangActivity extends AppCompatActivity {

    @BindView(R.id.etNamaBarang)
    EditText etNamaBarang;
    @BindView(R.id.etJenis)
    EditText etJenis;
    @BindView(R.id.etJumlah)
    EditText etJumlah;
    @BindView(R.id.etKondisi)
    EditText etKondisi;
    @BindView(R.id.btnSimpanBarang)
    Button btnSimpanBarang;
    ProgressDialog loading;

    BaseApiService mApiService;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_barang_2);

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        btnSimpanBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestSimpanBarang();
            }
        });
    }

    private void requestSimpanBarang(){
        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);

        mApiService.simpanBarangRequest(etNamaBarang.getText().toString(), etJenis.getText().toString(), etJumlah.getText().toString(), etKondisi.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            Toast.makeText(mContext, "Data Berhasil Ditambahkan", Toast.LENGTH_LONG).show();
                        } else {
                            loading.dismiss();
                            Toast.makeText(mContext, "Gagal Menyimpan Data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        loading.dismiss();
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
