package edu.csumb.esotorodriguez.project1gradetracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LogInScreen  extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_screen);

        username = findViewById(R.id.usernameInput);
        password = findViewById(R.id.passwordInput);
        login = findViewById(R.id.SignInButton);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(usernameCheck(username.getText().toString()) == true && passwordCheck(password.getText().toString()) == true) {
                    openLaunchScreen();
                }else if(usernameCheck(username.getText().toString()) == false && passwordCheck(password.getText().toString()) == false){
                    username.setError("Invalid Username!");
                    password.setError("Invalid Password!");
                } else if(usernameCheck(username.getText().toString()) == false){
                    username.setError("Invalid Username!");
                } else if(passwordCheck(password.getText().toString()) == false){
                    password.setError("Invalid Password!");
                }
            }
        });
    }
    public void openLaunchScreen() {
        Intent intent = new Intent(LogInScreen.this, MainActivity.class);
        startActivity(intent);
    }

    private boolean usernameCheck(String username){
        if(username.equals("admin1")){
            return true;
        } else {
            return false;
        }
    }

    private boolean passwordCheck(String password){
        if(password.equals("admin1")){
            return true;
        } else {
            return false;
        }
    }
}