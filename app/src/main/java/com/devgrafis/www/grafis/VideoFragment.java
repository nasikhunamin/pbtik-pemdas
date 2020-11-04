package com.devgrafis.www.grafis;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devgrafis.www.grafis.adapter.VideoAdapter;
import com.devgrafis.www.grafis.model.Video;
import com.devgrafis.www.grafis.touchlistener.RecyclerTouchListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {
private RecyclerView recyclerView;
private ArrayList<Video> mVideo;
private VideoAdapter videoAdapter;

    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_video, container, false);
        final String VIDEO_URL[] = getResources().getStringArray(R.array.VideoURL)  ;
        final String VIDEO_DESCRIPTION[] = getResources().getStringArray(R.array.VideoDescription);
        recyclerView = v.findViewById(R.id.recyclerVideo);
        videoAdapter = new VideoAdapter(getContext(), mVideo);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent youtubeActivity = new Intent(getContext(), YoutubeActivity.class);
                youtubeActivity.putExtra("VIDEO_URL", VIDEO_URL[position]);
                youtubeActivity.putExtra("VIDEO_DESCRIPTION", VIDEO_DESCRIPTION[position]);
                startActivity(youtubeActivity);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        recyclerView.setAdapter(videoAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVideo = new ArrayList<>();
        String title = "Video";
        String subtitle[] = getResources().getStringArray(R.array.VideoSubtitle);
        for (int i=0;i<subtitle.length;i++){
            mVideo.add(new Video(title + " " + (i+1), subtitle[i]));
        }
    }
}