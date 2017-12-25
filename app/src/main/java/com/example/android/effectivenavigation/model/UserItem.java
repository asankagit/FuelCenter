package com.example.android.effectivenavigation.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by asanka on 11/22/17.
 */

@Table(name = "UserItem")
public class UserItem {

    @Column(name = "User")
    public User user;

    @Column(name = "Item")
    public Item item;
}
