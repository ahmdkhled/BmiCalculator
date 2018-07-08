package com.ahmedkhaled.bmicalculator.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmedkhaled.bmicalculator.backend.HistoryDatabase;
import com.ahmedkhaled.bmicalculator.adapters.HistoryRecyclerAdapter;
import com.ahmedkhaled.bmicalculator.R;
import com.ahmedkhaled.bmicalculator.model.history;

import java.util.ArrayList;

/**
 * Created by Ahmed Khaled on 9/25/2017.
 */

public class HistoryFragment extends Fragment {
    RecyclerView historyRecycler;
    ArrayList<history> historyList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        HistoryDatabase historyDatabase = new HistoryDatabase(getActivity());
        View view=inflater.inflate(R.layout.fragment_history,container,false);
        historyRecycler= view.findViewById(R.id.histoyRecycler);
        historyList= historyDatabase.getData();

        HistoryRecyclerAdapter historyRecyclerAdapter=new HistoryRecyclerAdapter(getActivity(),historyList);

        historyRecycler.setAdapter(historyRecyclerAdapter);
        historyRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }
}
