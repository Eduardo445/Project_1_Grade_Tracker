
package edu.csumb.esotorodriguez.project1gradetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class editUserScreen extends AppCompatActivity {
    public static final String EXTRA_ID =
            "edu.csumb.esotorodriguez.Project_1_Grade_Tracker.EXTRA_ID";
    public static final String EXTRA_USERNAME =
            "edu.csumb.esotorodriguez.Project_1_Grade_Tracker.EXTRA_USERNAME";
    public static final String EXTRA_PASSWORD =
            "edu.csumb.esotorodriguez.Project_1_Grade_Tracker.EXTRA_PASSWORD";
    public static final String EXTRA_FIRST_NAME =
            "edu.csumb.esotorodriguez.Project_1_Grade_Tracker.EXTRA_FIRST_NAME";
    public static final String EXTRA_LAST_NAME =
            "edu.csumb.esotorodriguez.Project_1_Grade_Tracker.EXTRA_LAST_NAME";

    private EditText username;
    private EditText password;
    private EditText firstName;
    private EditText lastName;

    private UserDao userDB;


    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_screen);

        username = findViewById(R.id.editUsername);
        password = findViewById(R.id.editPassword);
        firstName = findViewById(R.id.editFirstname);
        lastName = findViewById(R.id.editLastname);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit User");
            username.setText(intent.getStringExtra(EXTRA_USERNAME));
            password.setText(intent.getStringExtra(EXTRA_PASSWORD));
            firstName.setText(intent.getStringExtra(EXTRA_FIRST_NAME));
            lastName.setText(intent.getStringExtra(EXTRA_LAST_NAME));

        }else {
            setTitle("Add Note");
        }

        userDB = Room.databaseBuilder(this, TrackerDB.class, TrackerDB.TRACKER_DB).allowMainThreadQueries().build().userDao();

    }

    private void saveUser(){
        String mUsername = username.getText().toString();
        String mPassword = password.getText().toString();
        String mFirstName = firstName.getText().toString();
        String mLastName = lastName.getText().toString();

        if(mUsername.trim().isEmpty() || mPassword.trim().isEmpty() || mFirstName.trim().isEmpty() || mLastName.trim().isEmpty()){
            Toast.makeText(this, "Please insert a username, password, first name, and last name", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_USERNAME, mUsername);
        data.putExtra(EXTRA_PASSWORD, mPassword);
        data.putExtra(EXTRA_FIRST_NAME, mFirstName);
        data.putExtra(EXTRA_LAST_NAME, mLastName);


        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if(id != -1){
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_user_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.save_note:
                saveUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}