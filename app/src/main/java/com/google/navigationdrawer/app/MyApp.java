package com.google.navigationdrawer.app;

import android.app.Application;

import com.google.navigationdrawer.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/BNazanin.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
