package com.google.leilaahamdyan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {

    private static final String PREF_NAME = "pref";
    public static        String IS_REVIEW = "is_review";

    private static SharedPreferences pref;
    private static Editor            editor;

    @SuppressLint("CommitPrefEdits")
    public SessionManager(Context context) {
        pref   = context.getSharedPreferences(PREF_NAME, 0);
        editor = pref.edit();
    }

    public static boolean getIsReview() {
        return pref.getBoolean(IS_REVIEW, false);
    }

    public static void setIsReview(boolean isReview) {
        editor.putBoolean(IS_REVIEW, isReview);
        editor.commit();
    }


}
