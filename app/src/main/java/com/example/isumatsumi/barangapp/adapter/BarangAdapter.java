package com.example.isumatsumi.barangapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.isumatsumi.barangapp.R;
import com.example.isumatsumi.barangapp.model.SemuabarangItem;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.BarangHolder> {

    Context mContext;
    List<SemuabarangItem> semuabarangItemList;

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

    public BarangAdapter(Context context, List<SemuabarangItem> barangList) {
        this.mContext = context;
        semuabarangItemList = barangList;
    }

    @Override
    public BarangHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);
        return new BarangHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BarangHolder holder, int position) {
        final SemuabarangItem semuabarangItem = semuabarangItemList.get(position);
        holder.tvNamaBarang.setText(semuabarangItem.getNamaBarang());
        holder.tvNamaJenis.setText(semuabarangItem.getJenisBarang());

        String namaBarang = semuabarangItem.getNamaBarang();
        String firstCharNamaBarang = namaBarang.substring(0,1);
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(firstCharNamaBarang, getColor());
        holder.ivTextDrawable.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return semuabarangItemList.size();
    }

    public class BarangHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.ivTextDrawable)
        ImageView ivTextDrawable;
        @BindView(R.id.tvNamaBarang)
        TextView tvNamaBarang;
        @BindView(R.id.tvJenis)
        TextView tvNamaJenis;

        public BarangHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
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
