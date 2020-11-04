package com.devgrafis.www.grafis.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devgrafis.www.grafis.R;
import com.devgrafis.www.grafis.model.Message;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private ArrayList<Message> mMessage;
    private Context mContext;
    public MessageAdapter(Context mContext, ArrayList<Message> mMessage){
        this.mContext = mContext;
        this.mMessage = mMessage;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.list_message, viewGroup, false);
        /*RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rootView.setLayoutParams(lp);*/
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //Message mCurrentMessage = mMessage.get(i);
        String tranmitter = mMessage.get(i).getTransmitter();
        String contentMessage = mMessage.get(i).getContenMessage();
        viewHolder.tranmitter.setText(tranmitter);
        viewHolder.contentMessage.setText(contentMessage);
    }

    @Override
    public int getItemCount() {
        return mMessage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tranmitter, contentMessage;
        public ViewHolder(View itemView) {
            super(itemView);
            tranmitter = itemView.findViewById(R.id.transmitter);
            contentMessage = itemView.findViewById(R.id.contentMessage);
        }
    }
}
