package com.example.android.effectivenavigation.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by asanka on 11/22/17.
 */

@Table(name = "UserItem")
public class UserItem  extends Model {

    @Column(name = "foo", onDelete= Column.ForeignKeyAction.CASCADE)
    public Item item;
    @Column(name = "bar", onDelete= Column.ForeignKeyAction.CASCADE)
    public User user;

    public List<Item> item_list() {
        return getMany(Item.class, "UserItem");
    }
    public List<User> user_list() {
        return getMany(User.class, "UserItem");
    }
}
