package com.google.firebase.quickstart.effectivenavigation.models;

import android.util.Log;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL on 3/23/2018.
 */


// [START post_class]
@IgnoreExtraProperties
public class Income {

//    public String uid;
    public String age;
    public String name;
    public String rel;
    public Double amount;
    public String date;
    public String datetime;
    public int yaer;
    public int month;
    public int day;
    public String datearr[];
    public int starCount = 0;
//    public Map<String, Boolean> stars = new HashMap<>();

    public Income() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Income(  String name,Double amount, String rel , String  age,String date,String datetime ) {
//        this.uid = uid;
        this.age = age;
        this.name = name;
        this.rel =rel;
        this.amount = amount;
        this.date = date;
        this.datetime = datetime;

    }
    public int getYear(){
        this.datearr = datetime.split("-");
        return Integer.parseInt(datearr[0]);

    }
  public int getMonth(){
        this.datearr = datetime.split("-");
        return Integer.parseInt(datearr[1]);

    }
  public int getDay(){
        this.datearr = datetime.split("-");
        return Integer.parseInt(datearr[2]);

    }


    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
//        result.put("uid", uid);
        result.put("age", age);
        result.put("name", name);
        result.put("rel", rel);
        result.put("date",date);
        result.put("starCount", starCount);
//        result.put("stars", stars);

        return result;
    }
    // [END post_to_map]

}
// [END post_class]
