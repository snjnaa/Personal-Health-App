package com.example.health_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText steps = findViewById(R.id.steps);
        EditText goal = findViewById(R.id.goal);
        Button done = findViewById(R.id.done);
        Button home = findViewById(R.id.back);
        TextView tv = findViewById(R.id.comment);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double s = Double.parseDouble(steps.getText().toString());
                double g = Double.parseDouble(goal.getText().toString());
                double percent = (s/g)*100;

                if (percent>=50) {
                    tv.setText("You have achieved "+percent+"% of your goal! Good job!");
                }
                else if (percent==100) {
                    tv.setText("You have completed your required steps for the day! Way to go!");

                }
                else {
                    tv.setText("You have only achieved "+percent+ "% of your goal. You always have tomorrow to do better!");
                }
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
