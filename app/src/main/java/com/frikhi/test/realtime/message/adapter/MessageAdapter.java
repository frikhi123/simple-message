package com.frikhi.test.realtime.message.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frikhi.test.realtime.message.R;
import com.frikhi.test.realtime.message.model.Message;

import java.util.Collections;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private Context context;
    private List<Message> messageList;
    private recycleItemClickListener listener;

    public MessageAdapter(Context context, List<Message> messageList, recycleItemClickListener listener){
        this.context = context;
        this.messageList= messageList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View groceryProductView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message_item, viewGroup, false);
        final ViewHolder mViewHolder = new ViewHolder(groceryProductView);
        groceryProductView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getPosition());
                notifyDataSetChanged();

            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        if(messageList != null && messageList.size() > 0){
            viewHolder.message.setText(messageList.get(position).getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView message;
        public ViewHolder(View view) {
            super(view);
            message =view.findViewById(R.id.message_text);
        }

    }
}
