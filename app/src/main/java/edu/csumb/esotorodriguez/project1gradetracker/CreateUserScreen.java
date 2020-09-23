package edu.csumb.esotorodriguez.project1gradetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateUserScreen extends AppCompatActivity {

    private UserDao userDB;

    private EditText first;
    private EditText last;
    private EditText user;
    private EditText pass;
    private Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user_screen);

        userDB = Room.databaseBuilder(this, TrackerDB.class, TrackerDB.TRACKER_DB).allowMainThreadQueries().build().userDao();

        first = findViewById(R.id.etFirstName);
        last = findViewById(R.id.etLastName);
        user = findViewById(R.id.etUsername2);
        pass = findViewById(R.id.etPassword2);
        create = findViewById(R.id.btnCreateUser);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (saveUser()) {
                    Toast.makeText(CreateUserScreen.this, "Created a new user account", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateUserScreen.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * Saves a new user into the DB as it meets.
     * the following requirements:
     * 1: Checks that no field is empty.
     *    - Returns false and lets the user know that fields are empty
     * 2: Checks that there is no duplicate username in the DB.
     *    - Returns false and lets the user know that the username
     *      already used
     * @return true and lets the user know that they created
     *         an account.
     */
    private boolean saveUser() {
        String firstName = first.getText().toString();
        String lastName = last.getText().toString();
        String username = user.getText().toString();
        String password = pass.getText().toString();

        if (firstName.trim().isEmpty() || lastName.trim().isEmpty() ||
            username.trim().isEmpty() || password.trim().isEmpty()) {
            Toast.makeText(CreateUserScreen.this, "Please insert all information", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (duplicateUsername(username)) {
            Toast.makeText(CreateUserScreen.this, "Username already used", Toast.LENGTH_SHORT).show();
            return false;
        }

        User user = new User(firstName, lastName, username, password);
        userDB.insert(user);
        return true;
    }

    /**
     * Helper method to check that their isn't the same
     * username in the DB.
     * @param username use for checking no duplicates
     * @return         false if found duplicate, otherwise true
     */
    private boolean duplicateUsername(String username) {
        User user1 = userDB.getSpecificUser(username);
        if (user1 != null) {
            return true;
        }
        return false;
    }

    /**
     * Allows to travel from the Main Screen to the CreateUser Screen
     * @param context for create user screen's file
     * @param value   for checking if intent is successful
     * @return        the intent value.
     */
    public static Intent getIntent(Context context, int value) {
        Intent intent = new Intent(context, CreateUserScreen.class);
        intent.putExtra("CREATE_USER", value);
        return intent;
    }
}