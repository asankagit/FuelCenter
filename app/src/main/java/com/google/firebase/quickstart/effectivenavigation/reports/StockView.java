package com.google.firebase.quickstart.effectivenavigation.reports;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.effectivenavigation.R;
import com.github.anastr.speedviewlib.SpeedView;

public class StockView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_view);

        SpeedView speedView = (SpeedView) findViewById(R.id.speedView);

        // change MAX speed to 320
        speedView.setMaxSpeed(320);

        speedView.setTickRotation(false);


        speedView.speedTo(50.00000f,0);

    }
}
