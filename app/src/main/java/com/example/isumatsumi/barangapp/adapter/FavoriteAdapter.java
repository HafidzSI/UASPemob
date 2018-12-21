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
import com.example.isumatsumi.barangapp.model.SemuafavoritItem;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Isumatsumi on 20/12/2018.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder>{

    Context mContext;
    List<SemuafavoritItem> semuafavoritItemList;

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

    public FavoriteAdapter(Context context, List<SemuafavoritItem> favoritList){
        this.mContext = context;
        semuafavoritItemList = favoritList;
    }

    @Override
    public FavoriteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorit, parent, false);
        return new FavoriteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FavoriteHolder holder, int position) {
        final SemuafavoritItem semuafavoritItem = semuafavoritItemList.get(position);
        holder.tvNamaBarangFavorite.setText(semuafavoritItem.getNamaBarang());
        holder.tvNamaJenisFavorite.setText(semuafavoritItem.getJenisBarang());

        String namaBarangFavorite = semuafavoritItem.getNamaBarang();
        String firstCharNamaBarangFavorit = namaBarangFavorite.substring(0,1);
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(firstCharNamaBarangFavorit, getColor());
        holder.ivTextDrawableFavorite.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return semuafavoritItemList.size();
    }

    public class FavoriteHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.ivTextDrawableFavorite)
        ImageView ivTextDrawableFavorite;
        @BindView(R.id.tvNamaBarangFavorite)
        TextView tvNamaBarangFavorite;
        @BindView(R.id.tvJenisFavorite)
        TextView tvNamaJenisFavorite;

        public FavoriteHolder(View itemView){
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
