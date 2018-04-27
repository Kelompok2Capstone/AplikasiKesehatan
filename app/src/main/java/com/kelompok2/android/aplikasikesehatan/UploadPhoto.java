package com.kelompok2.android.aplikasikesehatan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class UploadPhoto extends AppCompatActivity {
    private CircleImageView profilePicture;
    private Button choose, upload;
    private TextView username;
    private EditText name, address, phone, pekerjaan;

    private final static int Gallery_Pick = 1;
    private StorageReference mStorageRef;

    private DatabaseReference mDatabaseRef;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photo);
        mAuth = FirebaseAuth.getInstance();
        String online_user_id = mAuth.getCurrentUser().getUid();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("User").child(online_user_id);
        mStorageRef = FirebaseStorage.getInstance().getReference().child("Profile_Image");

        profilePicture = (CircleImageView) findViewById(R.id.profile_image);
        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        phone = (EditText) findViewById(R.id.phone);
        pekerjaan = (EditText) findViewById(R.id.pekerjaan);
        upload = (Button) findViewById(R.id.btnUpload);

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                String name = dataSnapshot.child("name").getValue().toString();

                try {
                    for (DataSnapshot ds:dataSnapshot.getChildren()) {
                        UserInformation user = ds.getValue(UserInformation.class);
                        name.setText(user.getName());
                        address.setText(user.getAddress());
                        phone.setText(user.getPhone());
                        pekerjaan.setText(user.getPekerjaan());
                    }

                    String image = dataSnapshot.child("user_image").getValue().toString();
//
//                username.setText(name);
                    Picasso.get().load(image).into(profilePicture);
                } catch (Exception ex) {}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, Gallery_Pick);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==Gallery_Pick && resultCode==RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(this);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK){
                Uri resultUri = result.getUri();

                String user_id = mAuth.getCurrentUser().getUid();
                StorageReference filePath = mStorageRef.child(user_id+".jpg");
                filePath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(UploadPhoto.this, "Saving..", Toast.LENGTH_LONG).show();

                            String downloadUrl = task.getResult().getDownloadUrl().toString();
                            mDatabaseRef.child("user_image").setValue(downloadUrl)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(UploadPhoto.this, "success", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent (UploadPhoto.this, AwalActivity.class);
                                            startActivity(i);
                                        }
                                    });
                        }

                        else {
                            Toast.makeText(UploadPhoto.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error = result.getError();
            }
        }
    }
}
