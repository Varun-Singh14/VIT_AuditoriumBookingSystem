package com.helloworld.vitauditoriumbookingsys.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.helloworld.vitauditoriumbookingsys.DateBooking.DateBookingRoom2;
import com.helloworld.vitauditoriumbookingsys.HomeAdapter.BookedSlotsRoom1.Room2Adapter;
import com.helloworld.vitauditoriumbookingsys.Model.RoomB;
import com.helloworld.vitauditoriumbookingsys.R;

import java.util.ArrayList;

public class Room2  extends AppCompatActivity {

    Room2Adapter room2Adapter;
    RecyclerView room2recycler;

    DatabaseReference databaseReference;

    ArrayList<RoomB> roomBList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room2);

        Button next1;

        databaseReference = FirebaseDatabase.getInstance().getReference("RoomB");
        room2recycler = findViewById(R.id.booked_slots_room2_date_recyclerView);
        room2recycler.setHasFixedSize(true);
        room2recycler.setLayoutManager(new LinearLayoutManager(this));
         roomBList = new ArrayList<>();
        room2Adapter = new Room2Adapter(getApplicationContext(), roomBList);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                roomBList.clear();
                for (DataSnapshot ds:snapshot.getChildren()) {
                    RoomB roomB = ds.getValue(RoomB.class);
                    roomB.setDate(ds.getKey());
                    roomBList.add(roomB);
                }
                room2Adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        room2recycler.setAdapter(room2Adapter);

        next1 = (Button) findViewById(R.id.btn_next_step_room2);

        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Room2.this, DateBookingRoom2.class);
                startActivity(intent);
            }
        });

    }
}
