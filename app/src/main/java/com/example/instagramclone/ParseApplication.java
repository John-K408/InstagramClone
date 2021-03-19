package com.example.instagramclone;

import android.app.Application;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class ParseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("zfB4MXiy1vSsV3baTgc5ZMUw65t7K6PvCwxh8p7p")
                .clientKey("yRyU0r7IfvmEiQ0ivEXQv1FHVU4PsIhEYOVSjMWN")
                .server("https://parseapi.back4app.com")
                .build()
        );


    }


}
