package com.example.isumatsumi.barangapp.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isumatsumi.barangapp.R;
import com.example.isumatsumi.barangapp.adapter.BarangAdapter;
import com.example.isumatsumi.barangapp.model.ResponseBarang;
import com.example.isumatsumi.barangapp.model.SemuabarangItem;
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

public class BarangActivity extends AppCompatActivity {

    @BindView(R.id.btnTambahBarang)
    Button btnTambahBarang;
    @BindView(R.id.tvBelumBarang)
    TextView tvBelumBarang;
    @BindView(R.id.rvBarang)
    RecyclerView rvBarang;
    ProgressDialog loading;

    Context mContext;
    List<SemuabarangItem> semuabarangItemList = new ArrayList<>();
    BarangAdapter barangAdapter;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang);

        getSupportActionBar().setTitle("Daftar Barang");

        ButterKnife.bind(this);
        mApiService = UtilsApi.getAPIService();
        mContext = this;

        barangAdapter = new BarangAdapter(this, semuabarangItemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvBarang.setLayoutManager(mLayoutManager);
        rvBarang.setItemAnimator(new DefaultItemAnimator());

        getDataBarang();

        btnTambahBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BarangActivity.this, TambahBarangActivity.class));
            }
        });
    }

    private void getDataBarang(){
        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);

        mApiService.getSemuaBarang().enqueue(new Callback<ResponseBarang>() {
            @Override
            public void onResponse(Call<ResponseBarang> call, Response<ResponseBarang> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    if (response.body().isError()) {
                        tvBelumBarang.setVisibility(View.VISIBLE);
                    } else {
                        final List<SemuabarangItem> semuabarangItems = response.body().getSemuabarang();
                        rvBarang.setAdapter(new BarangAdapter(mContext, semuabarangItems));
                        barangAdapter.notifyDataSetChanged();

                        initDataIntent(semuabarangItems);
                    }
                } else {
                    loading.dismiss();
                    Toast.makeText(mContext, "Gagal mengambil data mata kuliah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBarang> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initDataIntent(final List<SemuabarangItem> barangList){
        rvBarang.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        String id = barangList.get(position).getId();
                        String namabarang = barangList.get(position).getNamaBarang();
                        String jenis = barangList.get(position).getJenisBarang();
                        String jumlah = barangList.get(position).getJumlah();
                        String kondisi = barangList.get(position).getKondisi();

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
