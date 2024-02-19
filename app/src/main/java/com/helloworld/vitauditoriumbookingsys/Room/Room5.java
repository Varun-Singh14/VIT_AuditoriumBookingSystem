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
import com.helloworld.vitauditoriumbookingsys.DateBooking.DateBookingRoom5;
import com.helloworld.vitauditoriumbookingsys.HomeAdapter.BookedSlotsRoom1.Room3Adapter;
import com.helloworld.vitauditoriumbookingsys.HomeAdapter.BookedSlotsRoom1.Room5Adapter;
import com.helloworld.vitauditoriumbookingsys.Model.RoomC;
import com.helloworld.vitauditoriumbookingsys.Model.RoomE;
import com.helloworld.vitauditoriumbookingsys.R;

import java.util.ArrayList;

public class Room5  extends AppCompatActivity {

    Room5Adapter room5Adapter;
    RecyclerView room5recycler;

    DatabaseReference databaseReference;

    ArrayList<RoomE> roomEList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room5);

        Button next5;

        databaseReference = FirebaseDatabase.getInstance().getReference("RoomE");
        room5recycler = findViewById(R.id.booked_slots_room5_date_recyclerView);
        room5recycler.setHasFixedSize(true);
        room5recycler.setLayoutManager(new LinearLayoutManager(this));
        roomEList = new ArrayList<>();
        room5Adapter = new Room5Adapter(getApplicationContext(), roomEList);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                roomEList.clear();
                for (DataSnapshot ds:snapshot.getChildren()) {
                    RoomE roomE = ds.getValue(RoomE.class);
                    roomE.setDate(ds.getKey());
                    roomEList.add(roomE);
                }
                room5Adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        room5recycler.setAdapter(room5Adapter);

        next5 = (Button) findViewById(R.id.btn_next_step_room5);

        next5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Room5.this, DateBookingRoom5.class);
                startActivity(intent);
            }
        });

    }
}
