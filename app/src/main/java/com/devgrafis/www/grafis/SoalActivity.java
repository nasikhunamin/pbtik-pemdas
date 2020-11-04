package com.devgrafis.www.grafis;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SoalActivity extends AppCompatActivity {
private TextView soalFix;
private ImageView speech;
private String jawabanFix;
private Dialog dialogResult;
private TextView mResult, mResultVoice;
private ImageView closeDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);
        soalFix = findViewById(R.id.soalQuiz);
        speech = findViewById(R.id.mic);
        //int soal = getIntent().getIntExtra("id_soal", 0);
        int nomor_soal = getIntent().getIntExtra("nomor_soal", 0);

        String soalQuiz[] = getResources().getStringArray(R.array.SoalQuiz1);
        String jawaban[] = getResources().getStringArray(R.array.JawabanQuiz1);
        soalFix.setText(soalQuiz[nomor_soal-1]);
        jawabanFix = jawaban[nomor_soal-1];
        dialogResult = new Dialog(this);
        speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSpeech();
            }
        });
    }
    private void getSpeech(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");

        if (intent.resolveActivity(getPackageManager()) != null) {
            this.startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Device not support Google Speech featured", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("result", " "+ requestCode);
        switch (requestCode){
            case 10:
                try {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (resultCode == RESULT_OK && data != null){
                        //Log.i("result", result.get(0));
                        Toast.makeText(this, jawabanFix, Toast.LENGTH_SHORT).show();
                        if (result.get(0).equals(jawabanFix)) {
                            showDialog("Right", result.get(0));
                        } else {
                            showDialog("Wrong", result.get(0));
                        }
                    }
                }
                catch (Exception ex){
                    Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
        }
    }

    public void showDialog(String result, String resultVoice){
        dialogResult.setContentView(R.layout.dialog_quiz);
        closeDialog = dialogResult.findViewById(R.id.closeDialog);
        mResult = dialogResult.findViewById(R.id.result);
        mResultVoice = dialogResult.findViewById(R.id.resultVoice);
        mResult.setText(result);
        mResultVoice.setText(resultVoice);

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
