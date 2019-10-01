package com.frikhi.test.realtime.message.ui;

import android.content.Context;

import com.frikhi.test.realtime.message.model.Message;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MessagePresenter implements MessageContract.Presenter{

    private MessageContract.View mView;
    private FirebaseDatabase database;
    private DatabaseReference dataRef;

    @Inject
    public MessagePresenter(MessageContract.View mView) {
        this.database = FirebaseDatabase.getInstance();
        this.dataRef = database.getReference("messages");
        this.mView = mView;
    }

    @Override
    public void getMessages() {
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Message> messageList = new ArrayList<>();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Map<String, String> map = (Map<String, String>) ds.getValue();
                    Message message = new Message(map.get("message"));
                    messageList.add(message);
                }
                mView.onGetData(messageList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                mView.onAlertShow("Failed to read value."+ error.toException());
            }
        });
    }

    @Override
    public void sendMessage(Message Message) {
        dataRef.push().setValue(Message, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError != null) {
                    mView.onAlertShow("Data could not be saved. " + databaseError.getMessage());
                } else {
                    mView.onAlertShow("Data saved successfully.");
                }
            }

        });
    }

    @Override
    public void clearMessages() {
        dataRef.removeValue();
    }
}
