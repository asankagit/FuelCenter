package com.google.firebase.quickstart.effectivenavigation.reports;

import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import com.example.android.effectivenavigation.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.quickstart.effectivenavigation.dbConn.DBConn;
import com.google.firebase.quickstart.effectivenavigation.models.Income;
import com.google.firebase.quickstart.effectivenavigation.models.Post;
import com.google.firebase.quickstart.effectivenavigation.viewholder.PageViewAdapter;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;

public class DailyIncome extends AppCompatActivity {
    public ArrayList<Income>  incomeArrList = new ArrayList<Income>();
    DatePicker datePicker;

    PageViewAdapter mAdapter;
    ViewPager mPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_income);
        datePicker = (DatePicker)  findViewById(R.id.datePicker);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Log.d("Date", "Year=" + year + " Month=" + (month + 1) + " day=" + dayOfMonth);

            }
        });

        /**pageview **/
        mAdapter = new PageViewAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.viewPager);
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(0);
        /**page view**/

//        drawGraph();
        dailyIncome();

    }

    private void drawGraph(DataPoint[] dataPoint) {

/***************graph***/
        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(
                dataPoint
        );

        series.setTitle("Random Curve 1");
//        series.setColor(254);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(5);
        series.setThickness(3);
        graph.addSeries(series);


        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return super.formatLabel(value, isValueX);
                } else {
                    // show currency for y values
                    return super.formatLabel(value, isValueX) + " Rs";
                }
            }
        });
/**************** end graph*/
    }



    static int count = 0 ;
    Income  dailyIncome;

    ArrayList<DataPoint> dp = new ArrayList<>();

    public void dailyIncome() {

        DatabaseReference myRef = DBConn.getDBref().child("posts").child("-L3MBaCEvzmEolnpOGkS");
//        myRef.child("posts");


        /****/
        DatabaseReference scoresRef = DBConn.getDatabase().getReference().child("income").child("2018").child("january");
//        DatabaseReference scoresRef = DBConn.getDatabase().getReference().child("income");
        scoresRef.orderByChild("name").equalTo("asa").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {

                System.out.println("The OnChildAdd " + snapshot.getKey() + " dinosaur's score is " + snapshot.getValue());
                dailyIncome = snapshot.getValue(Income.class);
                incomeArrList.add(dailyIncome);
                dp.add(new DataPoint(dailyIncome.getDay(),dailyIncome.amount));


                DataPoint[] dataPoint = new DataPoint[dp.size()];
                dataPoint = dp.toArray(dataPoint);
                drawGraph(dataPoint);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                System.out.println("The onChildRemove" +dataSnapshot.getKey() + " dinosaur's score is " + dataSnapshot.getValue());

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                System.out.println("The onChildMove" + dataSnapshot.getKey() + " dinosaur's score is " + s);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

}
