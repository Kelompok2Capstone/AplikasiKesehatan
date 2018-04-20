package com.kelompok2.android.aplikasikesehatan;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by User on 21/04/2018.
 */

public class Constant {
    //Firebase Database
    public final static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public final static DatabaseReference mypost = database.getReference("post");

    //Firebase Storage
    public static FirebaseStorage storage = FirebaseStorage.getInstance();
    public static StorageReference storageRef = storage.getReference();

}
