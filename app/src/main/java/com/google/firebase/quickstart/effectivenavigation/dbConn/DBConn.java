package com.google.firebase.quickstart.effectivenavigation.dbConn;

/**
 * Created by DELL on 3/22/2018.
 */
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DBConn {
    private static DatabaseReference mDBReference;

    public static  DatabaseReference getDBref(){

        mDBReference =   FirebaseDatabase.getInstance().getReference();
//        mDBReference.keepSynced(false);
        return mDBReference;
    }

    public static FirebaseDatabase getDatabase(){
        return FirebaseDatabase.getInstance();
    }
}
