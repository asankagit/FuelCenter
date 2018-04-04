package com.google.firebase.quickstart.effectivenavigation.reports;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.effectivenavigation.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.github.anastr.speedviewlib.SpeedView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.quickstart.effectivenavigation.dbConn.DBConn;
import com.google.firebase.quickstart.effectivenavigation.models.Post;
import com.google.firebase.quickstart.effectivenavigation.models.Stock;
import com.google.firebase.quickstart.effectivenavigation.viewholder.PostViewHolder;
import com.google.firebase.quickstart.effectivenavigation.viewholder.StockViewHolder;

public class StockView extends AppCompatActivity {

    private FirebaseRecyclerAdapter<Stock, StockViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_view);

        SpeedView speedView = (SpeedView) findViewById(R.id.speedView);
//        @SuppressLint("ResourceType") View rootView  = (View) findViewById(R.layout.activity_stock_view);

//        mRecycler.setLayoutManager(new LinearLayoutManager(this.));

        mRecycler = (RecyclerView) findViewById(R.id.stock_list);
        // Show most recent items at the top
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        mRecycler.setLayoutManager(layoutManager);

//        mRecycler.setHasFixedSize(true);
        // change MAX speed to 320

        speedView.setMaxSpeed(320);
        speedView.setTickRotation(false);
        speedView.speedTo(50.00000f, 0);
        setStockItems();


    }
    @Override
    protected void onStart() {
        super.onStart();

        // Initialize Firebase listeners in adapter
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Tear down Firebase listeners in adapter
        mAdapter.stopListening();
    }






    private void setStockItems(){
//        DatabaseReference  databaseReference = DBConn.getDBref();
//        Query stocksQuery = databaseReference.child("stock").orderByChild("amount");
        Query stocksQuery = FirebaseDatabase.getInstance().getReference().child("stock");
        System.out.println(stocksQuery);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Stock>()
                .setQuery(stocksQuery, Stock.class)
                .build();
        mAdapter = new FirebaseRecyclerAdapter<Stock, StockViewHolder>(options) {
            @Override
            public StockViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//                return null;
                System.out.println("Inside onCreateViewHolder_"+i);
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                return new StockViewHolder(inflater.inflate(R.layout.item_stock, viewGroup, false));

            }

            @Override
            protected void onBindViewHolder(StockViewHolder stockViewHolder, int i, Stock stock) {
                final DatabaseReference postRef = getRef(i);
                System.out.println(stock.amount+">>>>>>>>>>>>>>>>>>>");
                stockViewHolder.percentage.setText(stock.percentage);
                Log.d("REF_KEy",postRef.getKey());
            }
        };


/*
        mManager = new LinearLayoutManager(StockView.this);
        mManager.setOrientation(LinearLayoutManager.VERTICAL);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);*/

        mRecycler.setAdapter(mAdapter);
    }
}
