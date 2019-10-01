package com.frikhi.test.realtime.message.ui;


import android.content.Context;

import com.frikhi.test.realtime.message.model.Message;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class MessageContract {

    interface View {
        void onAlertShow(String alert);
        void onGetData(List<Message> messageList);
    }

    interface Presenter {
        void getMessages();
        void sendMessage(Message Message);
        void clearMessages();
    }
}
