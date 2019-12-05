package com.basicmoon.expediaassessment.utils;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.basicmoon.expediaassessment.R;

import static com.google.common.base.Preconditions.checkNotNull;


public class ActivityUtils {

    /**
     * Add a fragment in an activity.
     * @param fragmentManager
     * @param fragment
     * @param frameId
     */
    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    /**
     * Get rating strings according to rating number.
     * @param context
     * @param rating
     * @return
     */
    public static String getRatingStringFromRating(Context context, Double rating) {
        if (rating >= 4.0f) {
            return String.format(context.getString(R.string.str_rating_excellent), rating);
        } else if (rating >= 3.0f) {
            return String.format(context.getString(R.string.str_rating_very_good), rating);
        } else if (rating >= 2.0f) {
            return String.format(context.getString(R.string.str_rating_fair), rating);
        }
        return String.format(context.getString(R.string.str_rating_poor), rating);
    }
}
