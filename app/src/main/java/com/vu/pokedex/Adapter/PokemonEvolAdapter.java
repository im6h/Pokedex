package com.vu.pokedex.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robertlevonyan.views.chip.Chip;
import com.robertlevonyan.views.chip.OnChipClickListener;
import com.robertlevonyan.views.chip.OnSelectClickListener;
import com.vu.pokedex.Common.Common;
import com.vu.pokedex.Model.Evolution;
import com.vu.pokedex.R;

import java.util.ArrayList;
import java.util.List;

public class PokemonEvolAdapter extends RecyclerView.Adapter<PokemonEvolAdapter.ViewHolder> {
    Context context;
    List<Evolution> mEvolution;

    public PokemonEvolAdapter(Context context, List<Evolution> mEvolution) {
        this.context = context;
        if (mEvolution != null)
            this.mEvolution = mEvolution;
        else
            this.mEvolution = new ArrayList();
    }

    @NonNull
    @Override
    public PokemonEvolAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.chip_item,viewGroup,false);


        return new PokemonEvolAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonEvolAdapter.ViewHolder viewHolder, int i) {
        viewHolder.chip.setChipText(mEvolution.get(i).getName());
        viewHolder.chip.changeBackgroundColor(Common.getColorByType(Common.findPokemonByNum(mEvolution.get(i).getNum()).getType().get(0)));
    }

    @Override
    public int getItemCount() {
        return mEvolution.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Chip chip;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chip = (Chip)itemView.findViewById(R.id.chip);
            chip.setOnChipClickListener(new OnChipClickListener() {
                @Override
                public void onChipClick(View v) {
                    LocalBroadcastManager.getInstance(context)
                            .sendBroadcast(new Intent(Common.KEY_NUM_EVOLUTION)
                                    .putExtra("num",mEvolution.get(getAdapterPosition())
                                            .getNum()));
                }
            });
        }
    }
}
