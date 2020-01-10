package com.example.daraz_fourth_assignment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity
{
    private EditText InputPhoneNumber, InputPassword;
    private Button LoginButton;
    private CheckBox chkBoxRememberMe;

    // We could write Users in child but since we are going to use this same activity for admins as well therefore we enlist that in datatype

    private String parentDbName = "Users";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginButton = findViewById(R.id.login_btn);
        InputPhoneNumber = findViewById(R.id.login_phone_number_input);
        InputPassword = findViewById(R.id.login_password_input);
        chkBoxRememberMe = findViewById(R.id.remember_me_chkb);

        // Using Paper.io dependency that we have added for the remember me feature

        Paper.init(this);







        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser()
    {
        // fetch the data provided and convert them to IDE readable format

        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();

        // Check either the fields are empty or not (Validate)

        if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Phone number not specified", Toast.LENGTH_SHORT).show();

        }

        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please enter a 6-digit password", Toast.LENGTH_SHORT).show();

        }
        else
        {
            AllowAccessToAccount(phone,password);
        }

    }

    private void AllowAccessToAccount(final String phone, final String password)
    {
        // Before allowing the access we store the current user Phone Key and Password Key first

        if (chkBoxRememberMe.isChecked())
        {
            // Updates everytime the user enters their keys and store it to android phone memory thanks to Paper

            Paper.book().write(Prevalent.UserPhoneKey, phone);
            Paper.book().write(Prevalent.UserPasswordKey,password);
        }

        // This is a database reference

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        // Lets retrieve if the user is available or not means already in database or not check

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                // Since our .chil(phone) is set unqiue one to distinguish between every individual data on users
                if(dataSnapshot.child(parentDbName).child(phone).exists())
                {
                    // Before writing the codes below we created Model package and java class of users
                    // Also Prevalent package and class
                    // Importing Users from model class listed above....
                    // The below code retrives the name phone and password of the user and sends to Users class
                    // thats why we create getters setters stuff...

                    Users usersData = dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);

                    // first thing to do after this is to check wheather the phone number exists or not and is authentic to password
                    // Here userData.get part is of database and the phone / password is the one user typed and is being evaluated

                    if(usersData.getPhone().equals(phone))
                    {
                        if(usersData.getPassword().equals(password))
                        {
                            Toast.makeText(LoginActivity.this, "logged in Successfully...", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this,HomeActivity.class);
                            startActivity(i);


                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Account with this " + phone + " number do not exist", Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this, "Sending back to register page", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}


