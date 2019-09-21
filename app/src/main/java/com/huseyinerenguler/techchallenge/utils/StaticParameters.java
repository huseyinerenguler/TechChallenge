package com.huseyinerenguler.techchallenge.utils;

import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.huseyinerenguler.techchallenge.models.Order;

import java.util.ArrayList;

public class StaticParameters {

    public static int screenWidth = 0;
    public static String SHARED_PREFERENCES_NAME = "sharedPreferences";
    public static String SHARED_PREFERENCES_KEY_REMEMBER_ME = "rememberMe";

    public static ArrayList<Order> orders = new ArrayList<>();

    public static void init(WindowManager windowManager) {

        StaticParameters.initScreenWidth(windowManager);
    }

    private static void initScreenWidth(WindowManager windowManager) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        int screenWidth = displayMetrics.widthPixels;
        if (screenWidth > 0)
            StaticParameters.screenWidth = screenWidth;
    }

}