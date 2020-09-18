package edu.csumb.esotorodriguez.project1gradetracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class LogInScreen  extends AppCompatActivity {

    private UserDao userDB;

    private EditText username;
    private EditText password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_screen);

        userDB = Room.databaseBuilder(this, TrackerDB.class, TrackerDB.TRACKER_DB).allowMainThreadQueries().build().userDao();

        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnSignIn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkUser()) {
                    Toast.makeText(LogInScreen.this, "Logged in new User", Toast.LENGTH_SHORT).show();
                    Intent intent = UserActivity.getIntent(getApplicationContext(),
                            userDB.getSpecificUser(username.getText().toString()).getUserID());
                    startActivity(intent);
                }
            }
        });
    }

    private boolean checkUser(){
        String user = username.getText().toString();
        String pass = password.getText().toString();

        if (!allInputs(user, pass)) {
            Toast.makeText(LogInScreen.this, "Enter a Username and Password", Toast.LENGTH_SHORT).show();
            return false;
        }

        User user1 = userDB.getSpecificUser(user);
        if (user1 != null) {
            if (user1.getUsername().equals(user) && user1.getPassword().equals(pass)) {
                return true;
            }
        }

        Toast.makeText(LogInScreen.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
        return false;
    }

    private boolean allInputs(String user, String pass) {
        if (user.trim().isEmpty() || pass.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public static Intent getIntent(Context context, int value) {
        Intent intent = new Intent(context, LogInScreen.class);
        intent.putExtra("LOGIN_USER", value);
        return intent;
    }
}