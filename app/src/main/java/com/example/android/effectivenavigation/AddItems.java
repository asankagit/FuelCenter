package com.example.android.effectivenavigation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;

import com.example.android.effectivenavigation.adapter.MyAdapter;

/**
 * Created by asanka on 11/23/17.
 */

public class AddItems extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.additem_act, container, false);

//        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.item_rv);
         mRecyclerView = (RecyclerView) rootView.findViewById(R.id.item_rv);


        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        String[] myDataset ={"s","sdf"};
        mAdapter = new MyAdapter(myDataset);
        //mAdapter = new CustomAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);




        return  rootView;




    }

}
