package com.google.firebase.quickstart.effectivenavigation.models;

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
    public Integer age;
    public String name;
    public String rel;
    public int starCount = 0;
//    public Map<String, Boolean> stars = new HashMap<>();

    public Income() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Income( Integer  age, String name, String rel) {
//        this.uid = uid;
        this.age = age;
        this.name = name;
        this.rel =rel;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
//        result.put("uid", uid);
        result.put("age", age);
        result.put("name", name);
        result.put("rel", rel);
        result.put("starCount", starCount);
//        result.put("stars", stars);

        return result;
    }
    // [END post_to_map]

}
// [END post_class]
