package com.helloworld.vitauditoriumbookingsys.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.helloworld.vitauditoriumbookingsys.Common.Common;
import com.helloworld.vitauditoriumbookingsys.DateBooking.DateBookingRoom1;
import com.helloworld.vitauditoriumbookingsys.Model.RoomA;
import com.helloworld.vitauditoriumbookingsys.Model.RoomB;

import com.helloworld.vitauditoriumbookingsys.HomeAdapter.BookedSlotsRoom1.Room1Adapter;
import com.helloworld.vitauditoriumbookingsys.R;

import java.util.ArrayList;
import java.util.Objects;

public class Room1  extends AppCompatActivity {

    Room1Adapter room1Adapter;
    RecyclerView room1recycler;

    DatabaseReference databaseReference;

    ArrayList<RoomB> roomAList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room1);

        Button next;

        databaseReference = FirebaseDatabase.getInstance().getReference("RoomA");
        room1recycler = findViewById(R.id.booked_slots_room1_date_recyclerView);
        room1recycler.setHasFixedSize(true);
        room1recycler.setLayoutManager(new LinearLayoutManager(this));
        roomAList = new ArrayList<>();
        room1Adapter = new Room1Adapter(getApplicationContext(), roomAList);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                roomAList.clear();
                for (DataSnapshot ds:snapshot.getChildren()) {
                    RoomB roomA = ds.getValue(RoomB.class);
                    roomA.setDate(ds.getKey());
                    roomAList.add(roomA);
                }
                room1Adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        room1recycler.setAdapter(room1Adapter);




        next = (Button) findViewById(R.id.btn_next_step_room1);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Room1.this, DateBookingRoom1.class);
                startActivity(intent);
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot roomSnapShot) {
                for(DataSnapshot dateSnapShot: roomSnapShot.getChildren() ) {
                    RoomA room_a = dateSnapShot.getValue(RoomA.class);
                    if (Objects.requireNonNull(room_a).getRegNo().equals(Common.currentUser.getRegNo())) {
//                        Toast.makeText(Room1.this, room_a.getStatus(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        
    }
}
