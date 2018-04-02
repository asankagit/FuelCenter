package com.google.firebase.quickstart.effectivenavigation.reports;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.android.effectivenavigation.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.quickstart.effectivenavigation.dbConn.DBConn;
import com.google.firebase.quickstart.effectivenavigation.models.Income;
import com.google.firebase.quickstart.effectivenavigation.models.Months;
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
    TextView daily_income_tv;


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
                dailyIncomeGraph(year , Months.getString(month), dayOfMonth);
                setdailyIncomeTextView(year , Months.getString(month) ,dayOfMonth);
                DAILY_AMOUNT = 0 ;


            }
        });

        /**pageview **/
        mAdapter = new PageViewAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.viewPager);
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(0);
        /**page view**/

//        drawGraph();
//        dailyIncomeGraph();

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
    int DAILY_AMOUNT = 0 ;
    ArrayList<DataPoint> dp = new ArrayList<>();

    private void setdailyIncomeTextView(int year, String month , final int day){
        daily_income_tv = (TextView) findViewById(R.id.daily_income_deb_tv);
        daily_income_tv.setText("");

        DatabaseReference scoresRef = DBConn.getDatabase().getReference().child("income").child(""+year).child(month).child(""+day);
//        DatabaseReference scoresRef = DBConn.getDatabase().getReference().child("income");
//        scoresRef.orderByChild("name").equalTo("asa").addChildEventListener(new ChildEventListener() {
        scoresRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
//                dailyIncome = snapshot.getValue(Income.class);

                if(DAILY_AMOUNT == 0){
                    DAILY_AMOUNT = Integer.parseInt( snapshot.getValue().toString());
                }


                daily_income_tv.setText(""+DAILY_AMOUNT);
//                Log.d("Tag",""+dailyIncome.amount);
                System.out.println("GET_KEY"+snapshot.getKey());
                System.out.println("CHILD(AMOUNT)"+snapshot.child("amount"));
                System.out.println("SNAPSHOT.TOSTRING"+snapshot.toString());
                System.out.println("PREVIOUSCHILD"+previousChild);
//                System.out.println(snapshot.getValue(Long.class));
                System.out.println("SNAPSHOT.GETVALUE"+snapshot.getValue());
//                System.out.println(snapshot.exists());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    public void dailyIncomeGraph(int year, String month , int day) {

        DatabaseReference myRef = DBConn.getDBref().child("posts").child("-L3MBaCEvzmEolnpOGkS");
//        myRef.child("posts");


        /****/
        DatabaseReference scoresRef = DBConn.getDatabase().getReference().child("income").child(""+year).child(month);
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
