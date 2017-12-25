package com.example.android.effectivenavigation.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by asanka on 11/22/17.
 */
@Table(name = "Item")
public class Item  extends Model {

    @Column(name = "Name")
    public String item_name;

    @Column(name = "Type")
    public String type;

    @Column(name = "Count")
    public int count;

    @Column(name = "Weight")
    public double weight;

    @Column(name = "Status")
    public  String  status;

    @Column(name = "User")
    public  User user;


    public Item() {
        super();
    }

    public Item(String name,String type,int count,double weight,String status,User user ) {
        super();
        this.item_name = name;
        this.type = type;
        this.count = count;
        this.weight = weight;
        this.status = status;
        this.user = user;
    }




}
