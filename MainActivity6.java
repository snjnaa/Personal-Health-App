package com.example.health_tracker;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity6 extends AppCompatActivity {
    SeekBar sb;
    TextView cd;
    Button start;
    CountDownTimer countDownTimer;
    Boolean counterIsActive = false;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main6);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sb = findViewById(R.id.seekBar);
        cd = findViewById(R.id.countdown);
        start = findViewById(R.id.start_btn);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.timeup);
        sb.setMax(20);      //change maximum allowed time here
        sb.setProgress(10);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                update(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button btn = findViewById(R.id.back);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity6.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void update(int progress) {
        int minutes = progress/60;
        int seconds = progress % 60;
        String secondsFinal = "";
        if (seconds <=9) {
            secondsFinal = "0"+seconds;
        }
        else {
            secondsFinal = ""+seconds;
        }
        sb.setProgress(progress);
        cd.setText(""+minutes+ ":" +secondsFinal);
    }

    public void start_timer(View view) {
        if (counterIsActive == false) {
            counterIsActive = true;
            sb.setEnabled(false);
            start.setText("Stop");
            countDownTimer = new CountDownTimer(sb.getProgress()*1000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    update((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    reset();
                    if (mediaPlayer != null)
                        mediaPlayer.start();
                }
            }.start();
        }
        else {
            reset();
        }
    }

    private void reset() {
        cd.setText("0:10");
        sb.setProgress(10);
        countDownTimer.cancel();
        start.setText("Start");
        sb.setEnabled(true);
        counterIsActive = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (counterIsActive) {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (counterIsActive) {
            countDownTimer.cancel();
        }
    }
}
