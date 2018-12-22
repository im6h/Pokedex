package com.vu.pokedex.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vu.pokedex.Model.Pokemon;
import com.vu.pokedex.R;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {
    Context context;
    List<Pokemon> pokemonList;

    public PokemonAdapter(Context context, List<Pokemon> pokemonList) {
        this.context = context;
        this.pokemonList = pokemonList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_pokemon_list,viewGroup,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.pkm_name.setText(pokemonList.get(i).getName());
        Picasso.get().load(pokemonList.get(i).getImg()).into(viewHolder.pkm_image);
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView pkm_name;
        ImageView pkm_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pkm_image = (ImageView) itemView.findViewById(R.id.pkm_image);
            pkm_name = (TextView) itemView.findViewById(R.id.pkm_name);
        }
    }
}
