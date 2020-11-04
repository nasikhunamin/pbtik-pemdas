package com.devgrafis.www.grafis;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devgrafis.www.grafis.adapter.GroupAdapter;
import com.devgrafis.www.grafis.adapter.MessageAdapter;
import com.devgrafis.www.grafis.model.Message;
import com.devgrafis.www.grafis.touchlistener.RecyclerTouchListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<com.devgrafis.www.grafis.model.Message> message;
    private MessageAdapter messageAdapter;
    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_message, container, false);
        recyclerView = v.findViewById(R.id.recyclerMessage);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        messageAdapter = new MessageAdapter(getContext(), message);
        recyclerView.setAdapter(messageAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(),recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        message = new ArrayList<>();
        String transmitter[] = {"Yoga", "Habibi"};
        String contentMessage[] = {"Kamu bisa mengoperasikan CorelDraw ?", "Besok bisa ketemu kah ?"};
        for (int i=0;i<transmitter.length;i++){
            message.add(new com.devgrafis.www.grafis.model.Message(transmitter[i], contentMessage[i]));
        }
    }
}
