package com.example.daraz_fourth_assignment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity
{
    private Button CreateAccountButton;
    private EditText InputName,InputPhoneNumber,InputPassword;
//    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        CreateAccountButton = findViewById(R.id.register_btn);
        InputName = findViewById(R.id.register_username_input);
        InputPhoneNumber = findViewById(R.id.register_phone_number_input);
        InputPassword = findViewById(R.id.register_password_input);

        // Since Progress Dialog is deprecated from API level 26 and above we need use Progress Bar instead
        // Therefore we specify layout in-order to follow up the process

//        RelativeLayout layout = findViewById(this);

        // Adding the progress bar

//        progressBar = new ProgressBar(RegisterActivity.this,null,android.R.attr.progressBarStyleLarge);
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100,100);
//        params.addRule(RelativeLayout.CENTER_IN_PARENT);
//        layout.addView(progressBar, params);


        // Initialize account

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                CreateAccount();

            }
        });

    }




    private void CreateAccount()
    {
        // fetch the data provided and convert them to IDE readable format

        String name = InputName.getText().toString();
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();


        // Check either the fields are empty or not (Validate)

        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "Please specify your name", Toast.LENGTH_SHORT).show();

        }

        else if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Phone number not specified", Toast.LENGTH_SHORT).show();

        }

        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please enter a 6-digit password", Toast.LENGTH_SHORT).show();

        }

        else
        {
            ValidatePhoneNumber(name, phone, password);
        }


    }

    private void ValidatePhoneNumber(final String name, final String phone, final String password)
    {
        // This is a database reference

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // The first if validation is to identify if the phone number exists then no more duplication of that id

                if(!(dataSnapshot.child("Users").child(phone).exists()))
                {
                    // if that phone number which will be placed does not exist then only it will let new user to create

                    HashMap<String,Object> userDataMap = new HashMap<>();
                    userDataMap.put("phone",phone);
                    userDataMap.put("password",password);
                    userDataMap.put("name",name);

                    // Basically now every user will be created based on phone as priority check
                    RootRef.child("Users").child(phone).updateChildren(userDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    // Send the user to login activity

                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(RegisterActivity.this, "Congratulation, your account has been created", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                                        startActivity(i);

                                    }
                                    // Just in-case there is some error and account is not being created display a message
                                    else
                                    {
                                        Toast.makeText(RegisterActivity.this, "Network Error !! Please try again", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "This " + phone + " already exists", Toast.LENGTH_SHORT).show();
                    Toast.makeText(RegisterActivity.this, "Sending back to login screen", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}


