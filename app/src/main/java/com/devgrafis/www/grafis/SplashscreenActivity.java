package com.devgrafis.www.grafis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class SplashscreenActivity extends AppCompatActivity {
    private boolean active = true;
    private int time = 3500;
    private ImageView logo;
    private Animation fromtop;
    private ProgressBar progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        fromtop = AnimationUtils.loadAnimation(this, R.anim.fromtop);

        logo = findViewById(R.id.logo);
        progress = findViewById(R.id.progress);
        //progress.getProgressDrawable().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        logo.setAnimation(fromtop);
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while(active && (waited <time)) {
                        sleep(100);
                        if(active) {
                            waited += 100;
                        }
                    }
                } catch(InterruptedException e) {
                    // do nothing
                } finally {
                    finish();
                    progress.setVisibility(View.VISIBLE);
                    startActivity(new Intent(SplashscreenActivity.this, LoginActivity.class));
                    //startActivity(new Intent(SplashscreenActivity.this, MainActivity.class));
                }
            }
        };
        splashTread.start();

    }
}
