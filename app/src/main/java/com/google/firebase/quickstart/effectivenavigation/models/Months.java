package com.google.firebase.quickstart.effectivenavigation.models;

/**
 * Created by DELL on 4/2/2018.
 */

public class Months {
    private static  String[] month = {
          "january",
          "february",
          "march",
          "april",
          "may",
          "june",
          "july",
          "auguest",
          "september",
          "october",
          "november",
          "december"
    };

    public static String getString(int monthId){
        return month[monthId];
    }
}
