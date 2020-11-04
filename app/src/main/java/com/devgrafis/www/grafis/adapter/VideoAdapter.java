package com.devgrafis.www.grafis.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devgrafis.www.grafis.R;
import com.devgrafis.www.grafis.model.Video;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    Context mContext;
    ArrayList<Video> mVideo;
    public VideoAdapter(Context mContext, ArrayList<Video> mVideo){
        this.mContext = mContext;
        this.mVideo = mVideo;
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.list_video, viewGroup, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder viewHolder, int i) {
        String title, subtitle;
        title = mVideo.get(i).getTitleVideo();
        subtitle = mVideo.get(i).getSubtitleVideo();

        viewHolder.titleVideo.setText(title);
        viewHolder.subtitleVideo.setText(subtitle);
    }

    @Override
    public int getItemCount() {
        return mVideo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleVideo, subtitleVideo;
        public ViewHolder(View itemView) {
            super(itemView);
            titleVideo = itemView.findViewById(R.id.titleVideo);
            subtitleVideo = itemView.findViewById(R.id.subtitleVideo);
        }
    }
}
