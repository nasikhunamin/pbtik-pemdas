package com.devgrafis.www.grafis.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.devgrafis.www.grafis.R;
import com.devgrafis.www.grafis.model.Group;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Group> mGroup;
    public GroupAdapter(Context mContext, ArrayList<Group> mGroup){
        this.mContext = mContext;
        this.mGroup = mGroup;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_group, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Group mCurrent = mGroup.get(i);
        String nameGroup = mGroup.get(i).getNameGroup();
        viewHolder.name.setText(nameGroup);
        //Glide.with(mContext).load(mCurrent.getImg()).into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return mGroup.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //private ImageView img;
        private TextView name;
        public ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.groupName);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, "UHU " + name.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
