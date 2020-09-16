package edu.csumb.esotorodriguez.project1gradetracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccount extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText firstName;
    private EditText lastName;
    private Button createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        username = findViewById(R.id.usernameInput);
        password = findViewById(R.id.passwordInput);
        firstName = findViewById(R.id.firstNameInput);
        lastName = findViewById(R.id.lastNameInput);
        createAccount = findViewById(R.id.createAccountButton);

        createAccount.setOnClickListener(new View.OnClickListener() {

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
}
