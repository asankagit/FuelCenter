package com.google.firebase.quickstart.effectivenavigation.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL on 4/3/2018.
 */
@IgnoreExtraProperties
public class Stock {

    public String itemName;
    public String amount;
    public String  percentage;
    public String imageId;

    public Stock(){}

    public Stock(String itemName , String amount , String percentage, String imageId){
        this.itemName = itemName;
        this.amount = amount;
        this.percentage  = percentage;
        this.imageId = imageId;
    }


    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("itemName", itemName);
        result.put("amount", amount);
        result.put("percentage", percentage);
        result.put("imageId", imageId);

        return result;
    }
    // [END post_to_map]
}
