package com.google.firebase.quickstart.effectivenavigation.reports;

import android.view.View;

import com.example.android.effectivenavigation.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Created by DELL on 3/23/2018.
 */

public abstract class ChartView {

//    GraphView graph = (GraphView) rootView.findViewById(R.id.graph);
    public  void setGraph(GraphView graph) {
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        series.setTitle("Random Curve 1");
//        series.setColor(254);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        series.setThickness(8);
        graph.addSeries(series);
    }
}
