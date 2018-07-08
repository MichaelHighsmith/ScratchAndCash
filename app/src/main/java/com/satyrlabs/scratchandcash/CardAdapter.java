package com.satyrlabs.scratchandcash;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private Context context;
    private List<String> cards;

    public CardAdapter(Context context, List<String> cards) {
        this.context = context;
        this.cards = cards;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_in_list, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        String currentString = cards.get(position);
        holder.cardText.setText(currentString);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder {

        TextView cardText;

        public CardViewHolder(View itemView) {
            super(itemView);
            cardText = itemView.findViewById(R.id.card_text);
        }
    }
}
