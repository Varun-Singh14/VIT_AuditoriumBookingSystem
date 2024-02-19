package com.helloworld.vitauditoriumbookingsys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.helloworld.vitauditoriumbookingsys.Common.Common;

import java.util.ArrayList;

public class AllEvents extends AppCompatActivity {

    String userRegNo;
    ArrayList<AllEventsHelperClass> allEventsList;
    RecyclerView rview;
    AllEventsAdapter adtv;

    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_events);
        allEventsList = new ArrayList<>();
        userRegNo = Common.currentUser.getRegNo();
        rview = findViewById(R.id.AllEventsRecyclerView);
        rview.setHasFixedSize(true);
        rview.setLayoutManager(new LinearLayoutManager(this));

        adtv = new AllEventsAdapter(AllEvents.this, allEventsList);

        db = FirebaseDatabase.getInstance().getReference();

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                allEventsList.clear();
                for (DataSnapshot fds: snapshot.getChildren()){
                    if (fds.getKey().startsWith("Room")){
                        for (DataSnapshot sds: fds.getChildren()){
                                AllEventsHelperClass abthc = sds.getValue(AllEventsHelperClass.class);
                                abthc.setDate(sds.getKey());
                                abthc.setRoomName(fds.getKey());
                                allEventsList.add(abthc);


                        }
                    }
                }
                adtv.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        rview.setAdapter(adtv);
    }
}