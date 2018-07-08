package com.satyrlabs.scratchandcash;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScratchCardListFragment extends Fragment {

    @BindView(R.id.scratch_cards_recycler_view)
    RecyclerView cardRecyclerView;

    List<String> cards = new ArrayList<>();
    CardAdapter cardAdapter;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.scratch_card_list, container, false);
        ButterKnife.bind(this, view);

        cards.add("Prize: $100");
        cards.add("Prize: $50");
        cards.add("Prize: $120");
        cards.add("Prize: $10");
        cards.add("Prize: $1");
        cards.add("Prize: $1");
        cards.add("Prize: $100");
        cards.add("Prize: $100");
        cards.add("Prize: $100");

        cardRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        cardAdapter = new CardAdapter(this.getContext(), cards);
        cardRecyclerView.setAdapter(cardAdapter);

        return view;
    }




}
