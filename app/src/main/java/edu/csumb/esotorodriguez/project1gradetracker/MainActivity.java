package edu.csumb.esotorodriguez.project1gradetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final int CREATE_USER_REQUEST = 1;
    public static final int LOG_IN_REQUEST = 2;

    Button log;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log = findViewById(R.id.btnLog);
        create = findViewById(R.id.btnCreate);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = LogInScreen.getIntent(getApplicationContext(), LOG_IN_REQUEST);
                startActivity(intent);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = CreateUserScreen.getIntent(getApplicationContext(), CREATE_USER_REQUEST);
                startActivity(intent);
            }
        });

    }
}