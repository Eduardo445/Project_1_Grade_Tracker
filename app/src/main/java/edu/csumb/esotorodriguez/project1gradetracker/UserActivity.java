package edu.csumb.esotorodriguez.project1gradetracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Insert;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class UserActivity extends AppCompatActivity {
    public static final String USER_NAME =
            "edu.csumb.esotorodriguez.project1gradetracker.USER_NAME";
    public static final int ADD_USER_REQUEST = 1;

    private UserDao userDB;

    RecyclerView userView;
    RecyclerView coursesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        userDB = Room.databaseBuilder(this, TrackerDB.class, TrackerDB.TRACKER_DB).allowMainThreadQueries().build().userDao();

        FloatingActionButton buttonAddNote = findViewById(R.id.button_add_note);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, editUserScreen.class);
                startActivityForResult(intent, ADD_USER_REQUEST);
            }
        });



        userView = findViewById(R.id.recycler_user_view);
        coursesView = findViewById(R.id.recycler_course_view);

        userView.setLayoutManager(new LinearLayoutManager(this));
        userView.setHasFixedSize(true);

        UserAdapter adapter = new UserAdapter();
        userView.setAdapter(adapter);
//        userDB = new ViewModelProvider(this).get(UserDao.class);
        Intent intent = getIntent();
        if (intent.hasExtra(USER_NAME)) {
            User user = userDB.getSpecificUser(intent.getStringExtra(USER_NAME));
        }



        coursesView.setLayoutManager(new LinearLayoutManager(this));
        coursesView.setHasFixedSize(true);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_USER_REQUEST && resultCode == RESULT_OK){
            String username = data.getStringExtra(editUserScreen.EXTRA_USERNAME);
            String password = data.getStringExtra(editUserScreen.EXTRA_PASSWORD);
            String firstName = data.getStringExtra(editUserScreen.EXTRA_FIRST_NAME);
            String lastName = data.getStringExtra(editUserScreen.EXTRA_LAST_NAME);

            User user = new User(username, password, firstName, lastName);
            userDB.insert(user);

            Toast.makeText(this, "User Saved", Toast.LENGTH_SHORT).show();
        }
    }


    public static Intent getIntent(Context context, String value) {
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra(USER_NAME, value);
        return intent;

    }
}