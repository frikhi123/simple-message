package com.frikhi.test.realtime.message.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.frikhi.test.realtime.message.App;
import com.frikhi.test.realtime.message.R;
import com.frikhi.test.realtime.message.adapter.MessageAdapter;
import com.frikhi.test.realtime.message.adapter.recycleItemClickListener;
import com.frikhi.test.realtime.message.model.Message;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageActivity extends AppCompatActivity implements MessageContract.View {

    @BindView(R.id.message_recyclerView)
    RecyclerView messageRecyclerView;

    @BindView(R.id.message_text)
    EditText messageText;

    @BindView(R.id.send_button)
    Button sendButton;

    @BindView(R.id.clear_button)
    Button clearButton;

    private MessageAdapter messageAdapter;
    private List<Message> messageList = new ArrayList<>();

    @Inject
    public MessagePresenter messagePresenter;

    private recycleItemClickListener listener = (v, position) -> {

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_main);
        ButterKnife.bind(this);

        DaggerMessageComponent.builder()
                .appComponent(((App) getApplicationContext()).getAppComponent())
                .messageModule(new MessageModule(this))
                .build()
                .inject(this);


        messageAdapter = new MessageAdapter(this, messageList, listener);
        messageRecyclerView.setAdapter(messageAdapter);

        messagePresenter.getMessages();
    }

    @OnClick({R.id.send_button, R.id.clear_button})
    void sendMessage(View view) {
        switch (view.getId()) {
            case R.id.send_button:
                if (!messageText.getText().toString().trim().equals("")) {
                    messagePresenter.sendMessage(new Message(messageText.getText().toString()));
                } else
                    Toast.makeText(this, "write message before sending", Toast.LENGTH_SHORT).show();
                messageText.setText("");
                break;
            case R.id.clear_button:
                messagePresenter.clearMessages();
                messageText.setText("");
                break;
        }
    }


    @Override
    public void onAlertShow(String alert) {
        Toast.makeText(this, alert, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetData(List<Message> messageList) {
        Collections.reverse(messageList);
        this.messageList.clear();
        this.messageList.addAll(messageList);
        this.messageAdapter.notifyDataSetChanged();
    }
}
