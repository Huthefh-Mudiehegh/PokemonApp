package com.huthfy.pickemonapp.ui.mainActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.huthfy.pickemonapp.R;
import com.huthfy.pickemonapp.model.Pokemon;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    ArrayList<Pokemon> arrayList;
    private Context context;

    public PokemonAdapter(Context context) {
        this.context = context;
        arrayList = new ArrayList<>();
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PokemonViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_list_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon item = arrayList.get(position);

        //third party library for images
        Glide.with(context).load(item.getUrl()).into(holder.pokemonImage);
        holder.pokemonName.setText(item.getName());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public void setArrayList(ArrayList arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    class PokemonViewHolder extends RecyclerView.ViewHolder {

        ImageView pokemonImage;
        TextView pokemonName;
        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemonImage = itemView.findViewById(R.id.pokemon_img);
            pokemonName = itemView.findViewById(R.id.pokemon_name);

        }
    }
}
