package com.helloworld.vitauditoriumbookingsys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.helloworld.vitauditoriumbookingsys.Common.Common;


public class Profile extends AppCompatActivity {

    TextView name, regno, phone;
    String userInfo;

    private FirebaseDatabase fd;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        //Hooks
        name = findViewById(R.id.textViewName);
        regno = findViewById(R.id.textViewRegno);
        phone = findViewById(R.id.textViewPhoneNumber);

        name.setText(Common.currentUser.getName());
        regno.setText(Common.currentUser.getRegNo());
        phone.setText(Common.currentUser.getPhoneNumber());

    }
}