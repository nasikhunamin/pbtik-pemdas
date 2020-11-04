package com.devgrafis.www.grafis.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.devgrafis.www.grafis.R;
import com.devgrafis.www.grafis.model.Quiz;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Quiz> mQuiz;
    public QuizAdapter(Context mContext, ArrayList<Quiz> mQuiz){
        this.mContext = mContext;
        this.mQuiz = mQuiz;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new QuizAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_quiz, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String titleQuiz, subtitleQuiz;
        titleQuiz = mQuiz.get(i).getTitleQuiz();
        subtitleQuiz = mQuiz.get(i).getSubtitleQuiz();

        viewHolder.titleQuiz.setText(titleQuiz);
        viewHolder.subtitleQuiz.setText(subtitleQuiz);
    }

    @Override
    public int getItemCount() {
        return mQuiz.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleQuiz, subtitleQuiz;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleQuiz = itemView.findViewById(R.id.titleQuiz);
            subtitleQuiz = itemView.findViewById(R.id.subtitleQuiz);
        }
    }
}
