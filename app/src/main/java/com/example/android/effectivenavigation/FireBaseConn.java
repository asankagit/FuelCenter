package com.example.android.effectivenavigation;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.*;

import java.io.FileInputStream;
//import

///**
// * Created by DELL on 1/8/2018.
// */
//
public class FireBaseConn {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public FireBaseConn() {
        mAuth = FirebaseAuth.getInstance();
    }

    public FirebaseAuth.AuthStateListener signIn() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }

            }
        };

        return mAuthListener;
    }

    public String firebaseAuthByToken(){
        FileInputStream serviceAccount = new FileInputStream("path/to/serviceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://fuelcenter-7331d.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
    }
}