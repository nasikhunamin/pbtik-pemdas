package com.devgrafis.www.grafis;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
private TextView titleQuiz, subtitleQuiz;
private Button soal1,soal2, soal3, soal4, soal5;
/*private ArrayList<String> result;
private int skor;
private boolean status;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        //soalQuiz = findViewById(R.id.soalQuiz);
        titleQuiz = findViewById(R.id.titleQuizFirst);
        subtitleQuiz = findViewById(R.id.subtitleQuizFirst);
        soal1 = findViewById(R.id.choice1);
        soal2 = findViewById(R.id.choice2);
        soal3 = findViewById(R.id.choice3);
        soal4 = findViewById(R.id.choice4);
        soal5 = findViewById(R.id.choice5);

        //skor = 0;
        //status = false;
        Intent quiz = getIntent();
        String title = quiz.getStringExtra("title");
        String subtitle = quiz.getStringExtra("subtitle");
        final int soal = quiz.getIntExtra("id_soal", 0);
        //Toast.makeText(this, String.valueOf(soal), Toast.LENGTH_SHORT).show();
        titleQuiz.setText(title);
        subtitleQuiz.setText(subtitle);
        soal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent soalActivity = new Intent(getApplicationContext(), SoalActivity.class);
                soalActivity.putExtra("nomor_soal", 1);
                soalActivity.putExtra("id_soal", soal);
                startActivity(soalActivity);
            }
        });

        soal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent soalActivity = new Intent(getApplicationContext(), SoalActivity.class);
                soalActivity.putExtra("nomor_soal", 2);
                soalActivity.putExtra("id_soal", soal);
                startActivity(soalActivity);
            }
        });

        soal3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent soalActivity = new Intent(getApplicationContext(), SoalActivity.class);
                soalActivity.putExtra("nomor_soal", 3);
                soalActivity.putExtra("id_soal", soal);
                startActivity(soalActivity);
            }
        });

        soal4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent soalActivity = new Intent(getApplicationContext(), SoalActivity.class);
                soalActivity.putExtra("nomor_soal", 4);
                soalActivity.putExtra("id_soal", soal);
                startActivity(soalActivity);
            }
        });

        soal5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent soalActivity = new Intent(getApplicationContext(), SoalActivity.class);
                soalActivity.putExtra("nomor_soal", 5);
                soalActivity.putExtra("id_soal", soal);
                startActivity(soalActivity);
            }
        });

    }
    // Menampilkan soal quiz
    private void getSoalQuiz(int id) {
        if (id == 0){

        }else if( id == 1){

        }
    }

    // Memanggil fitur google speech
    /*private void getSpeech(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ar_EG");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Device not support Google Speech featured", Toast.LENGTH_SHORT).show();
        }

    }*/

    //
    public void nextSoal(){

    }

    // Mendapatkan nilai hasil speech
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 10:
                try {
                    if (resultCode == RESULT_OK && data != null){
                        result = new ArrayList<>();
                        result.add(String.valueOf(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)));
                        skor+=1;
                        status=true;
                        if (status != false){
                            nextSoal();
                        }
                    }
                }
                catch (Exception ex){
                    Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
        }
    }*/
}
