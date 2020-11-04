package com.devgrafis.www.grafis.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.devgrafis.www.grafis.DetailMaterialActivity;
import com.devgrafis.www.grafis.R;
import com.devgrafis.www.grafis.model.Material;

import java.util.ArrayList;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.ViewHolder> {
    private ArrayList<Material> mMaterial;
    private Context mContext;

    public MaterialAdapter(Context context, ArrayList<Material> mMaterialData){
        this.mContext = context;
        this.mMaterial = mMaterialData;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleText;
        private TextView mSubtitleText;
        private ImageView mImgSource;
        ViewHolder(View itemView){
            super(itemView);
            mTitleText = itemView.findViewById(R.id.materialTitle);
            mSubtitleText = itemView.findViewById(R.id.materialSubTitle);
            mImgSource = itemView.findViewById(R.id.materialImage);

            itemView.setOnClickListener(this);
        }

        void bindTo(Material currentMaterial){
            mTitleText.setText(currentMaterial.getMaterialTitle());
            mSubtitleText.setText(currentMaterial.getMaterialSubtitle());
        }

        @Override
        public void onClick(View v) {
            Material mCureentMaterial = mMaterial.get(getAdapterPosition());
            Intent detailInten = new Intent(mContext, DetailMaterialActivity.class);
            /*detailInten.putExtra("title", mCureentMaterial.getMaterialTitle());
            detailInten.putExtra("subtitle", mCureentMaterial.getMaterialSubtitle());*/

            detailInten.putExtra("imgSource", mCureentMaterial.getImgSource());
            detailInten.putExtra("content", mCureentMaterial.getMaterialContent());
            mContext.startActivity(detailInten);
        }
    }

    @NonNull
    @Override
    public MaterialAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_material, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialAdapter.ViewHolder viewHolder, int i) {
        Material mCurrentMaterial = mMaterial.get(i);
        Glide.with(mContext).load(mCurrentMaterial.getImgSource()).into(viewHolder.mImgSource);
        viewHolder.bindTo(mCurrentMaterial);
    }

    @Override
    public int getItemCount() {
     return mMaterial.size();
    }


}
