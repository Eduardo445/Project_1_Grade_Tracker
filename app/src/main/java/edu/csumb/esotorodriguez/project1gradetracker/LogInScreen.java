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
                            userDB.getSpecificUser(username.getText().toString()).getUsername());
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * Purpose is to check if the user is in the DB.
     * 1: It checks that the user inputs all information.
     *    - Returns false and lets user know to input all information
     * 2: Checks the DB for a matching user.
     *    - Returns false if their is no user in DB
     * @return true if all passes.
     */
    private boolean checkUser() {
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

    /**
     * Helper method to check if all fields where filled.
     * @param user Username that the user inputted
     * @param pass Password that the user inputted
     * @return     false if a field value is missing, otherwise true.
     */
    private boolean allInputs(String user, String pass) {
        if (user.trim().isEmpty() || pass.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Allows to travel from the Main Screen to the Login Screen
     * @param context for main screen's file
     * @param value   for checking if intent is successful
     * @return        the intent value.
     */
    public static Intent getIntent(Context context, int value) {
        Intent intent = new Intent(context, LogInScreen.class);
        intent.putExtra("LOGIN_USER", value);
        return intent;
    }
}