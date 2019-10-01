package com.frikhi.test.realtime.message;

import android.app.Application;

import com.frikhi.test.realtime.message.di.component.DaggerAppComponent;
import com.frikhi.test.realtime.message.di.component.AppComponent;
import com.frikhi.test.realtime.message.di.module.AppModule;
import com.google.firebase.FirebaseApp;


public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
