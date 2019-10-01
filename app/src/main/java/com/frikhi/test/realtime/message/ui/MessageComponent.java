package com.frikhi.test.realtime.message.ui;

import com.frikhi.test.realtime.message.di.ActivityScope;
import com.frikhi.test.realtime.message.di.component.AppComponent;

import dagger.Component;


@ActivityScope
@Component(dependencies = AppComponent.class, modules = MessageModule.class)
public interface MessageComponent {

    void inject(MessageActivity activity);

}

