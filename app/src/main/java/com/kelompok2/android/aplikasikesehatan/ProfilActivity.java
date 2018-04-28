package com.kelompok2.android.aplikasikesehatan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfilActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference databaseReference;

    private EditText editTextName;
    private EditText editTextAddress;
    private EditText editTextPhone;
    private EditText editTextPekerjaan;

    private Button buttonUpdate;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

//        firebaseAuth = FirebaseAuth.getInstance();
        if (Constant.mAuth.getCurrentUser() == null) {
            finish();
        }
        databaseReference = FirebaseDatabase.getInstance().getReference();
//        FirebaseUser user = firebaseAuth.getCurrentUser();
        Constant.mAuth.getCurrentUser();

        editTextName = (EditText) findViewById(R.id.name);
        editTextAddress = (EditText) findViewById(R.id.address);
        editTextPhone = (EditText) findViewById(R.id.phone);
        editTextPekerjaan = (EditText) findViewById(R.id.pekerjaan);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);

        buttonUpdate.setOnClickListener(this);
    }

    private void saveUserInformation(){
        String name = editTextName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String pekerjaan = editTextPekerjaan.getText().toString().trim();


        UserInformation userInformation = new UserInformation(name, address, phone, pekerjaan);

        String online_user_id = Constant.mAuth.getCurrentUser().getUid();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference User = rootRef.child("User").child(online_user_id).push();

        User.setValue(userInformation);

        Toast.makeText(this, "Information Updated..", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, UploadPhoto.class));
    }

    @Override
    public void onClick(View view) {
        if(view==buttonUpdate){
            saveUserInformation();
        }

    }
}
