package com.example.health_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity5 extends AppCompatActivity {
    private EditText date, waterintake;
    private Button submit;
    private DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btn = findViewById(R.id.back);
        Button read = findViewById(R.id.read);
        Button delete = findViewById(R.id.del);
        date = findViewById(R.id.date);
        waterintake = findViewById(R.id.intake);
        submit = findViewById(R.id.add);

        db = new DBHandler(getApplicationContext(), "waterIntake", null, 1);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String subDate = date.getText().toString();
                String subIntake = waterintake.getText().toString();

                if (subDate.isEmpty() || subIntake.isEmpty()) {
                    Toast.makeText(MainActivity5.this, "Please enter all the data.", Toast.LENGTH_LONG).show();
                    return;
                }

                db.insert(subDate, subIntake);

                Toast.makeText(MainActivity5.this, "Water Intake level has been added.", Toast.LENGTH_LONG).show();
                date.setText("");
                waterintake.setText("");
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity5.this, MainActivity.class);
                startActivity(i);
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity5.this, MainActivity7.class);
                startActivity(i);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subDate = date.getText().toString();
                db.delete(subDate);
                Toast.makeText(MainActivity5.this, "Entry has been deleted.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
