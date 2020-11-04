package com.devgrafis.www.grafis;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.devgrafis.www.grafis.adapter.QuizAdapter;
import com.devgrafis.www.grafis.model.Quiz;
import com.devgrafis.www.grafis.touchlistener.RecyclerTouchListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Quiz> mQuiz;
    private QuizAdapter quizAdapter;
    private final String titleQuiz[] = {"Tugas 1", "Tugas 2", "Tugas 3"};
    private final String subtitleQuiz[] = {"Bitmap dan Vektor", "Konsep Warna", "Format Gambar"};
    private Dialog dialogResult;

    public QuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quiz, container, false);
        recyclerView = v.findViewById(R.id.recyclerQuiz);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        quizAdapter = new QuizAdapter(getContext(), mQuiz);
        recyclerView.setAdapter(quizAdapter);
        dialogResult = new Dialog(getContext());
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent quizActivity = new Intent(getContext(), QuizActivity.class);
                switch (position){
                    case 0:
                        quizActivity.putExtra("title",titleQuiz[position]);
                        quizActivity.putExtra("subtitle",subtitleQuiz[position]);
                        quizActivity.putExtra("id_soal", position+1);
                        startActivity(quizActivity);
                        break;
                    case 1:
                        showDialog();
                        break;
                    case 2:
                        showDialog();
                        break;
                }
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
        mQuiz = new ArrayList<>();
        for(int i=0;i<titleQuiz.length;i++){
            mQuiz.add(new Quiz(titleQuiz[i], subtitleQuiz[i]));
        }
    }

    public void showDialog(){
        dialogResult.setContentView(R.layout.dialog_info);
        ImageView closeDialog = dialogResult.findViewById(R.id.closeDialog);

        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogResult.dismiss();
            }
        });
        dialogResult.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogResult.show();
    }
}
