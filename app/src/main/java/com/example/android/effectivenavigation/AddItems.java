package com.example.android.effectivenavigation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.android.effectivenavigation.adapter.CustomAdapter;
import com.example.android.effectivenavigation.adapter.MyAdapter;
import com.example.android.effectivenavigation.model.DataModel;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;
//import com.google.firebase.auth.FirebaseAuth;


/**
 * Created by asanka on 11/23/17.
 */

public class AddItems extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String read_db;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.additem_act, container, false);

// ...
        mAuth = FirebaseAuth.getInstance();
//        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.item_rv);
         mRecyclerView = (RecyclerView) rootView.findViewById(R.id.item_rv);


        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);


        //----------------------------------------send data to firebase
        //set admin previlages
        // Fetch the service account key JSON file contents
        FileInputStream serviceAccount = null;
        try {
            serviceAccount = new FileInputStream("config/fuelcenter-7331d-firebase-adminsdk-bw6ra-07f5954b68.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Initialize the app with a service account, granting admin privileges

        FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://fuelcenter-7331d.firebaseio.com")
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FirebaseApp.initializeApp(options);
        ///////////////////////////


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");


        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                read_db = value;
//                Toast.makeText(, "d", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
//        //----------------------end send to firebase
        // specify an adapter (see also next example)

        String[] myDataset ={"s","sdf"};
        mAdapter = new MyAdapter(myDataset);
        //mAdapter = new CustomAdapter(myDataset);


        /////////implement card view
        ArrayList<DataModel> cardlist = new ArrayList<>();
        DataModel cardelement = new DataModel("cardview test", read_db ,1,1);
        cardlist.add(cardelement);
        mAdapter =  new CustomAdapter(cardlist);
        mRecyclerView.setAdapter(mAdapter);



        return  rootView;




    }

}
