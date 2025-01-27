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

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.result), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText height = findViewById(R.id.height);
        EditText weight = findViewById(R.id.weight);
        TextView tv = findViewById(R.id.bmi_res);
        Button res = findViewById(R.id.calc);
        Button home = findViewById(R.id.back);

        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double h = Double.parseDouble(height.getText().toString());
                double w = Double.parseDouble(weight.getText().toString());
                double hinm = h/100;
                double bmi = (w/(hinm*hinm));

                if (bmi<18.5) {
                    tv.setText("Your BMI is "+bmi+". You are underweight.");
                }
                else if (bmi >= 18.5 && bmi <= 24.9) {
                    tv.setText("Your BMI is "+bmi+". You are normal weight.");
                }
                else if (bmi >= 25.0 && bmi <= 29.9) {
                    tv.setText("Your BMI is "+bmi+". You are over weight.");
                }
                else {
                    tv.setText("Your BMI is "+bmi+". You are obese.");
                }
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
