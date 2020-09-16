package edu.csumb.esotorodriguez.project1gradetracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int CREATE_USER_REQUEST = 1;
    public static final int LOG_IN_REQUEST = 2;
//    private UserViewModel userViewModel;

    Button log;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log = findViewById(R.id.btnLog);
        create = findViewById(R.id.btnCreate);

//        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

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
//                Intent intent = new Intent(MainActivity.this, CreateUserScreen.class);
//                startActivityForResult(intent, CREATE_USER_REQUEST);
            }
        });

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == CREATE_USER_REQUEST && resultCode == RESULT_OK) {
//            String firstName = data.getStringExtra(CreateUserScreen.EXTRA_FIRST_NAME);
//            String lastName = data.getStringExtra(CreateUserScreen.EXTRA_LASR_NAME);
//            String username = data.getStringExtra(CreateUserScreen.EXTRA_USERNAME);
//            String password = data.getStringExtra(CreateUserScreen.EXTRA_PASSWORD);
//
//            User user = new User(firstName, lastName, username, password);
//            userViewModel.insert(user);
//            Log.i("MainActivity", "This is the start of Saved User\n");
//            Log.i("MainActivity", Integer.toString(userViewModel.getAllUsers().getValue().size()));
//
//            Toast.makeText(this, "User saved", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "User not saved", Toast.LENGTH_SHORT).show();
//        }
//    }

//    public static Intent getIntent(Context context, int value) {
//        Intent intent = new Intent(context, MainActivity.class);
//        intent.putExtra("HOME_SCREEN", value);
//        return intent;
//    }
}