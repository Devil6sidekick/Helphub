package com.example.charityyyyy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginPage extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText loginEmail,loginPassword;
    private TextView registerNow;
    private Button login_button;
    TextView forgotPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setStatusBarColor(ContextCompat.getColor(loginPage.this,R.color.beige));

        auth = FirebaseAuth.getInstance();
        loginEmail=findViewById(R.id.login_email);
        loginPassword=findViewById(R.id.login_password);
        registerNow=findViewById(R.id.register_now);
        login_button=findViewById(R.id.login_button);
        forgotPassword=findViewById(R.id.forgot_password);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = loginEmail.getText().toString();
                String pass = loginPassword.getText().toString();

                if (!mail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
                    if (!pass.isEmpty()){
                        auth.signInWithEmailAndPassword(mail,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(loginPage.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(loginPage.this,homePage.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(loginPage.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else {
                        loginPassword.setError("You forgot the Password");
                    }
                }else if(mail.isEmpty()) {
                    loginEmail.setError("You forgot the Email");
                }else{
                    loginEmail.setError("Please enter a valid Email");
                }

            }
        });

        registerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginPage.this, registerPage.class));
            }
        });


        //mot de passe oublie
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(loginPage.this);
                View dialogView = getLayoutInflater().inflate(R.layout.forgotpassword,null);
                EditText resetMail = dialogView.findViewById(R.id.forgot_email);

                builder.setView(dialogView);
                AlertDialog dialog = builder.create();

                dialogView.findViewById(R.id.forgot_reset_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String mail = resetMail.getText().toString();

                        if (TextUtils.isEmpty(mail) || !Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
                            resetMail.setError("Enter a valid email");
                            return;
                        }
                        auth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(loginPage.this, "Check your email", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }else {
                                    Toast.makeText(loginPage.this, "Unable to send, failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                dialogView.findViewById(R.id.forgot_cancel_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                if (dialog.getWindow() != null){
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                dialog.show();
            }
        });
    }
}