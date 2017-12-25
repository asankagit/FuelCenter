package com.example.android.effectivenavigation.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by asanka on 11/9/17.
 */


@Table(name = "Users")
public class User extends Model {

    // If name is omitted, then the field name is used.
//    @Column(name = "Id")
//    public String myid;

    @Column(name = "Name")
    public String name;

    @Column(name = "Item")
    public  Item item;



//    @Column(name = "Category")
//    public Category category;
//
    public User() {
        super();
        this.name = "dynamic name form user const";
    }
//
    public User(String name) {
        super();
        this.name = name;

    }
}
