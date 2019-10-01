package com.frikhi.test.realtime.message.ui;

import com.frikhi.test.realtime.message.di.ActivityScope;

import dagger.Module;
import dagger.Provides;


@Module
public class MessageModule {

    private MessageContract.View mView;


    public MessageModule(MessageContract.View mView) {
        this.mView = mView;
    }

    @ActivityScope
    @Provides
    public MessageContract.View provideContext(){
        return mView;
    }

}


