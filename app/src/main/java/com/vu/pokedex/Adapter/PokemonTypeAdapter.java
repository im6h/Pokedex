package com.vu.pokedex.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robertlevonyan.views.chip.Chip;
import com.robertlevonyan.views.chip.OnSelectClickListener;
import com.vu.pokedex.Common.Common;
import com.vu.pokedex.Interface.IItemClickListener;
import com.vu.pokedex.R;

import java.util.List;

public class PokemonTypeAdapter extends RecyclerView.Adapter<PokemonTypeAdapter.ViewHolder> {
    Context context;
    List<String> mType;

    public PokemonTypeAdapter(Context context, List<String> mType) {
        this.context = context;
        this.mType = mType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.chip_item, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.chip.setChipText(mType.get(i));
        viewHolder.chip.changeBackgroundColor(Common.getColorByType(mType.get(i)));
    }

    @Override
    public int getItemCount() {
        return mType.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Chip chip;
        IItemClickListener itemClickListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chip = (Chip) itemView.findViewById(R.id.chip);
            chip.setOnSelectClickListener(new OnSelectClickListener() {
                @Override
                public void onSelectClick(View v, boolean selected) {
                    itemClickListener.onClick(v,getAdapterPosition());
            }
            });
        }
    }
}
