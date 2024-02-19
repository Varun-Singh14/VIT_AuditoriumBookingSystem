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
import com.helloworld.vitauditoriumbookingsys.DateBooking.DateBookingRoom6;
import com.helloworld.vitauditoriumbookingsys.HomeAdapter.BookedSlotsRoom1.Room3Adapter;
import com.helloworld.vitauditoriumbookingsys.HomeAdapter.BookedSlotsRoom1.Room6Adapter;
import com.helloworld.vitauditoriumbookingsys.Model.RoomC;
import com.helloworld.vitauditoriumbookingsys.Model.RoomF;
import com.helloworld.vitauditoriumbookingsys.R;

import java.util.ArrayList;

public class Room6 extends AppCompatActivity {

    Room6Adapter room6Adapter;
    RecyclerView room6recycler;

    DatabaseReference databaseReference;

    ArrayList<RoomF> roomFList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room6);

        Button next6;

        databaseReference = FirebaseDatabase.getInstance().getReference("RoomF");
        room6recycler = findViewById(R.id.booked_slots_room6_date_recyclerView);
        room6recycler.setHasFixedSize(true);
        room6recycler.setLayoutManager(new LinearLayoutManager(this));
        roomFList = new ArrayList<>();
        room6Adapter = new Room6Adapter(getApplicationContext(), roomFList);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                roomFList.clear();
                for (DataSnapshot ds:snapshot.getChildren()) {
                    RoomF roomF = ds.getValue(RoomF.class);
                    roomF.setDate(ds.getKey());
                    roomFList.add(roomF);
                }
                room6Adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        room6recycler.setAdapter(room6Adapter);

        next6 = (Button) findViewById(R.id.btn_next_step_room6);

        next6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Room6.this, DateBookingRoom6.class);
                startActivity(intent);
            }
        });

    }
}
