package com.example.isumatsumi.barangapp.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isumatsumi.barangapp.R;
import com.example.isumatsumi.barangapp.adapter.BarangAdapter;
import com.example.isumatsumi.barangapp.adapter.FavoriteAdapter;
import com.example.isumatsumi.barangapp.model.ResponseBarang;
import com.example.isumatsumi.barangapp.model.ResponseFavorite;
import com.example.isumatsumi.barangapp.model.SemuabarangItem;
import com.example.isumatsumi.barangapp.model.SemuafavoritItem;
import com.example.isumatsumi.barangapp.util.Constant;
import com.example.isumatsumi.barangapp.util.RecyclerItemClickListener;
import com.example.isumatsumi.barangapp.util.api.BaseApiService;
import com.example.isumatsumi.barangapp.util.api.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Isumatsumi on 19/12/2018.
 */

public class FavoriteActivity extends AppCompatActivity{


    @BindView(R.id.rvBarangFavorite)
    RecyclerView rvBarangFavorite;
    @BindView(R.id.tvBelumBarangFavorite)
    TextView tvBelumBarangFavorite;
    FavoriteAdapter favoriteAdapter;
    ProgressDialog loading;

    Context mContext;
    List<SemuafavoritItem> semuafavoritItemList = new ArrayList<>();
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorit);

        getSupportActionBar().setTitle("Barang Favorit");

        ButterKnife.bind(this);
        mApiService = UtilsApi.getAPIService();
        mContext = this;

        favoriteAdapter = new FavoriteAdapter(this, semuafavoritItemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvBarangFavorite.setLayoutManager(mLayoutManager);
        rvBarangFavorite.setItemAnimator(new DefaultItemAnimator());

        getDataBarangFavorite();
    }

    public void getDataBarangFavorite()
    {
        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);

        mApiService.getFavorite().enqueue(new Callback<ResponseFavorite>() {
            @Override
            public void onResponse(Call<ResponseFavorite> call, Response<ResponseFavorite> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    if (response.body().isError()) {
                        tvBelumBarangFavorite.setVisibility(View.VISIBLE);
                    } else {
                        final List<SemuafavoritItem> semuafavoritItems = response.body().getSemuafavorit();
                        rvBarangFavorite.setAdapter(new FavoriteAdapter(mContext, semuafavoritItems));
                        favoriteAdapter.notifyDataSetChanged();

                        initDataIntent(semuafavoritItems);
                    }
                } else {
                    loading.dismiss();
                    Toast.makeText(mContext, "Gagal mengambil data barang favorit", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseFavorite> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initDataIntent(final List<SemuafavoritItem> barangFavoritList){
        rvBarangFavorite.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        String id = barangFavoritList.get(position).getId();
                        String namabarang = barangFavoritList.get(position).getNamaBarang();
                        String jenis = barangFavoritList.get(position).getJenisBarang();
                        String jumlah = barangFavoritList.get(position).getJumlah();
                        String kondisi = barangFavoritList.get(position).getKondisi();

                        Intent detailBarang = new Intent(mContext, BarangDetailActivity.class);
                        detailBarang.putExtra(Constant.KEY_ID_BARANG, id);
                        detailBarang.putExtra(Constant.KEY_NAMA_BARANG, namabarang);
                        detailBarang.putExtra(Constant.KEY_JENIS, jenis);
                        detailBarang.putExtra(Constant.KEY_JUMLAH, jumlah);
                        detailBarang.putExtra(Constant.KEY_KONDISI, kondisi);
                        startActivity(detailBarang);
                    }
                }));
    }
}
