package com.example.instagramapp;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    //Initialize Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("nz8V6TFeiaBIhD5loEGaMtP6zPC2fa92Dt5DxxLh")
                .clientKey("rtlr1rzKzqdaYP76mLI5lm47oyga8tBD9zs86pn2")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
