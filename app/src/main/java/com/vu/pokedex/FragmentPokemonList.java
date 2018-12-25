package com.vu.pokedex;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vu.pokedex.Adapter.PokemonAdapter;
import com.vu.pokedex.Common.Common;
import com.vu.pokedex.Common.ItemOffsetDecoration;
import com.vu.pokedex.Model.Pokedex;
import com.vu.pokedex.Model.Pokemon;
import com.vu.pokedex.Retrofit.IPokeDex;
import com.vu.pokedex.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPokemonList extends Fragment {

    static FragmentPokemonList instance;

    RecyclerView recyclerView;
    TextView pkm_sum;

    IPokeDex iPokeDex;
    CompositeDisposable compositeDisposable;

    public static FragmentPokemonList getInstance() {
        if (instance == null) {
            instance = new FragmentPokemonList();
        }
        return instance;
    }

    public FragmentPokemonList() {
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getInstance();
        iPokeDex = retrofit.create(IPokeDex.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.pkm_rcv_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(getActivity(),R.dimen.spacing);
        recyclerView.addItemDecoration(itemOffsetDecoration);
        pkm_sum = (TextView) view.findViewById(R.id.pkm_sum_list);
        fetchData();
        return view;
    }

    private void fetchData() {
        compositeDisposable.add(iPokeDex.getListPokemon()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Pokedex>() {
                    @Override
                    public void accept(Pokedex pokedex) throws Exception {
                        Common.mListPokemon = pokedex.getPokemon();
                        PokemonAdapter adapter = new PokemonAdapter(getActivity(),Common.mListPokemon);
                        recyclerView.setAdapter(adapter);
                        pkm_sum.setText("Gen 1: " + String.valueOf(Common.mListPokemon.size()));
                    }
                }));
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        compositeDisposable.clear();
        super.onDestroyView();
    }
}
