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
import com.helloworld.vitauditoriumbookingsys.DateBooking.DateBookingRoom3;
import com.helloworld.vitauditoriumbookingsys.HomeAdapter.BookedSlotsRoom1.Room3Adapter;
import com.helloworld.vitauditoriumbookingsys.Model.RoomC;
import com.helloworld.vitauditoriumbookingsys.R;

import java.util.ArrayList;

public class Room3  extends AppCompatActivity {

    Room3Adapter room3Adapter;
    RecyclerView room3recycler;

    DatabaseReference databaseReference;

    ArrayList<RoomC> roomCList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room3);

        Button next3;

        databaseReference = FirebaseDatabase.getInstance().getReference("RoomC");
        room3recycler = findViewById(R.id.booked_slots_room3_date_recyclerView);
        room3recycler.setHasFixedSize(true);
        room3recycler.setLayoutManager(new LinearLayoutManager(this));
        roomCList = new ArrayList<>();
        room3Adapter = new Room3Adapter(getApplicationContext(), roomCList);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                roomCList.clear();
                for (DataSnapshot ds:snapshot.getChildren()) {
                    RoomC roomC = ds.getValue(RoomC.class);
                    roomC.setDate(ds.getKey());
                    roomCList.add(roomC);
                }
                room3Adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        room3recycler.setAdapter(room3Adapter);

        next3 = (Button) findViewById(R.id.btn_next_step_room3);

        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Room3.this, DateBookingRoom3.class);
                startActivity(intent);
            }
        });

    }
}
