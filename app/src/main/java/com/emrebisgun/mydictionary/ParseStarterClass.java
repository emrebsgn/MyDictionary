package com.emrebisgun.mydictionary;

import android.app.Application;

import com.parse.Parse;

public class ParseStarterClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //set log level örnek
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("u7dl1bKXYLgPtVl09c5Mb4pbh4zZPid1WZ8CBqet")
                .clientKey("picKdDzxLx9zQ2WoLI753LHQ4uciWinKjoWplWFO")
                .server("https://parseapi.back4app.com/")
                .build()



        );
    }
}
