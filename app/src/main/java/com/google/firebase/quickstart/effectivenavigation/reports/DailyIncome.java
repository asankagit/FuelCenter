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

public class DailyIncome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_income);

        dailyIncome();

    }

    public void dailyIncome() {

        DatabaseReference myRef = DBConn.getDBref().child("posts").child("-L3MBaCEvzmEolnpOGkS");
//        myRef.child("posts");


        /****/
        DatabaseReference scoresRef = DBConn.getDatabase().getReference().child("income");
        scoresRef.orderByChild("name").equalTo("asa").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                System.out.println("The OnChildAdd " + snapshot.getKey() + " dinosaur's score is " + snapshot.getValue());
//                Income dailyIncome = snapshot.getValue(Income.class);
                System.out.println("DAILY_INCOME__"+snapshot.child("name").getValue());
//                System.out.println("DAILY_INCOME_NAME_"+dailyIncome.name);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                System.out.println("The  onChildChange" + dataSnapshot.getKey() + " dinosaur's score is " + s);
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
}
