package com.google.firebase.quickstart.effectivenavigation.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by DELL on 3/16/2018.
 */

public class InvoicePending extends PostListFragment {

    public InvoicePending() {}

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // [START recent_posts_query]
        // Last 100 posts, these are automatically the 100 most recent
        // due to sorting by push() keys
        Query recentPostsQuery = databaseReference.child("posts").orderByChild("approved").equalTo(false)
                .limitToFirst(100);
        // [END recent_posts_query]

        return recentPostsQuery;
    }
}