package com.kelompok2.android.aplikasikesehatan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText LEmail;
    private EditText LPass;
    private EditText LConfPass;
    private Button LAkun;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        LEmail = (EditText) findViewById(R.id.etEmail);
        LPass = (EditText) findViewById(R.id.etPass);
        LConfPass = (EditText) findViewById(R.id.etConfPass);
        LAkun = (Button) findViewById(R.id.button_regis);

    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentuser = mAuth.getCurrentUser();
        if(currentuser != null){
            sendtoMain();
        }

    }

   private void sendtoMain() {
       Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
       startActivity(intent);
       finish();
    }

    public void akunbaru(View view) {
        String Email = LEmail.getText().toString();
        String Password = LPass.getText().toString();
        String ConfPasswprd = LConfPass.getText().toString();

        if (!TextUtils.isEmpty(Email) && !TextUtils.isEmpty(Password) & !TextUtils.isEmpty(ConfPasswprd)){
            if (Password.equals(ConfPasswprd)){
                mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                           sendtoMain();
                        }else {
                            String pesanerror = task.getException().getMessage();
                            Toast.makeText(RegisterActivity.this, "Error" + pesanerror, Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }else {
                Toast.makeText(RegisterActivity.this, "Confirm Password", Toast.LENGTH_LONG).show();
            }
        }
    }
}
