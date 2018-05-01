package com.kelompok2.android.aplikasikesehatan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText LEmail;
    private EditText LPass;
    private EditText LConfPass;
    private TextView LAkun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        LEmail = (EditText) findViewById(R.id.etEmail);
        LPass = (EditText) findViewById(R.id.etPass);
        LConfPass = (EditText) findViewById(R.id.etConfPass);
        LAkun = (TextView) findViewById(R.id.textView);

    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser curUser = Constant.mAuth.getCurrentUser();
        if(curUser != null){
            sendtoMain();
        }

    }

   private void sendtoMain() {
       Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
       startActivity(intent);
//       finish();
    }

    private ProgressDialog pDialog;
    public void akunbaru(View view) {

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait..");
        pDialog.show();

        String Email = LEmail.getText().toString();
        String Password = LPass.getText().toString();
        String ConfPasswprd = LConfPass.getText().toString();

        Constant.mAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("", "createUserWithEmail:success");
                            Constant.currentUser = Constant.mAuth.getCurrentUser(); //simpan data user
                            pDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Berhasil mendaftar, Silahkan login!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class)); //panggil login activity
                            finish();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("", "createUserWithEmail:failure", task.getException());
                            pDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }

    public void btnbacktologin(View view) {
     sendtoMain();
    }
}
