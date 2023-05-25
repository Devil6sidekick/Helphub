package com.example.charityyyyy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registerPage extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText registerName, registerEmail, registerPhone, registerPassword;
    private Button registerButton;
    private TextView have_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setStatusBarColor(ContextCompat.getColor(registerPage.this,R.color.beige));

        auth = FirebaseAuth.getInstance();
        registerName = findViewById(R.id.register_fullname);
        registerEmail = findViewById(R.id.register_email);
        registerPhone = findViewById(R.id.register_phone);
        registerPassword = findViewById(R.id.register_password);
        registerButton = findViewById(R.id.register_button);
        have_account = findViewById(R.id.have_account);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = registerName.getText().toString().trim();
                String mail = registerEmail.getText().toString().trim();
                String phone = registerPhone.getText().toString().trim();
                String pass = registerPassword.getText().toString().trim();

                if (user.isEmpty()) {
                    registerName.setError("You forgot the name");
                }if (mail.isEmpty()){
                    registerEmail.setError("You forgot the email");
                }if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
                    registerEmail.setError("Please Enter a valid mail");
                }if (phone.isEmpty()){
                    registerPhone.setError("You forgot the phone number");
                }if(!Patterns.PHONE.matcher(phone).matches()){
                    registerPhone.setError("Please enter a valid number");
                }if (pass.isEmpty()){
                    registerPassword.setError("You forgot the password");
                }else {
                    auth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(registerPage.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(registerPage.this, loginPage.class));
                            }else {
                                Toast.makeText(registerPage.this, "Failed to Register" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        have_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(registerPage.this,loginPage.class));
            }
        });

    }
}