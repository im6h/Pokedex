package com.vu.pokedex;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vu.pokedex.Adapter.PokemonEvolAdapter;
import com.vu.pokedex.Adapter.PokemonTypeAdapter;
import com.vu.pokedex.Common.Common;
import com.vu.pokedex.Model.Pokemon;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonDetail extends Fragment {

    static PokemonDetail instance;

    ImageView pkm_image;
    TextView name,height,weight;
    RecyclerView rcv_type,rcv_weaknesses, rcv_prev_evolution, rcv_next_evolution;

    public static PokemonDetail getInstance() {
        if (instance == null){
            instance = new PokemonDetail();
        }
        return instance;
    }

    public PokemonDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pokemon_detail, container, false);
        Pokemon pokemon;

        name = (TextView) view.findViewById(R.id.name);
        height = (TextView) view.findViewById(R.id.height);
        weight = (TextView) view.findViewById(R.id.weight);
        pkm_image = (ImageView) view.findViewById(R.id.image);

        rcv_type = (RecyclerView) view.findViewById(R.id.rcv_type);
        rcv_type.setHasFixedSize(true);
        rcv_type.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rcv_weaknesses = (RecyclerView) view.findViewById(R.id.rcv_weaknesses);
        rcv_weaknesses.setHasFixedSize(true);
        rcv_weaknesses.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        rcv_prev_evolution = (RecyclerView) view.findViewById(R.id.rcv_prev_Evolution);
        rcv_prev_evolution.setHasFixedSize(true);
        rcv_prev_evolution.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        rcv_next_evolution = (RecyclerView) view.findViewById(R.id.rcv_next_Evolution);
        rcv_next_evolution.setHasFixedSize(true);
        rcv_next_evolution.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        if (getArguments().get("num") == null){
            pokemon = Common.mListPokemon.get(getArguments().getInt("Position"));
        }
        else {
            pokemon = Common.findPokemonByNum(getArguments().getString("num"));
        }
        detailPokemon(pokemon);
        PokemonTypeAdapter typeAdapter = new PokemonTypeAdapter(getActivity(),pokemon.getType());
        rcv_type.setAdapter(typeAdapter);

        PokemonTypeAdapter weakness = new PokemonTypeAdapter(getActivity(),pokemon.getWeaknesses());
        rcv_weaknesses.setAdapter(weakness);

        PokemonEvolAdapter preEvolAdapter = new PokemonEvolAdapter(getActivity(),pokemon.getPrev_evolution());
        rcv_prev_evolution.setAdapter(preEvolAdapter);
        PokemonEvolAdapter nextEvolAdapter = new PokemonEvolAdapter(getActivity(),pokemon.getNext_evolution());
        rcv_next_evolution.setAdapter(nextEvolAdapter);

        return view;
    }

    private void detailPokemon(Pokemon pokemon) {
        // load image
        Picasso.get().load(pokemon.getImg()).into(pkm_image);
        name.setText(pokemon.getName());
        height.setText("Height: " + pokemon.getHeight());
        weight.setText("Weight: "+pokemon.getWeight());
    }

}
