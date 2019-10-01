package com.frikhi.test.realtime.message.di.component;

import android.app.Application;

import com.frikhi.test.realtime.message.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Application application();
}
