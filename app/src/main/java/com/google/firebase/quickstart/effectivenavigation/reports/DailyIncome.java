package com.google.firebase.quickstart.effectivenavigation.reports;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class DailyIncome extends AppCompatActivity {
    public ArrayList<Income>  incomeArrList = new ArrayList<Income>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_income);
//        drawGraph();
        dailyIncome();

    }

    private void drawGraph(DataPoint[] dataPoint) {

/***************graph***/
        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(
                dataPoint
//                new DataPoint[] {
//                        new DataPoint(0, 10000),
//                        new DataPoint(3, 20000),
//                        new DataPoint(14, 60000),
//                        new DataPoint(1, 50000),
//                        new DataPoint(22, 30000)
//                }
        );

        series.setTitle("Random Curve 1");
//        series.setColor(254);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        series.setThickness(8);
        graph.addSeries(series);

        /**
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
        });**/
/**************** end graph*/
    }

    static int count = 0 ;
    static Income  dailyIncome;

    ArrayList<DataPoint> dp = new ArrayList<>();

    public void dailyIncome() {

        DatabaseReference myRef = DBConn.getDBref().child("posts").child("-L3MBaCEvzmEolnpOGkS");
//        myRef.child("posts");


        /****/
        DatabaseReference scoresRef = DBConn.getDatabase().getReference().child("income");
        scoresRef.orderByChild("name").equalTo("asa").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {

                System.out.println("The OnChildAdd " + snapshot.getKey() + " dinosaur's score is " + snapshot.getValue());
                dailyIncome = snapshot.getValue(Income.class);


//                System.out.println("DAILY_INCOME__"+snapshot.child("name").getValue());
//              System.out.println("DAILY_INCOME_NAME_"+dailyIncome.name);

                incomeArrList.add(dailyIncome);
                dp.add(new DataPoint(dailyIncome.getDay(),dailyIncome.amount));

//              printData();
                System.out.println("count"+count);
                count++;



//                new DataPoint[] {
//                        new DataPoint(0, 1),
//                        new DataPoint(1, 5),
//                        new DataPoint(2, 3),
//                        new DataPoint(3, 2),
//                        new DataPoint(4, 6)
//                }
//                dp[0] = new DataPoint(0,0);

//               for(int i =0; i<incomeArrList.size();i++){
//
//                   dp.add(new DataPoint(incomeArrList.get(i).getDay(),incomeArrList.get(i).amount));
//                   dp.add(new DataPoint(3,0));
//                   System.out.println(incomeArrList.size()+"DATE__" +incomeArrList.get(i).getDay()+"*"+incomeArrList.get(i).amount);
//
//                }

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
        /*****/
        /**********************************************/
        Query postsQuery = DBConn.getDatabase().getReference().child("income").orderByChild("name").equalTo("asa");
        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Post>()
                .setQuery(postsQuery, Post.class)
                .build();
        System.out.println("OPTIONS>>>"+options);
        /*********************************************/

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Post post = snapshot.getValue(Post.class);
                System.out.println("<><><><"+post.body);
                System.out.println("<><><><"+post.title);
                System.out.println("<><><><"+post.author);
                if (post.author!=null) {
                    System.out.println("connected");
                } else {
                    System.out.println("not connected");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.err.println("Listener was cancelled");
            }
        });
        System.out.println(">>>>>>>>>>>>>>>>>>>>"+myRef.child("post"));


    }

    public void printData(){
        while(!incomeArrList.isEmpty()){
            String tmp_date =  incomeArrList.get(0).date;
            Double tmp_amount =  incomeArrList.get(0).amount;
            System.out.println("DATE_"+tmp_date+"AMOUNT_"+tmp_amount);
        }

    }
}
