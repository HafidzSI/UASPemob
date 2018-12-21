package com.example.isumatsumi.barangapp.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.isumatsumi.barangapp.R;
import com.example.isumatsumi.barangapp.util.Constant;
import com.example.isumatsumi.barangapp.util.api.BaseApiService;
import com.example.isumatsumi.barangapp.util.api.UtilsApi;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarangDetailActivity extends AppCompatActivity {

    @BindView(R.id.ivTextDrawable)
    ImageView ivTextDrawable;
    @BindView(R.id.tvNamaBarang)
    TextView tvNamaBarang;
    @BindView(R.id.tvJenis)
    TextView tvJenis;
    @BindView(R.id.tvJumlah)
    TextView tvJumlah;
    @BindView(R.id.tvKondisi)
    TextView tvKondisi;
    @BindView(R.id.btnHapus)
    Button btnHapus;
    @BindView(R.id.btnFavorit)
    Button btnFavorit;
    @BindView(R.id.btnShare)
    Button btnShare;

    ProgressDialog loading;

    String mId;
    String mNamaBarang;
    String mJenis;
    String mJumlah;
    String mKondisi;

    Context mContext;
    BaseApiService mApiService;

    public String[] mColors = {
            "#39add1", // light blue
            "#3079ab", // dark blue
            "#c25975", // mauve
            "#e15258", // red
            "#f9845b", // orange
            "#838cc7", // lavender
            "#7d669e", // purple
            "#53bbb4", // aqua
            "#51b46d", // green
            "#e0ab18", // mustard
            "#637a91", // dark gray
            "#f092b0", // pink
            "#b7c0c7"  // light gray
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang_detail);
        getSupportActionBar().setTitle("Barang Detail");

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        Intent intent = getIntent();
        mId = intent.getStringExtra(Constant.KEY_ID_BARANG);
        mNamaBarang = intent.getStringExtra(Constant.KEY_NAMA_BARANG);
        mJenis = intent.getStringExtra(Constant.KEY_JENIS);
        mJumlah = intent.getStringExtra(Constant.KEY_JUMLAH);
        mKondisi = intent.getStringExtra(Constant.KEY_KONDISI);

        tvNamaBarang.setText(mNamaBarang);
        tvJenis.setText(mJenis);
        tvJumlah.setText(mJumlah);
        tvKondisi.setText(mKondisi);

        String firstCharNamaBarang = mJenis.substring(0,1);
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(firstCharNamaBarang, getColor());
        ivTextDrawable.setImageDrawable(drawable);

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDeleteBarang();
            }
        });
        btnFavorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestSimpanFavorit();
            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareintent = new Intent(Intent.ACTION_SEND);
                shareintent.setType("text/plain");
                shareintent.putExtra(Intent.EXTRA_SUBJECT, mNamaBarang);
                shareintent.putExtra(Intent.EXTRA_TEXT, mJenis);
                startActivity(Intent.createChooser(shareintent, "Bagikan dengan"));
            }
        });
    }

    private void requestSimpanFavorit(){
        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
        mApiService.simpanFavorite(mNamaBarang,mJenis,mJumlah,mKondisi)
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

    private void requestDeleteBarang(){
        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);

        mApiService.deteleBarang(mId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    Toast.makeText(mContext, "Berhasil mengapus barang", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(mContext, BarangActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                } else {
                    loading.dismiss();
                    Toast.makeText(mContext, "Gagal menghapus barang", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public int getColor() {
        String color;

        // Randomly select a fact
        Random randomGenerator = new Random(); // Construct a new Random number generator
        int randomNumber = randomGenerator.nextInt(mColors.length);

        color = mColors[randomNumber];
        int colorAsInt = Color.parseColor(color);

        return colorAsInt;
    }
}
