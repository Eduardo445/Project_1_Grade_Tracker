package edu.csumb.esotorodriguez.project1gradetracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
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
    public static final int EDIT_USER_REQUEST = 2;


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

        final UserAdapter adapter = new UserAdapter();
        userView.setAdapter(adapter);
//        userDB = new ViewModelProvider(this).get(UserDao.class);
        Intent intent = getIntent();
        if (intent.hasExtra(USER_NAME)) {
            User user = userDB.getSpecificUser(intent.getStringExtra(USER_NAME));
        }



        coursesView.setLayoutManager(new LinearLayoutManager(this));
        coursesView.setHasFixedSize(true);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                userDB.delete(adapter.getUserAt(viewHolder.getAdapterPosition()));
                Toast.makeText(UserActivity.this, "User Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(userView);

        adapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User user) {
                Intent intent = new Intent(UserActivity.this, editUserScreen.class);
                intent.putExtra(editUserScreen.EXTRA_ID, user.getUserID());
                intent.putExtra(editUserScreen.EXTRA_USERNAME, user.getUsername());
                intent.putExtra(editUserScreen.EXTRA_PASSWORD, user.getPassword());
                intent.putExtra(editUserScreen.EXTRA_FIRST_NAME, user.getFirstName());
                intent.putExtra(editUserScreen.EXTRA_LAST_NAME, user.getLastName());

                startActivityForResult(intent, EDIT_USER_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_USER_REQUEST && resultCode == RESULT_OK){
            String username = data.getStringExtra(editUserScreen.EXTRA_USERNAME);
            String password = data.getStringExtra(editUserScreen.EXTRA_PASSWORD);
            String firstName = data.getStringExtra(editUserScreen.EXTRA_FIRST_NAME);
            String lastName = data.getStringExtra(editUserScreen.EXTRA_LAST_NAME);

            User user = new User(username, password, firstName, lastName);
            userDB.insert(user);

            Toast.makeText(this, "User Saved", Toast.LENGTH_SHORT).show();
        } else if(requestCode == EDIT_USER_REQUEST && resultCode == RESULT_OK){
            int id = data.getIntExtra(editUserScreen.EXTRA_ID, -1);

            if(id == -1){
                Toast.makeText(this, "User can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String username = data.getStringExtra(editUserScreen.EXTRA_USERNAME);
            String password = data.getStringExtra(editUserScreen.EXTRA_PASSWORD);
            String firstName = data.getStringExtra(editUserScreen.EXTRA_FIRST_NAME);
            String lastName = data.getStringExtra(editUserScreen.EXTRA_LAST_NAME);

            User user = new User(username, password, firstName, lastName);
            user.setUserID(id);

            userDB.update(user);

            Toast.makeText(this, "User Updated", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "User Not Saved", Toast.LENGTH_SHORT).show();
        }
    }


    public static Intent getIntent(Context context, String value) {
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra(USER_NAME, value);
        return intent;

    }
}